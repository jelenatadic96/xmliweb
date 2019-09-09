package com.megatravel.korisniciservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.korisniciservice.jwt.JwtTokenUtils;
import com.megatravel.korisniciservice.model.Korisnik;
import com.megatravel.korisniciservice.model.StatusKorisnika;
import com.megatravel.korisniciservice.repository.KorisnikRepository;
import com.megatravel.korisniciservice.security.AllowedRoles;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	private EmailCheckService emailCheckService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	public List<Korisnik> preuzmiSve() {
		return this.korisnikRepository.findAll();
	}
	
	public Korisnik preuzmiJednog(Long id) {
		Optional<Korisnik> korisnik = this.korisnikRepository.findById(id);
		if(korisnik.isPresent()) {
			return korisnik.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public Korisnik registruj(Korisnik korisnik) {
		korisnik.setStatusKorisnika(StatusKorisnika.AKTIVAN);
		korisnik.setRole(AllowedRoles.ROLE_OBICAN);
		if(this.emailCheckService.mejlJeSlobodan(korisnik.getMejl())) {
			return this.korisnikRepository.save(korisnik);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	public Korisnik promeniStatusKorisnika(Long id, StatusKorisnika statusKorisnika) {
		Korisnik korisnik = this.preuzmiJednog(id);
		if(korisnik.getStatusKorisnika().equals(StatusKorisnika.UKLONJEN)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		korisnik.setStatusKorisnika(statusKorisnika);
		return this.korisnikRepository.save(korisnik);
	}
	
	public Korisnik preuzmiPoMejlu(String mejl) {
		Optional<Korisnik> korisnik = this.korisnikRepository.findByMejl(mejl);
		if(korisnik.isPresent()) {
			return korisnik.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public String uloguj(String mejl, String lozinka) {
		Korisnik korisnik = this.preuzmiPoMejlu(mejl);
		if(korisnik.getLozinka().equals(lozinka) && korisnik.getStatusKorisnika().equals(StatusKorisnika.AKTIVAN)) {
			return jwtTokenUtils.createToken(mejl, korisnik.getRole());
		} else {
			throw new ResponseStatusException(HttpStatus.LOCKED);
		}
	}
	
}
