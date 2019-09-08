package com.megatravel.smestajservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestajservice.model.Usluga;
import com.megatravel.smestajservice.repository.UslugaRepository;

@Service
public class UslugaService {

	@Autowired
	private UslugaRepository uslugaRepository;
	
	public List<Usluga> preuzmiSve() {
		return this.uslugaRepository.findAll();
	}
	
	private Usluga preuzmiJednu(Long id) {
		Optional<Usluga> usluga = this.uslugaRepository.findById(id);
		if(usluga.isPresent()) {
			return usluga.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public Usluga kreiraj(Usluga usluga) {
		return this.uslugaRepository.save(usluga);
	}
	
	public Usluga azuriraj(Long id, Usluga novaUsluga) {
		Usluga staraUsluga = this.preuzmiJednu(id);
		staraUsluga.prekopiraj(novaUsluga);
		return this.uslugaRepository.save(staraUsluga);
	}
	
	public void obrisi(Long id) {
		Usluga usluga = this.preuzmiJednu(id);
		if(this.dozvoljenoBrisanje(usluga)) {
			this.uslugaRepository.delete(usluga);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}
	
	private boolean dozvoljenoBrisanje(Usluga usluga) {
		return usluga.getSpojeviSaJedinicama().isEmpty();
	}
	
}
