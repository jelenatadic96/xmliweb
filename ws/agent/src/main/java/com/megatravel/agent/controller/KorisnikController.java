package com.megatravel.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agent.dto.KorisnikDTO;
import com.megatravel.agent.service.KorisnikService;

@RestController
@RequestMapping(value = "/rest/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> preuzmiSveKorisnike() {
		return new ResponseEntity<List<KorisnikDTO>>(KorisnikDTO.transformisi(this.korisnikService.preuzmiSve()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> preuzmiJednogPoId(@PathVariable("id") Long id) {
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(this.korisnikService.preuzmiJednog(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pretrazi", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> preuzmiJednogPoMejlu(@RequestParam("mejl") String mejl) {
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(this.korisnikService.preuzmiPoMejlu(mejl)), HttpStatus.OK);
	}
	
}
