package com.megatravel.smestajservice.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestajservice.dto.UpitPretrageDTO;
import com.megatravel.smestajservice.model.Cenovnik;
import com.megatravel.smestajservice.model.Kategorija;
import com.megatravel.smestajservice.model.Rezervacija;
import com.megatravel.smestajservice.model.SmestajnaJedinica;
import com.megatravel.smestajservice.model.TipSmestaja;
import com.megatravel.smestajservice.repository.SmestajnaJedinicaRepository;
import com.megatravel.smestajservice.soap.dto.SmestajnaJedinicaDTO;

@Service
public class SmestajnaJedinicaService {

	@Autowired
	private SmestajnaJedinicaRepository smestajnaJedinicaRepository;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private TipSmestajaService tipSmestajaService;
	
	public List<SmestajnaJedinica> preuzmiSve() {
		List<SmestajnaJedinica> jedinice = this.smestajnaJedinicaRepository.findAll();
		jedinice.forEach(x -> x.setTrenutnaCenaPoNoci(this.trenutnaCenaSmestajneJedinice(x)));
		return this.smestajnaJedinicaRepository.findAll();
	}

	public SmestajnaJedinica preuzmiJednu(Long id) {
		Optional<SmestajnaJedinica> smestajnaJedinica = this.smestajnaJedinicaRepository.findById(id);
		if(smestajnaJedinica.isPresent()) {
			return smestajnaJedinica.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public SmestajnaJedinica korigujOcenu(Long id, double ocena) {
		SmestajnaJedinica smestajnaJedinica = this.preuzmiJednu(id);
		smestajnaJedinica.setOcena(ocena);
		smestajnaJedinica.setKategorija(Kategorija.odOcene(ocena));
		return this.smestajnaJedinicaRepository.save(smestajnaJedinica);
	}

	public List<SmestajnaJedinica> pretrazi(UpitPretrageDTO upitPretrageDTO) {
		List<SmestajnaJedinica> rezultat = this.preuzmiSve();
		// Filtriranje po kapacitetu
		rezultat.removeIf(x -> x.getKapacitet() < upitPretrageDTO.getBrojOsoba());
		// Filtriranje po oceni
		if(upitPretrageDTO.getKategorija() != null) rezultat.removeIf(x -> x.getOcena() < Kategorija.odKategorije(upitPretrageDTO.getKategorija()));
		// Filtriranje po zauzetosti
		rezultat.removeIf(x -> !this.rezervacijaService.smestajnaJedinicaJeSlobodna(x, upitPretrageDTO.getPrviDan(), upitPretrageDTO.getPoslednjiDan()));
		// Filtriranje po tipu smestaja
		if(upitPretrageDTO.getTipSmestajaDTO() != null && upitPretrageDTO.getTipSmestajaDTO().getId() != 0) rezultat.removeIf(x -> !x.getTip().getId().equals(upitPretrageDTO.getTipSmestajaDTO().getId()));
		// Filtriranje po adresi
		rezultat.removeIf(x -> !(x.getAdresa().getZemlja().equals(upitPretrageDTO.getZemlja()) && 
				x.getAdresa().getGrad().equals(upitPretrageDTO.getGrad())));
		// Nije uradjeno filtriranje po udaljenosti
		return rezultat;
	}
	
	public Set<Long> preuzmiSmestajeKojeJePosetioKorisnik(Long korisnikId) {
		Set<Long> rezultat = new HashSet<>();
		for(Rezervacija rezervacija : this.rezervacijaService.preuzmiSveNaKojimaJeKorisnik(korisnikId)) {
			rezultat.add(rezervacija.getSmestajnaJedinica().getId());
		}
		return rezultat;
	}
	
	private double trenutnaCenaSmestajneJedinice(SmestajnaJedinica smestajnaJedinica) {
		LocalDate trenutniDatum = LocalDate.now();
		for(Cenovnik cenovnik : smestajnaJedinica.getCenovnici()) {
			if(cenovnik.getPrviDanVazenja().isBefore(trenutniDatum) && cenovnik.getPoslednjiDanVazenja().isAfter(trenutniDatum)) {
				return cenovnik.getCenaPoNoci();
			}
		}
		return 0;
	}

	public SmestajnaJedinica kreiraj(SmestajnaJedinicaDTO smestajDTO, Long agentId) {
		SmestajnaJedinica smestaj = new SmestajnaJedinica(smestajDTO);
		smestaj.setOcena(0.0);
		smestaj.setKategorija(Kategorija.NEKATEGORISAN);
		TipSmestaja tip = this.tipSmestajaService.preuzmiJedan(smestajDTO.getTip());
		smestaj.setTip(tip);
		smestaj.setPutanjaDoSlike("");
		this.smestajnaJedinicaRepository.save(smestaj);
		List<Cenovnik> cenovnici = this.cenovnikService.kreirajCenovnike(smestaj, smestajDTO.getCenovnici());
		smestaj.setCenovnici(cenovnici);
		return smestaj;
	}
	
}
