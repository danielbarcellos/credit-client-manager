package com.drbsoft.ccm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.drbsoft.ccm.persistence.ClientRepository;
import com.drbsoft.ccm.persistence.entity.Client;

@SpringBootApplication
@ComponentScan("com.drbsoft.ccm")
public class CcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcmApplication.class, args);
	}

	/**
	 * Visa criar dois usarios dummies para fins de testes iniciais.
	 * 
	 * @param clientRepository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(ClientRepository clientRepository) {
		return (args)-> {
			Client daniel = new Client("Daniel", "Rua A");
			Client rachel = new Client("Rachel", "Rua B");
			
			clientRepository.save(daniel);
			clientRepository.save(rachel);
		};
	}
}