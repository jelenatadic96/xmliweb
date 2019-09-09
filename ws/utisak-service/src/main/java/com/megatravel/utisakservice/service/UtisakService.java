package com.megatravel.utisakservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.utisakservice.dto.RezervacijaDTO;
import com.megatravel.utisakservice.interfaces.SmestajInterface;
import com.megatravel.utisakservice.model.Utisak;
import com.megatravel.utisakservice.repository.UtisakRepository;

@Service
public class UtisakService {

	@Autowired
	private UtisakRepository utisakRepository;
	
	@Autowired
	private SmestajInterface smestajInterface;
	
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

	public Utisak odobriKomentar(Long id) {
		Utisak utisak = this.preuzmiJedan(id);
		utisak.setKomentarOdobren(true);
		return this.utisakRepository.save(utisak);
	}

	public Utisak ostaviKomentar(Long rezervacijaId, Long korisnikId, String komentar) {
		if(!this.rezervacijaJeRealizovana(rezervacijaId)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		Optional<Utisak> utisakOptional = this.utisakRepository.findByRezervacijaId(rezervacijaId);
		if(utisakOptional.isPresent()) {
			Utisak utisak = utisakOptional.get();
			if(utisak.getKomentar() == null || utisak.getKomentar().isEmpty()) {
				utisak.setKomentar(komentar);
				return this.utisakRepository.save(utisak);
			} else {
				throw new ResponseStatusException(HttpStatus.CONFLICT);
			}
		} else {
			Utisak utisak = new Utisak();
			utisak.setRezervacijaId(rezervacijaId);
			utisak.setKomentar(komentar);
			utisak.setOcena(0);
			utisak.setKomentarOdobren(false);
			return this.utisakRepository.save(utisak);
		}
	}

	public Utisak ostaviOcenu(Long rezervacijaId, Long korisnikId, Integer ocena) {
		if(!this.rezervacijaJeRealizovana(rezervacijaId)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		Optional<Utisak> utisakOptional = this.utisakRepository.findByRezervacijaId(rezervacijaId);
		if(utisakOptional.isPresent()) {
			Utisak utisak = utisakOptional.get();
			if(utisak.getOcena() == 0) {
				utisak.setOcena(ocena);
				return this.utisakRepository.save(utisak);
			} else {
				throw new ResponseStatusException(HttpStatus.CONFLICT);
			}
		} else {
			Utisak utisak = new Utisak();
			utisak.setRezervacijaId(rezervacijaId);
			utisak.setKomentar(null);
			utisak.setOcena(ocena);
			utisak.setKomentarOdobren(false);
			return this.utisakRepository.save(utisak);
		}
	}
	
	private boolean rezervacijaJeRealizovana(Long id) {
		ResponseEntity<RezervacijaDTO> odgovor = this.smestajInterface.preuzmiRezervaciju(id);
		if(odgovor.getStatusCode().is2xxSuccessful()) {
			return odgovor.getBody().isRealizovana();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}
