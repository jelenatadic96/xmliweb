package com.megatravel.korisniciservice.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.korisniciservice.dto.AgentDTO;
import com.megatravel.korisniciservice.dto.RegistracijaDTO;
import com.megatravel.korisniciservice.model.Agent;
import com.megatravel.korisniciservice.service.AgentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	public ResponseEntity<AgentDTO> kreiraj(@RequestBody RegistracijaDTO registracijaDTO) {
		return new ResponseEntity<AgentDTO>(new AgentDTO(this.agentService.kreiraj(new Agent(registracijaDTO), registracijaDTO.getAdresaDTO())), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/upravljani", method = RequestMethod.GET)
	public ResponseEntity<Set<Long>> preuzmiUpravljaneSmestaje(@RequestParam("agent-id") Long agentId) {
		return new ResponseEntity<Set<Long>>(this.agentService.preuzmiUpravljaneSmestaje(agentId), HttpStatus.OK);
	}
	
}
