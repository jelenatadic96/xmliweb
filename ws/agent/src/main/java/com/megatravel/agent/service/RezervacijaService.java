package com.megatravel.agent.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.dto.RezervacijaDTO;
import com.megatravel.agent.model.Rezervacija;
import com.megatravel.agent.model.SmestajnaJedinica;
import com.megatravel.agent.model.SpojAgentSmestaj;
import com.megatravel.agent.repository.RezervacijaRepository;
import com.megatravel.agent.soap.client.SmestajServiceClient;

@Service
public class RezervacijaService {

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private SmestajnaJedinicaService smestajnaJedinicaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private SmestajServiceClient smestajServiceClient;
	
	public List<Rezervacija> preuzmiRezervacijeSmestaja(Long id) {
		SmestajnaJedinica smestaj = this.smestajnaJedinicaService.preuzmiJednu(id);
		return smestaj.getRezervacije();
	}

	public Rezervacija preuzmiJednu(Long id) {
		Optional<Rezervacija> rezervacija = this.rezervacijaRepository.findById(id);
		if(rezervacija.isPresent()) {
			return rezervacija.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public Rezervacija kreirajPraznuRezervaciju(RezervacijaDTO rezervacijaDTO) {
		Rezervacija rezervacija = new Rezervacija();
		SmestajnaJedinica smestaj = this.smestajnaJedinicaService.preuzmiJednu(rezervacijaDTO.getSmestajnaJedinicaDTO().getId());
		if(!this.smestajnaJedinicaJeSlobodna(smestaj, rezervacijaDTO.getPrviDanRezervacije(), rezervacijaDTO.getPoslednjiDanRezervacije())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
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
	
	public Rezervacija potvrdiRezervaciju(Long id) {
		Rezervacija rezervacija = this.preuzmiJednu(id);
		if(this.dozvoljenoPotvrdjivanje(rezervacija)) {
			rezervacija.setRealizovana(true);
			this.smestajServiceClient.potvrdiRezervaciju(id);
			return this.rezervacijaRepository.save(rezervacija);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	private boolean dozvoljenoPotvrdjivanje(Rezervacija rezervacija) {
		LocalDate trenutniDatum = LocalDate.now();
		return rezervacija.getPoslednjiDanRezervacije().isBefore(trenutniDatum);
	}

	public void dodajZaSinhronizaciju(com.megatravel.agent.soap.generated.RezervacijaDTO rezervacijaDTO) {
		try {
			this.preuzmiJednu(rezervacijaDTO.getId());
		} catch(Exception e) {
			Rezervacija rezervacija = new Rezervacija();
			rezervacija.setPoslednjiDanRezervacije(rezervacijaDTO.getPoslednjiDanRezervacije().toGregorianCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			rezervacija.setPrviDanRezervacije(rezervacijaDTO.getPrviDanRezervacije().toGregorianCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			rezervacija.setRealizovana(rezervacijaDTO.isRealizovana());
			rezervacija.setUkupnaCena(rezervacijaDTO.getUkupnaCena());
			rezervacija.setSmestajnaJedinica(this.smestajnaJedinicaService.preuzmiJednu(rezervacijaDTO.getSmestaj()));
			if(rezervacijaDTO.getKorisnik() != 0)
				rezervacija.setKorisnik(this.korisnikService.preuzmiJednog(rezervacijaDTO.getKorisnik()));
			this.rezervacijaRepository.save(rezervacija);
		}
	}

	public List<Rezervacija> preuzmiRezervacijeKojeJeDodaoAgent(Long id) {
		List<Rezervacija> rezervacije = this.rezervacijaRepository.findAll();
		List<Rezervacija> rezultat = new ArrayList<Rezervacija>();
		for(Rezervacija rezervacija : rezervacije) {
			for(SpojAgentSmestaj spoj : rezervacija.getSmestajnaJedinica().getSpojeviSaAgentima()) {
				if(spoj.getAgent().getId().equals(id)) {
					rezultat.add(rezervacija);
				}
			}
		}
		return rezultat;
	}
	
}
