package com.drbsoft.ccm;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.drbsoft.ccm.persistence.ClientRepository;
import com.drbsoft.ccm.persistence.entity.Client;
import com.drbsoft.ccm.persistence.entity.CreditLine;

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
			
			CreditLine limit0 = new CreditLine();
			limit0.setValue(BigDecimal.valueOf(1500.0));
			limit0.setVenture("B");
			
			daniel.setLimits(limit0);
			
			CreditLine limit1 = new CreditLine();
			limit1.setValue(BigDecimal.valueOf(1500.0));
			limit1.setVenture("B");
			
			rachel.setLimits(limit1);
			
			clientRepository.save(daniel);
			clientRepository.save(rachel);
		};
	}
}