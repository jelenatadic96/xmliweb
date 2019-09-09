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
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agent.dto.RezervacijaDTO;
import com.megatravel.agent.service.RezervacijaService;

@RestController
public class RezervacijaController {

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@RequestMapping(value = "/rest/smestaj/{id}/rezervacije", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> preuzmiRezervacijeSmestajneJedinice(@PathVariable("id") Long id) {
		return new ResponseEntity<List<RezervacijaDTO>>(RezervacijaDTO.transformisi(this.rezervacijaService.preuzmiRezervacijeSmestaja(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/rezervacije/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> potvrdiRealizacijuRezervacije(@PathVariable("id") Long id) {
		return new ResponseEntity<RezervacijaDTO>(new RezervacijaDTO(this.rezervacijaService.potvrdiRezervaciju(id)), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/rest/rezervacije", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> kreirajPraznuRezervaciju(@RequestBody RezervacijaDTO rezervacijaDTO) {
		return new ResponseEntity<RezervacijaDTO>(new RezervacijaDTO(this.rezervacijaService.kreirajPraznuRezervaciju(rezervacijaDTO)), HttpStatus.CREATED);
	}
	
}
