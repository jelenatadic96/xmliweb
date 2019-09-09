package com.megatravel.smestajservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestajservice.dto.UpitPretrageDTO;
import com.megatravel.smestajservice.model.Kategorija;
import com.megatravel.smestajservice.model.SmestajnaJedinica;
import com.megatravel.smestajservice.repository.SmestajnaJedinicaRepository;

@Service
public class SmestajnaJedinicaService {

	@Autowired
	private SmestajnaJedinicaRepository smestajnaJedinicaRepository;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	public List<SmestajnaJedinica> preuzmiSve() {
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
		if(upitPretrageDTO.getTipSmestajaDTO() != null) rezultat.removeIf(x -> !x.getTip().getId().equals(upitPretrageDTO.getTipSmestajaDTO().getId()));
		// Filtriranje po adresi
		rezultat.removeIf(x -> !(x.getAdresa().getZemlja().equals(upitPretrageDTO.getZemlja()) && 
				x.getAdresa().getGrad().equals(upitPretrageDTO.getGrad())));
		// Nije uradjeno filtriranje po udaljenosti
		return rezultat;
	}
	
}
