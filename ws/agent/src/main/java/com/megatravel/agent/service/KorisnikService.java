package com.megatravel.agent.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.model.Korisnik;
import com.megatravel.agent.model.StatusKorisnika;
import com.megatravel.agent.repository.KorisnikRepository;
import com.megatravel.agent.soap.generated.KorisnikDTO;

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
	
	public Korisnik findByMejl(String email) {
		Optional<Korisnik> temp = korisnikRepository.findByMejl(email);
		if(temp.isPresent()) {
			return temp.get();
		}
		else {
			return null;
		}
	}
	
	
	public boolean signin(String email, String password) throws AuthenticationException, IOException {
		Korisnik user = korisnikRepository.findByMejl(email).get();
		try {
			if(!user.getLozinka().equals(password)) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return true;
	}

	public void dodajKorisnikZaSinhronizaciju(KorisnikDTO korisnikDTO) {
		try {
			preuzmiJednog(korisnikDTO.getId());
		} catch(Exception e) {
			Korisnik korisnik = new Korisnik();
			korisnik.setId(korisnikDTO.getId());
			korisnik.setIme(korisnikDTO.getIme());
			korisnik.setPrezime(korisnikDTO.getPrezime());
			korisnik.setMejl(korisnikDTO.getMejl());
			korisnik.setLozinka(korisnikDTO.getLozinka());
			korisnik.setStatusKorisnika(StatusKorisnika.AKTIVAN);
			this.korisnikRepository.save(korisnik);
		}
	}
	
}
