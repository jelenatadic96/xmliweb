package com.megatravel.agent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.model.Korisnik;
import com.megatravel.agent.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public Korisnik preuzmiJednog(Long id) {
		Optional<Korisnik> korisnik = this.korisnikRepository.findById(id);
		if(korisnik.isPresent()) {
			return korisnik.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public List<Korisnik> preuzmiSve() {
		return this.korisnikRepository.findAll();
	}

	public Korisnik preuzmiPoMejlu(String mejl) {
		Optional<Korisnik> korisnik = this.korisnikRepository.findByMejl(mejl);
		if(korisnik.isPresent()) {
			return korisnik.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}
