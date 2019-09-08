package com.megatravel.smestajservice.controller;

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

import com.megatravel.smestajservice.dto.TipSmestajaDTO;
import com.megatravel.smestajservice.model.TipSmestaja;
import com.megatravel.smestajservice.service.TipSmestajaService;

@RestController
@RequestMapping(value = "/rest/tipovi", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipSmestajaController {

	@Autowired
	private TipSmestajaService tipSmestajaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TipSmestajaDTO>> preuzmiSveTipove() {
		return new ResponseEntity<List<TipSmestajaDTO>>(TipSmestajaDTO.transformisi(this.tipSmestajaService.preuzmiSve()), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipSmestajaDTO> kreiraj(@RequestBody TipSmestajaDTO tipSmestajaDTO) {
		return new ResponseEntity<TipSmestajaDTO>(new TipSmestajaDTO(this.tipSmestajaService.kreiraj(new TipSmestaja(tipSmestajaDTO))), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipSmestajaDTO> azuriraj(@RequestBody TipSmestajaDTO tipSmestajaDTO,
			@PathVariable("id") Long id) {
		return new ResponseEntity<TipSmestajaDTO>(new TipSmestajaDTO(this.tipSmestajaService.azuriraj(id, new TipSmestaja(tipSmestajaDTO))), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> obrisi(@PathVariable("id") Long id) {
		this.tipSmestajaService.obrisi(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
