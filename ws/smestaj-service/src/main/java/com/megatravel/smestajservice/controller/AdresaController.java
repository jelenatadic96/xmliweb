package com.megatravel.smestajservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.smestajservice.dto.AdresaDTO;
import com.megatravel.smestajservice.model.Adresa;
import com.megatravel.smestajservice.service.AdresaService;

@RestController
@RequestMapping(value = "/rest/adrese")
public class AdresaController {

	@Autowired
	private AdresaService adresaService;
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdresaDTO> kreiraj(@RequestBody AdresaDTO adresaDTO) {
		return new ResponseEntity<AdresaDTO>(new AdresaDTO(this.adresaService.kreiraj(new Adresa(adresaDTO))), HttpStatus.CREATED);
	}
	
}
