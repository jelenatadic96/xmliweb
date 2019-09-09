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

import com.megatravel.smestajservice.dto.UslugaDTO;
import com.megatravel.smestajservice.model.Usluga;
import com.megatravel.smestajservice.service.UslugaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/rest/usluge", produces = MediaType.APPLICATION_JSON_VALUE)
public class UslugaController {

	@Autowired
	private UslugaService uslugaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UslugaDTO>> preuzmiSveUsluge() {
		return new ResponseEntity<List<UslugaDTO>>(UslugaDTO.transformisi(this.uslugaService.preuzmiSve()), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UslugaDTO> kreiraj(@RequestBody UslugaDTO uslugaDTO) {
		return new ResponseEntity<UslugaDTO>(new UslugaDTO(this.uslugaService.kreiraj(new Usluga(uslugaDTO))), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UslugaDTO> azuriraj(@RequestBody UslugaDTO uslugaDTO,
			@PathVariable("id") Long id) {
		return new ResponseEntity<UslugaDTO>(new UslugaDTO(this.uslugaService.azuriraj(id, new Usluga(uslugaDTO))), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> obrisi(@PathVariable("id") Long id) {
		this.uslugaService.obrisi(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
