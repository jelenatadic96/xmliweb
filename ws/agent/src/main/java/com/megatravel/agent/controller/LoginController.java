package com.megatravel.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agent.dto.AgentDTO;
import com.megatravel.agent.dto.ZahtevZaLoginDTO;
import com.megatravel.agent.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AgentDTO> uloguj(@RequestBody ZahtevZaLoginDTO loginDTO) {
		return new ResponseEntity<AgentDTO>(new AgentDTO(this.loginService.uloguj(loginDTO.getMejl(), loginDTO.getLozinka())), HttpStatus.OK);
	}
	
}
