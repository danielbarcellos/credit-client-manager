package com.drbsoft.ccm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.drbsoft.ccm.persistence.ClientRepository;
import com.drbsoft.ccm.persistence.entity.Client;

@RestController
@RequestMapping("/api/clientes")
public class ClientRestController {

	@Autowired
	ClientRepository repository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> getById(@PathVariable("id") Long id) {
		Client probe = new Client();
		probe.setId(id);
		
		Optional<Client> optional = this.repository.findOne(Example.of(probe));
		if(!optional.isPresent()) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Client>(optional.get(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Client> getAll(Pageable pageRequest) {
		return this.repository.findAll(pageRequest);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> save(@RequestBody Client entity) {
		Client client = this.repository.save(entity);
		return new ResponseEntity<Client>(client, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> update(@PathVariable("id") Long id, @RequestBody Client entity) {
		Optional<Client> optional = this.repository.findById(id);
		if(!optional.isPresent()) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		
		System.out.println(entity);
		
		Client existent = optional.get();
		existent.setId(id);
		existent.setName(entity.getName());
		existent.setLimits(entity.getLimits());
		existent.setAddress(entity.getAddress());
		
		Client client = this.repository.save(existent);
		return new ResponseEntity<Client>(client, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> delete(@PathVariable("id") Long id) {
		Optional<Client> optional = this.repository.findById(id);
		if(!optional.isPresent()) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		
		this.repository.deleteById(id);
		return new ResponseEntity<Client>(HttpStatus.OK);
	}
}