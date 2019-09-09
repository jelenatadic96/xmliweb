package com.megatravel.agent.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.dto.SmestajnaJedinicaDTO;
import com.megatravel.agent.dto.UpitPretrageDTO;
import com.megatravel.agent.model.Adresa;
import com.megatravel.agent.model.Agent;
import com.megatravel.agent.model.Cenovnik;
import com.megatravel.agent.model.Kategorija;
import com.megatravel.agent.model.Korisnik;
import com.megatravel.agent.model.Rezervacija;
import com.megatravel.agent.model.SmestajnaJedinica;
import com.megatravel.agent.model.SpojAgentSmestaj;
import com.megatravel.agent.model.TipSmestaja;
import com.megatravel.agent.repository.SmestajnaJedinicaRepository;
import com.megatravel.agent.repository.SpojAgentSmestajRepository;
import com.megatravel.agent.soap.client.SmestajServiceClient;
import com.megatravel.agent.soap.generated.SpojAgentSmestajDTO;

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
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private SpojAgentSmestajRepository spojAgentSmestajRepository;
	
	@Autowired
	private SmestajServiceClient client;
	
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
	
	public SmestajnaJedinica kreiraj(SmestajnaJedinicaDTO smestajDTO, Long agentId) {
		smestajDTO.setId(null);
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
		try {
			this.client.dodajSmestajnuJedinicu(smestaj);
		} catch(Exception e) { }
		this.uslugaService.kreirajSpojeve(smestaj, smestajDTO.getUsluge());
		this.kreirajSpojAgentSmestaj(smestaj, this.agentService.preuzmiJednogAgent(agentId));
		return smestaj;
	}

	private void kreirajSpojAgentSmestaj(SmestajnaJedinica smestaj, Agent agent) {
		SpojAgentSmestaj spoj = new SpojAgentSmestaj();
		spoj.setAgent(agent);
		spoj.setSmestajnaJedinica(smestaj);
		this.spojAgentSmestajRepository.save(spoj);
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
	
	public Set<SmestajnaJedinica> preuzmiSmestajeKojeJePosetioKorisnik(Korisnik korisnik) {
		Set<SmestajnaJedinica> rezultat = new HashSet<>();
		for(Rezervacija rezervacija : korisnik.getRezervacije()) {
			rezultat.add(rezervacija.getSmestajnaJedinica());
		}
		return rezultat;
	}
	
	public Set<SmestajnaJedinica> preuzmiSmestajeKojimaUpravljaAgent(Agent agent) {
		Set<SmestajnaJedinica> rezultat = new HashSet<SmestajnaJedinica>();
		for(SpojAgentSmestaj spoj : agent.getSmestaji()) {
			rezultat.add(spoj.getSmestajnaJedinica());
		}
		return rezultat;
	}

	public void dodajZaSinhronizaciju(com.megatravel.agent.soap.generated.SmestajnaJedinicaDTO jedinicaDTO) {
		try {
			this.preuzmiJednu(jedinicaDTO.getId());
		} catch(Exception e) {
			SmestajnaJedinica smestajnaJedinica = new SmestajnaJedinica();
			smestajnaJedinica.setId(jedinicaDTO.getId());
			smestajnaJedinica.setOpis(jedinicaDTO.getOpis());
			smestajnaJedinica.setKapacitet(jedinicaDTO.getKapacitet());
			smestajnaJedinica.setBrojDanaZaOtkazivanje(jedinicaDTO.getBrojDanaZaOtkazivanje());
			smestajnaJedinica.setOcena(jedinicaDTO.getOcena());
			smestajnaJedinica.setKategorija(Kategorija.odOcene(smestajnaJedinica.getOcena()));
			smestajnaJedinica.setPutanjaDoSlike("");
			try {
				smestajnaJedinica.setAdresa(this.adresaService.preuzmiJednu(jedinicaDTO.getAdresa()));
			} catch(ResponseStatusException e2) { }
			smestajnaJedinica.setTip(this.tipSmestajaService.preuzmiJedan(jedinicaDTO.getTip()));
			this.smestajnaJedinicaRepository.save(smestajnaJedinica);
		}
	}

	public void dodajSpojeveZaSinhronizaciju(SpojAgentSmestajDTO spojDTO) {
		if(!this.spojAgentSmestajRepository.findById(spojDTO.getId()).isPresent()) {
			SpojAgentSmestaj spoj = new SpojAgentSmestaj();
			spoj.setAgent(this.agentService.preuzmiJednogAgent(spojDTO.getAgentId()));
			spoj.setSmestajnaJedinica(this.preuzmiJednu(spojDTO.getSmestajId()));
			this.spojAgentSmestajRepository.save(spoj);
		}
	}
	
}