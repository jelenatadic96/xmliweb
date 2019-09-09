package com.megatravel.porukeservice.interfaces;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.megatravel.porukeservice.dto.AgentDTO;
import com.megatravel.porukeservice.dto.KorisnikDTO;

@FeignClient("korisnici-service")
public interface KorisnikInterface {

	@RequestMapping(value = "/rest/korisnici/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> preuzmiJednogKorisnika(@PathVariable("id") Long id);
	
	@RequestMapping(value = "/rest/agenti/{id}", method = RequestMethod.GET)
	public ResponseEntity<AgentDTO> preuzmiJednogAgenta(@PathVariable("id") Long id);
	
	@RequestMapping(value = "/rest/agenti/upravljani", method = RequestMethod.GET)
	public ResponseEntity<Set<Long>> preuzmiUpravljaneSmestaje(@RequestParam("agent-id") Long agentId);
	
}
