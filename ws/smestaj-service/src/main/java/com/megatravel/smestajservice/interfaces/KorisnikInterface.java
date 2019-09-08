package com.megatravel.smestajservice.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.megatravel.smestajservice.dto.KorisnikDTO;

@FeignClient("korisnici-service")
public interface KorisnikInterface {

	@RequestMapping(value = "/rest/korisnici/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> preuzmiJednogKorisnika(@PathVariable("id") Long id);
	
}
