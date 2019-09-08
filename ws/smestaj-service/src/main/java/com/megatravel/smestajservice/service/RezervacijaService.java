package com.megatravel.smestajservice.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestajservice.dto.KorisnikDTO;
import com.megatravel.smestajservice.dto.RezervacijaDTO;
import com.megatravel.smestajservice.interfaces.KorisnikInterface;
import com.megatravel.smestajservice.model.Rezervacija;
import com.megatravel.smestajservice.model.SmestajnaJedinica;
import com.megatravel.smestajservice.repository.RezervacijaRepository;

@Service
public class RezervacijaService {

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private SmestajnaJedinicaService smestajnaJedinicaService;
	
	@Autowired
	private KorisnikInterface korisnikInterface;
	
	public List<Rezervacija> preuzmiIstoriju(Long id) {
		List<Rezervacija> rezervacije = this.rezervacijaRepository.findAllByKorisnikId(id);
		LocalDate trenutniDatum = LocalDate.now();
		rezervacije.removeIf(x -> !x.getPoslednjiDanRezervacije().isBefore(trenutniDatum));
		return rezervacije;
	}

	public List<Rezervacija> preuzmiAktivne(Long id) {
		List<Rezervacija> rezervacije = this.rezervacijaRepository.findAllByKorisnikId(id);
		LocalDate trenutniDatum = LocalDate.now();
		rezervacije.removeIf(x -> x.getPoslednjiDanRezervacije().isBefore(trenutniDatum));
		return rezervacije;
	}

	private Rezervacija preuzmiJednu(Long id) {
		Optional<Rezervacija> rezervacija = this.rezervacijaRepository.findById(id);
		if(rezervacija.isPresent()) {
			return rezervacija.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public Rezervacija rezervisiSmestajnuJedinicu(Long id, RezervacijaDTO rezervacijaDTO) {
		Rezervacija rezervacija = new Rezervacija();
		SmestajnaJedinica smestaj = this.smestajnaJedinicaService.preuzmiJednu(rezervacijaDTO.getSmestajnaJedinicaDTO().getId());
		if(!this.smestajnaJedinicaJeSlobodna(smestaj, rezervacijaDTO.getPrviDanRezervacije(), rezervacijaDTO.getPoslednjiDanRezervacije())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		if(!this.korisnikPostoji(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		rezervacija.setKorisnikId(id);
		rezervacija.setPrviDanRezervacije(rezervacijaDTO.getPrviDanRezervacije());
		rezervacija.setPoslednjiDanRezervacije(rezervacijaDTO.getPoslednjiDanRezervacije());
		rezervacija.setRealizovana(false);
		rezervacija.setSmestajnaJedinica(smestaj);
		rezervacija.setUkupnaCena(this.cenovnikService.izracunajCenu(smestaj, rezervacija.getPrviDanRezervacije(), rezervacija.getPoslednjiDanRezervacije()));
		return this.rezervacijaRepository.save(rezervacija);
	}
	
	public boolean smestajnaJedinicaJeSlobodna(SmestajnaJedinica smestaj, LocalDate prviDatum, LocalDate poslednjiDatum) {
		for(Rezervacija rezervacija : smestaj.getRezervacije()) {
			LocalDate prviZauzet = rezervacija.getPrviDanRezervacije();
			LocalDate poslednjiZauzet = rezervacija.getPoslednjiDanRezervacije();
			if(prviDatum.isAfter(prviZauzet) && prviDatum.isBefore(poslednjiZauzet)) {
				return false;
			} else if(poslednjiDatum.isAfter(prviZauzet) && poslednjiDatum.isBefore(poslednjiZauzet)) {
				return false;
			} else if(poslednjiDatum.isAfter(poslednjiZauzet) && prviDatum.isBefore(prviZauzet)) {
				return false;
			} else if(prviDatum.isEqual(prviZauzet) || prviDatum.isEqual(poslednjiZauzet)) {
				return false;
			} else if(poslednjiDatum.isEqual(prviZauzet) || poslednjiDatum.isEqual(poslednjiZauzet)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean korisnikPostoji(Long id) {
		ResponseEntity<KorisnikDTO> odgovor = korisnikInterface.preuzmiJednogKorisnika(id);
		return odgovor.getStatusCode().is2xxSuccessful();
	}
	
	public void otkazi(Long id) {
		Rezervacija rezervacija = this.preuzmiJednu(id);
		if(this.dozvoljenoOtkazivanje(rezervacija)) {
			this.rezervacijaRepository.delete(rezervacija);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}
	
	private boolean dozvoljenoOtkazivanje(Rezervacija rezervacija) {
		LocalDate trenutniDatum = LocalDate.now();
		int brojDana = rezervacija.getSmestajnaJedinica().getBrojDanaZaOtkazivanje();
		return ChronoUnit.DAYS.between(trenutniDatum, rezervacija.getPrviDanRezervacije()) >= brojDana + 1;
	}
	
}
