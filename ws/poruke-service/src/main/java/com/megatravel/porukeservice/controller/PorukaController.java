package com.megatravel.porukeservice.controller;

import java.util.List;

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

import com.megatravel.porukeservice.dto.AgentDTO;
import com.megatravel.porukeservice.dto.PorukaDTO;
import com.megatravel.porukeservice.model.Poruka;
import com.megatravel.porukeservice.service.PorukaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class PorukaController {

	@Autowired
	private PorukaService porukaService;
	
	@RequestMapping(value = "/korisnici/{id}/poruke", method = RequestMethod.GET)
	public ResponseEntity<List<AgentDTO>> preuzmiAgenteSaKojimaCetujeKorisnik(@PathVariable("id") Long id) {
		return new ResponseEntity<List<AgentDTO>>(this.porukaService.preuzmiAgenteSaKojimaCetujeKorisnik(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/korisnici/{id}/poruke/{agent-id}", method = RequestMethod.GET)
	public ResponseEntity<List<PorukaDTO>> preuzmiPorukeKorisnikaSaAgentom(@PathVariable("id") Long id,
			@PathVariable("agent-id") Long agentId) {
		return new ResponseEntity<List<PorukaDTO>>(PorukaDTO.transformisi(this.porukaService.preuzmiPorukeKorisnikaSaAgentom(id, agentId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/korisnici/{korisnik-id}/poruke", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PorukaDTO> posaljiPorukuAgentu(@RequestBody PorukaDTO porukaDTO,
			@PathVariable("korisnik-id") Long korisnikId,
			@RequestParam("agent-id") Long agentId) {
		return new ResponseEntity<PorukaDTO>(new PorukaDTO(this.porukaService.posaljiPoruku(new Poruka(porukaDTO), agentId, korisnikId)), HttpStatus.CREATED);
	}
	
}
