package com.drbsoft.ccm.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drbsoft.ccm.persistence.ClientRepository;
import com.drbsoft.ccm.persistence.entity.Client;
import com.drbsoft.ccm.persistence.entity.Venture;
import com.drbsoft.ccm.service.Amount;
import com.drbsoft.ccm.service.CreditLineService;

@RestController
@RequestMapping("/api/clientes")
public class CreditLineRestController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	CreditLineService creditLineService;

	@RequestMapping(value = "/{id}/creditAmount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Amount> getById(@PathVariable("id") Long id, @RequestParam(value = "venture") String venture, 
			@RequestParam(value = "requestedValue") Double requestedValue,
			Pageable pageRequest) {
		
		Optional<Client> optional = this.clientRepository.findById(id);
		if(!optional.isPresent() || StringUtils.isEmpty(venture)) {
			return new ResponseEntity<Amount>(HttpStatus.NOT_FOUND);
		}
		
		Amount calculatedAmount = this.creditLineService.calculateAmount(optional.get(), Venture.valueOf(venture), BigDecimal.valueOf(requestedValue));
		
		return new ResponseEntity<Amount>(calculatedAmount, HttpStatus.OK);
	}
}
