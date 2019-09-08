package com.megatravel.agent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.dto.SmestajnaJedinicaDTO;
import com.megatravel.agent.dto.UpitPretrageDTO;
import com.megatravel.agent.model.Adresa;
import com.megatravel.agent.model.Cenovnik;
import com.megatravel.agent.model.Kategorija;
import com.megatravel.agent.model.SmestajnaJedinica;
import com.megatravel.agent.model.TipSmestaja;
import com.megatravel.agent.repository.SmestajnaJedinicaRepository;

@Service
public class SmestajnaJedinicaService {

	@Autowired
	private SmestajnaJedinicaRepository smestajnaJedinicaRepository;
	
	@Autowired
	private AdresaService adresaService;
	
	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private TipSmestajaService tipSmestajaService;
	
	@Autowired
	private UslugaService uslugaService;
	
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
	
	public SmestajnaJedinica kreiraj(SmestajnaJedinicaDTO smestajDTO) {
		SmestajnaJedinica smestaj = new SmestajnaJedinica(smestajDTO);
		smestaj.setOcena(0.0);
		smestaj.setKategorija(Kategorija.NEKATEGORISAN);
		Adresa adresa = this.adresaService.kreirajAdresu(smestajDTO.getAdresaDTO());
		TipSmestaja tip = this.tipSmestajaService.preuzmiJedan(smestajDTO.getTipDTO().getId());
		smestaj.setTip(tip);
		smestaj.setAdresa(adresa);
		smestaj.setPutanjaDoSlike("");
		this.smestajnaJedinicaRepository.save(smestaj);
		List<Cenovnik> cenovnici = this.cenovnikService.kreirajCenovnike(smestaj, smestajDTO.getCenovnici());
		smestaj.setCenovnici(cenovnici);
		this.uslugaService.kreirajSpojeve(smestaj, smestajDTO.getUsluge());
		return smestaj;
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