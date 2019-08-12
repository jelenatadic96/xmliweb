package com.megatravel.utisakservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.utisakservice.model.Utisak;
import com.megatravel.utisakservice.repository.UtisakRepository;

@Service
public class UtisakService {

	@Autowired
	private UtisakRepository utisakRepository;
	
	public List<Utisak> preuzmiSveUtiske() {
		return this.utisakRepository.findAll();
	}
	
	public List<Utisak> preuzmiNeodobreneUtiske() {
		List<Utisak> utisci = this.preuzmiSveUtiske();
		utisci.removeIf(x -> x.isKomentarOdobren());
		return utisci;
	}
	
	private Utisak preuzmiJedan(Long id) {
		Optional<Utisak> utisak = this.utisakRepository.findById(id);
		if(utisak.isPresent()) {
			return utisak.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public Utisak kreiraj(Utisak utisak, Long korisnikId) {
		utisak.setKomentarOdobren(false);
		// Proveri da li je korisnik na rezervaciji
		// Proveri da li je rezervacija realizovana
		// Proveri da li rezervacija ima utisak već
		// Koriguj ocenu smeštaja u zavisnosti od nove ocene
		return this.utisakRepository.save(utisak);
	}

	public Utisak odobriKomentar(Long id) {
		Utisak utisak = this.preuzmiJedan(id);
		utisak.setKomentarOdobren(true);
		return this.utisakRepository.save(utisak);
	}
	
}
