package com.megatravel.porukeservice.interfaces;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("smestaj-service")
public interface SmestajInterface {

	@RequestMapping(value = "/rest/smestaj/poseceni-smestaji", method = RequestMethod.GET)
	public ResponseEntity<Set<Long>> poseceniSmestaji(@RequestParam("korisnik-id") Long korisnikId);
	
}
