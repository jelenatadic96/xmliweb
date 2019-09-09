package com.megatravel.smestajservice.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.smestajservice.dto.RezervacijaDTO;
import com.megatravel.smestajservice.service.RezervacijaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RezervacijaController {

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@RequestMapping(value = "/rest/korisnici/{id}/rezervacije/istorija", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> preuzmiIstorijuRezervacija(@PathVariable("id") Long id) {
		return new ResponseEntity<List<RezervacijaDTO>>(RezervacijaDTO.transformisi(this.rezervacijaService.preuzmiIstoriju(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/korisnici/{id}/rezervacije/aktivne", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> preuzmiAktivneRezervacije(@PathVariable("id") Long id) {
		return new ResponseEntity<List<RezervacijaDTO>>(RezervacijaDTO.transformisi(this.rezervacijaService.preuzmiAktivne(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/korisnici/{id}/rezervacije", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> rezervisiSmestajnuJedinicu(@RequestBody RezervacijaDTO rezervacijaDTO,
			@PathVariable("id") Long id) {
		return new ResponseEntity<RezervacijaDTO>(new RezervacijaDTO(this.rezervacijaService.rezervisiSmestajnuJedinicu(id, rezervacijaDTO)), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/rest/rezervacije/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> otkaziRezervaciju(@PathVariable("id") Long id) {
		this.rezervacijaService.otkazi(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
