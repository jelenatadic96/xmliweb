package com.megatravel.korisniciservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.korisniciservice.dto.AgentDTO;
import com.megatravel.korisniciservice.model.Agent;
import com.megatravel.korisniciservice.service.AgentService;

@RestController
@RequestMapping(value = "/rest/agenti", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentController {

	@Autowired
	private AgentService agentService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AgentDTO> preuzmiJednog(@PathVariable("id") Long id) {
		return new ResponseEntity<AgentDTO>(new AgentDTO(this.agentService.preuzmiJednogAgent(id)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AgentDTO>> preuzmiSve() {
		return new ResponseEntity<List<AgentDTO>>(AgentDTO.transformisi(this.agentService.preuzmiSveAgente()), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AgentDTO> kreiraj(@RequestBody AgentDTO agentDTO) {
		return new ResponseEntity<AgentDTO>(new AgentDTO(this.agentService.kreiraj(new Agent(agentDTO))), HttpStatus.CREATED);
	}
	
}
