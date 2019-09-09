package com.megatravel.utisakservice.controller;

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

import com.megatravel.utisakservice.dto.UtisakDTO;
import com.megatravel.utisakservice.service.UtisakService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/rest/utisci", produces = MediaType.APPLICATION_JSON_VALUE)
public class UtisakController {

	@Autowired
	private UtisakService utisakService;
	
	@RequestMapping(value = "/svi", method = RequestMethod.GET)
	public ResponseEntity<List<UtisakDTO>> preuzmiSveUtiske() {
		return new ResponseEntity<List<UtisakDTO>>(UtisakDTO.transformisi(this.utisakService.preuzmiSveUtiske()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/neodobreni", method = RequestMethod.GET)
	public ResponseEntity<List<UtisakDTO>> preuzmiNeodobreneKomentare() {
		return new ResponseEntity<List<UtisakDTO>>(UtisakDTO.transformisi(this.utisakService.preuzmiNeodobreneUtiske()), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			value = "/komentar")
	public ResponseEntity<UtisakDTO> ostaviKomentar(@RequestParam("korisnik") Long korisnikId,
			@RequestBody UtisakDTO utisakDTO) {
		return new ResponseEntity<UtisakDTO>(new UtisakDTO(this.utisakService.ostaviKomentar(utisakDTO.getRezervacijaId(), korisnikId, utisakDTO.getKomentar())), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			value = "/ocena")
	public ResponseEntity<UtisakDTO> ostaviOcenu(@RequestParam("korisnik") Long korisnikId,
			@RequestBody UtisakDTO utisakDTO) {
		return new ResponseEntity<UtisakDTO>(new UtisakDTO(this.utisakService.ostaviOcenu(utisakDTO.getRezervacijaId(), korisnikId, utisakDTO.getOcena())), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UtisakDTO> odobriKomentar(@PathVariable("id") Long id) {
		return new ResponseEntity<UtisakDTO>(new UtisakDTO(this.utisakService.odobriKomentar(id)), HttpStatus.ACCEPTED);
	}
	
}
