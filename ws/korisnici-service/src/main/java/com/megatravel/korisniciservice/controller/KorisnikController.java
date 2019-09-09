package com.megatravel.korisniciservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.korisniciservice.dto.KorisnikDTO;
import com.megatravel.korisniciservice.dto.RegistracijaDTO;
import com.megatravel.korisniciservice.dto.ZahtevZaLoginDTO;
import com.megatravel.korisniciservice.model.Korisnik;
import com.megatravel.korisniciservice.model.StatusKorisnika;
import com.megatravel.korisniciservice.service.KorisnikService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/rest/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> preuzmiJednog(@PathVariable("id") Long id) {
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(this.korisnikService.preuzmiJednog(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/email/{mail}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> preuzmiJednogEmail(@PathVariable("mail") String mail) {
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(this.korisnikService.preuzmiPoMejlu(mail)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_OBICAN', 'ROLE_ADMIN')")
	public ResponseEntity<List<KorisnikDTO>> preuzmiSve() {
		return new ResponseEntity<List<KorisnikDTO>>(KorisnikDTO.transformisi(this.korisnikService.preuzmiSve()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_OBICAN', 'ROLE_ADMIN')")
	public ResponseEntity<KorisnikDTO> promeniStatusKorisnika(@PathVariable("id") Long id,
			@RequestParam("status") StatusKorisnika statusKorisnika) {
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(this.korisnikService.promeniStatusKorisnika(id, statusKorisnika)), HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody ZahtevZaLoginDTO loginDTO) {
		String token = this.korisnikService.uloguj(loginDTO.getMejl(), loginDTO.getLozinka());
		ObjectMapper mapper = new ObjectMapper();
		try {
			return new ResponseEntity<String>(mapper.writeValueAsString(token), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Void> signup(@RequestBody RegistracijaDTO registracijaDTO, HttpServletRequest request) {
		if (!registracijaDTO.getRepeatPassword().equals(registracijaDTO.getPassword())) {
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
		this.korisnikService.registruj(new Korisnik(registracijaDTO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
}
