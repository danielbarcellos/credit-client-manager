package com.drbsoft.ccm.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.apache.commons.lang3.StringUtils;
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
 * 
 * Este é um teste de integração que visa avaliar o endpoint <pre>/api/clientes</pre> como
 * um recurso restful da entidade {@link Client}.
 * 
 * @author Daniel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { CcmApplication.class })
@WebAppConfiguration
public class ClientIntegrationTest extends AbstractIntegrationTest {

	private static final String URI = "/api/clientes";

	private static final String CLIENT_NAME = "Pedro";

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Deve testar a consulta via GET dos clientes.
	 * @throws Exception - Quando ocorre algum erro inesperado.
	 */
	@Test
	public void shouldGetAllClients() throws Exception {
		this.mockMvc.perform(get(URI)).andExpect(status().isOk()).andReturn();
	}

	/**
	 * Deve testar a consulta via GET dos clientes pelo seu id.
	 * @throws Exception - Quando ocorre algum erro inesperado.
	 */
	@Test
	public void shouldGetByIdWhenClientsIsSaved() throws Exception {
		Client in = buildClient();

		String carrinhoAsJson = json(in);

		MvcResult mvcResult = this.mockMvc
				.perform(post(URI)
				.contentType(contentType)
				.content(carrinhoAsJson))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(contentType))
				.andReturn();

		String contentAsString = mvcResult.getResponse().getContentAsString();
		Client out = json(contentAsString, Client.class);

		assertThat(out, isA(Client.class));

		this.mockMvc
				.perform(get(URI + "/" + out.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(equalTo(out.getId().intValue()))));
	}
	
	/**
	 * Deve testar o salvamento via POST dos clientes.
	 * @throws Exception - Quando ocorre algum erro inesperado.
	 */
	@Test
	public void shouldSaveWhenAClientIsCreated() throws Exception {
		Client in = buildClient();
		
		String carrinhoAsJson = json(in);
		
		MvcResult mvcResult = this.mockMvc
			.perform(post(URI)
            .contentType(contentType)
            .content(carrinhoAsJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.name", is(in.getName())))
            .andReturn();
		
		String contentAsString = mvcResult.getResponse().getContentAsString();
		Client out = json(contentAsString, Client.class);
		
		assertThat(out, isA(Client.class));
		assertThat(in.getName(), is(equalTo(out.getName())));
	}
	
	/**
	 * Deve testar o update via PUT dos clientes.
	 * @throws Exception - Quando ocorre algum erro inesperado.
	 */
	@Test
	public void shouldUpdateWhenAClientIsCreated() throws Exception {
		Client in = buildClient();
		
		String carrinhoAsJson1 = json(in);
		
		MvcResult mvcResult1 = this.mockMvc
			.perform(post(URI)
            .contentType(contentType)
            .content(carrinhoAsJson1))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.name", is(in.getName())))
            .andReturn();
		
		String contentAsString1 = mvcResult1.getResponse().getContentAsString();
		Client out = json(contentAsString1, Client.class);
		
		assertThat(out, isA(Client.class));
		assertThat(in.getName(), is(equalTo(out.getName())));
		
		String newName = "Catarina";
		out.setName(newName); // Altera o nome...
		
		String carrinhoAsJson2 = json(out);
		
		this.mockMvc
			.perform(put(URI + "/" + out.getId())
            .contentType(contentType)
            .content(carrinhoAsJson2))
            .andExpect(status().isAccepted())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.name", is(newName)));
	}
	
	/**
	 * Deve testar a remocao via DELETE dos clientes.
	 * @throws Exception - Quando ocorre algum erro inesperado.
	 */
	@Test
	public void shouldRemoveWhenAClientIsCreated() throws Exception {
		Client in = buildClient();
		in.setName(CLIENT_NAME);
		
		String carrinhoAsJson1 = json(in);
		
		MvcResult mvcResult1 = this.mockMvc
			.perform(post(URI)
            .contentType(contentType)
            .content(carrinhoAsJson1))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.name", is(in.getName())))
            .andReturn();
		
		String contentAsString1 = mvcResult1.getResponse().getContentAsString();
		Client out = json(contentAsString1, Client.class);
		
		assertThat(out, isA(Client.class));
		assertThat(in.getName(), is(equalTo(out.getName())));
		
		String carrinhoAsJson2 = json(out);
		
		this.mockMvc
			.perform(delete(URI + "/" + out.getId())
            .contentType(contentType)
            .content(carrinhoAsJson2))
            .andExpect(status().isOk());
		
		MvcResult result = this.mockMvc
			.perform(get(URI + "/" + out.getId()))
		    .andExpect(status().isNotFound())
		    .andReturn();
		
		assertThat(StringUtils.isEmpty(result.getResponse().getContentAsString()), is(equalTo(Boolean.TRUE)));
	}

	private Client buildClient() {
		Client client = new Client();
		client.setName(CLIENT_NAME);
		return client;
	}
}