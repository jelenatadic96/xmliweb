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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.smestajservice.dto.SmestajnaJedinicaDTO;
import com.megatravel.smestajservice.dto.UpitPretrageDTO;
import com.megatravel.smestajservice.service.SmestajnaJedinicaService;

@RestController
@RequestMapping(value = "/rest/smestaj", produces = MediaType.APPLICATION_JSON_VALUE)
public class SmestajnaJedinicaController {

	@Autowired
	private SmestajnaJedinicaService smestajnaJedinicaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SmestajnaJedinicaDTO>> preuzmiSveSmestajneJedinice() {
		return new ResponseEntity<List<SmestajnaJedinicaDTO>>(SmestajnaJedinicaDTO.transformisi(this.smestajnaJedinicaService.preuzmiSve()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pretraga", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmestajnaJedinicaDTO>> pretraziSveSmestajneJedinice(@RequestBody UpitPretrageDTO upitPretrageDTO) {
		return new ResponseEntity<List<SmestajnaJedinicaDTO>>(SmestajnaJedinicaDTO.transformisi(this.smestajnaJedinicaService.pretrazi(upitPretrageDTO)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajnaJedinicaDTO> korigujOcenu(@PathVariable("id") Long id,
			@RequestParam("ocena") double ocena) {
		return new ResponseEntity<SmestajnaJedinicaDTO>(new SmestajnaJedinicaDTO(this.smestajnaJedinicaService.korigujOcenu(id, ocena)), HttpStatus.ACCEPTED);
	}
	
}
