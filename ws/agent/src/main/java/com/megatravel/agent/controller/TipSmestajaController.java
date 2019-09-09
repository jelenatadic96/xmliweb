package com.megatravel.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agent.dto.TipSmestajaDTO;
import com.megatravel.agent.service.TipSmestajaService;

@RestController
@RequestMapping(value = "/rest/tipovi", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipSmestajaController {

	@Autowired
	private TipSmestajaService tipSmestajaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TipSmestajaDTO>> preuzmiSveTipove() {
		return new ResponseEntity<List<TipSmestajaDTO>>(TipSmestajaDTO.transformisi(this.tipSmestajaService.preuzmiSve()), HttpStatus.OK);
	}
	
}
