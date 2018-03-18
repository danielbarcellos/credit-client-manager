package com.drbsoft.ccm.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.drbsoft.ccm.persistence.entity.Client;
/**
 * Teste do repository ClientRepository. 
 * 
 * @author daniel
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientRepositoryTest {

	private static final String ANDERSON = "Anderson";

	private static final String RACHEL = "Rachel";
	
	private static final String DANIEL = "Daniel";
	
	@Autowired
	ClientRepository repository; 
	
	@Before
	public void before() {
		assertNotNull(repository);
	}
	/**
	 * Deve testar se consegue salvar quando um novo cliente é criado.
	 */
	@Test
	public void shouldSaveWhenANewClientIsCreated() {
		Client expected = new Client();
		expected.setName(ANDERSON);
		
		this.repository.save(expected);
	}

	/**
	 * Deve testar quando um novo cliente é criado e logo recupera-lo.
	 */
	@Test
	public void shouldSaveAndRetrieveWhenANewClientIsCreated() {
		Client expected = new Client();
		expected.setName(DANIEL);
		
		this.repository.save(expected);
		
		Client probe = new Client();
		probe.setName(DANIEL);
		
		Client actual = this.repository.findOne(Example.of(probe)).get();
		assertEquals(expected, actual);
	}
	
	/**
	 * Deve testar se consegue testar, recuperar o cliente e em seguida remove-lo.
	 */
	@Test
	public void shouldSaveAndRetrieveAndDeleteWhenANewClientIsCreated() {
		Client expected = new Client();
		expected.setName(RACHEL);
		
		this.repository.save(expected);
		
		Client probe = new Client();
		probe.setName(RACHEL);
		
		Client actual = this.repository.findOne(Example.of(probe)).get();
		assertEquals(expected, actual);
		
		this.repository.delete(expected);
		
		assertEquals(false, this.repository.findOne(Example.of(probe)).isPresent());;
	}
}
