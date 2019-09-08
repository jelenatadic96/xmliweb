package com.megatravel.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agent.dto.UtisakDTO;
import com.megatravel.agent.service.UtisakService;

@RestController
@RequestMapping(value = "/rest/utisci", produces = MediaType.APPLICATION_JSON_VALUE)
public class UtisakController {

	@Autowired
	private UtisakService utisakService;
	
	@RequestMapping(value = "/svi", method = RequestMethod.GET)
	public ResponseEntity<List<UtisakDTO>> preuzmiSveUtiske() {
		return new ResponseEntity<List<UtisakDTO>>(UtisakDTO.transformisi(this.utisakService.preuzmiSveUtiske()), HttpStatus.OK);
	}
	
}
