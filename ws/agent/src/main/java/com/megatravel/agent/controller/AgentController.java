package com.megatravel.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agent.dto.RezervacijaDTO;
import com.megatravel.agent.dto.SmestajnaJedinicaDTO;
import com.megatravel.agent.service.AgentService;
import com.megatravel.agent.service.RezervacijaService;
import com.megatravel.agent.service.SmestajnaJedinicaService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentController {

	@Autowired
	private SmestajnaJedinicaService smestajnaJedinicaService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@RequestMapping(value = "/rest/agenti/{id}/smestaji",
			method = RequestMethod.GET)
	public ResponseEntity<List<SmestajnaJedinicaDTO>> preuzmiSmestajeKojimaUpravljaAgent(@PathVariable("id") Long id) {
		return new ResponseEntity<List<SmestajnaJedinicaDTO>>(SmestajnaJedinicaDTO.transformisi(this.smestajnaJedinicaService.preuzmiSmestajeKojimaUpravljaAgent(this.agentService.preuzmiJednogAgent(id))), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/agenti/{id}/rezervacije",
			method = RequestMethod.GET)
	public ResponseEntity<List<RezervacijaDTO>> preuzmiSveRezervacijeDozvoljeneAgentu(@PathVariable("id") Long id) {
		return new ResponseEntity<List<RezervacijaDTO>>(RezervacijaDTO.transformisi(this.rezervacijaService.preuzmiRezervacijeKojeJeDodaoAgent(id)), HttpStatus.OK);
	}
	
}
