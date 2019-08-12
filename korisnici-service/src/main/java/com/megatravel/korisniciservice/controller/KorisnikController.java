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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.korisniciservice.dto.KorisnikDTO;
import com.megatravel.korisniciservice.model.Korisnik;
import com.megatravel.korisniciservice.model.StatusKorisnika;
import com.megatravel.korisniciservice.service.KorisnikService;

@RestController
@RequestMapping(value = "/rest/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> preuzmiJednog(@PathVariable("id") Long id) {
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(this.korisnikService.preuzmiJednog(id)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> preuzmiSve() {
		return new ResponseEntity<List<KorisnikDTO>>(KorisnikDTO.transformisi(this.korisnikService.preuzmiSve()), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> kreiraj(@RequestBody KorisnikDTO korisnikDTO) {
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(this.korisnikService.kreiraj(new Korisnik(korisnikDTO))), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<KorisnikDTO> promeniStatusKorisnika(@PathVariable("id") Long id,
			@RequestParam("status") StatusKorisnika statusKorisnika) {
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(this.korisnikService.promeniStatusKorisnika(id, statusKorisnika)), HttpStatus.ACCEPTED);
	}
	
}
