package com.megatravel.utisakservice.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.megatravel.utisakservice.dto.RezervacijaDTO;

@FeignClient("smestaj-service")
public interface SmestajInterface {

	@RequestMapping(value = "/rest/rezervacije/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> preuzmiRezervaciju(@PathVariable("id") Long id);
	
}
