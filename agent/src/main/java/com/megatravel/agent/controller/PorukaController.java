package com.megatravel.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agent.dto.KorisnikDTO;
import com.megatravel.agent.dto.PorukaDTO;
import com.megatravel.agent.model.Poruka;
import com.megatravel.agent.service.PorukaService;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class PorukaController {

	@Autowired
	private PorukaService porukaService;
	
	@RequestMapping(value = "/agenti/{id}/poruke", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> preuzmiKorisnikeSaKojimaCetujeAgent(@PathVariable("id") Long id) {
		return new ResponseEntity<List<KorisnikDTO>>(KorisnikDTO.transformisi(this.porukaService.preuzmiKorisnikeSaKojimaCetujeAgent(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/agenti/{id}/poruke/{korisnik-id}", method = RequestMethod.GET)
	public ResponseEntity<List<PorukaDTO>> preuzmiPorukeAgentSaKorisnikom(@PathVariable("id") Long id,
			@PathVariable("korisnik-id") Long korisnikId) {
		return new ResponseEntity<List<PorukaDTO>>(PorukaDTO.transformisi(this.porukaService.preuzmiPorukeAgentaSaKorisnikom(id, korisnikId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/agenti/{agent-id}/poruke", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PorukaDTO> posaljiPorukuKorisniku(@RequestBody PorukaDTO porukaDTO,
			@PathVariable("agent-id") Long agentId,
			@RequestParam("korisnik-id") Long korisnikId) {
		return new ResponseEntity<PorukaDTO>(new PorukaDTO(this.porukaService.posaljiPoruku(new Poruka(porukaDTO), agentId, korisnikId)), HttpStatus.CREATED);
	}
	
}
