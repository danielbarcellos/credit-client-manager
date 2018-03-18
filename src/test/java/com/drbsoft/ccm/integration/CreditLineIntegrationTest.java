package com.drbsoft.ccm.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import com.drbsoft.ccm.CcmApplication;
import com.drbsoft.ccm.persistence.entity.Client;
/**
 * Este é um teste de integração que visa validar as regras de calculo de juro sobre um valor
 * solicitado.
 * O serviço testado é o <pre>/api/clientes/{id}/creditAmount</pre>. 
 * 
 * @author Daniel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { CcmApplication.class })
@WebAppConfiguration
public class CreditLineIntegrationTest extends AbstractIntegrationTest {

	private static final String ENDPOINT = "/api/clientes";
	private static final String ENDPOINT_CLIENT = "/api/clientes/{0}";
	private static final String ENDPOINT_CLIENT_CREDIT_AMOUNT = "/api/clientes/{0}/creditAmount";

	private static final String CLIENT_NAME = "Antonio";
	private String clientAsJson;
	private Client client;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
		// Cria o cliente.
		Client in = new Client();
		in.setName(CLIENT_NAME);

		clientAsJson = json(in);
		
		MvcResult mvcResult = this.mockMvc
				.perform(post(ENDPOINT)
						.contentType(contentType)
						.content(clientAsJson))
						.andExpect(status().isCreated())
						.andExpect(content().contentType(contentType))
						.andReturn();
		
		String contentAsString = mvcResult.getResponse().getContentAsString();
		client = json(contentAsString, Client.class);
		
		assertThat(client, isA(Client.class));
		
		// Verifica se o cliente foi criado com sucesso.
		this.mockMvc
		.perform(get(ENDPOINT_CLIENT, client.getId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(equalTo(client.getId().intValue()))));
	}

	/**
	 * Deve buscar o calculo de juros sobre uma taxa de jutos relativa ao risco A.
	 * Logo, não deve cobrar juros sobre o valor solicitado.
	 * @throws Exception - Quando ocorre algum erro inesperado.
	 */
	@Test
	public void shouldGetClientCreditAmountCalculationWhenRiskIs_A() throws Exception {
		this.mockMvc
			.perform(get(ENDPOINT_CLIENT_CREDIT_AMOUNT, client.getId())
				.param("venture", "A")
				.param("requestedValue", "1000.0"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$.client.id", is(equalTo(client.getId().intValue()))))
			.andExpect(jsonPath("$.requestedValue", is(equalTo(1000.0))))
			.andExpect(jsonPath("$.calculatedValue", is(equalTo(1000.0))));
		
	}

	/**
	 * Deve buscar o calculo de juros sobre uma taxa de jutos relativa ao risco B.
	 * Logo, deve cobrar <B>10%</B> de juros sobre o valor solicitado.
	 * @throws Exception - Quando ocorre algum erro inesperado.
	 */
	@Test
	public void shouldGetClientCreditAmountCalculationWhenRiskIs_B() throws Exception {
		this.mockMvc
			.perform(get(ENDPOINT_CLIENT_CREDIT_AMOUNT, client.getId())
				.param("venture", "B")
				.param("requestedValue", "1000.0"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$.client.id", is(equalTo(client.getId().intValue()))))
			.andExpect(jsonPath("$.requestedValue", is(equalTo(1000.0))))
			.andExpect(jsonPath("$.calculatedValue", is(equalTo(1100.0))));
		
	}

	/**
	 * Deve buscar o calculo de juros sobre uma taxa de jutos relativa ao risco C.
	 * Logo, deve cobrar <B>20%</B> de juros sobre o valor solicitado.
	 * @throws Exception - Quando ocorre algum erro inesperado.
	 */
	@Test
	public void shouldGetClientCreditAmountCalculationWhenRiskIs_C() throws Exception {
		this.mockMvc
		.perform(get(ENDPOINT_CLIENT_CREDIT_AMOUNT, client.getId())
				.param("venture", "C")
				.param("requestedValue", "1000.0"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.client.id", is(equalTo(client.getId().intValue()))))
				.andExpect(jsonPath("$.requestedValue", is(equalTo(1000.0))))
				.andExpect(jsonPath("$.calculatedValue", is(equalTo(1200.0))));
		
	}
}