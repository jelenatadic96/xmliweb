package com.megatravel.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agent.dto.UslugaDTO;
import com.megatravel.agent.service.UslugaService;

@RestController
@RequestMapping(value = "/rest/usluge", produces = MediaType.APPLICATION_JSON_VALUE)
public class UslugaController {

	@Autowired
	private UslugaService uslugaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UslugaDTO>> preuzmiSveUsluge() {
		return new ResponseEntity<List<UslugaDTO>>(UslugaDTO.transformisi(this.uslugaService.preuzmiSve()), HttpStatus.OK);
	}
	
}
