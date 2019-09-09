package com.megatravel.agent.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.model.Agent;
import com.megatravel.agent.model.Korisnik;
import com.megatravel.agent.model.Poruka;
import com.megatravel.agent.model.SmestajnaJedinica;
import com.megatravel.agent.repository.PorukaRepository;
import com.megatravel.agent.soap.client.PorukeServiceClient;
import com.megatravel.agent.soap.generated.PorukaDTO;
import com.megatravel.agent.soap.generated.PosaljiPorukuRequest;

@Service
public class PorukaService {

	@Autowired
	private PorukaRepository porukaRepository;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private SmestajnaJedinicaService smestajnaJedinicaService;
	
	@Autowired
	private PorukeServiceClient porukeServiceClient;
	
	public List<Poruka> preuzmiPorukeAgenta(Long agentId) {
		List<Poruka> poruke = this.porukaRepository.findAll();
		poruke.removeIf(x -> !x.getAgent().getId().equals(agentId));
		return poruke;
	}

	public List<Poruka> preuzmiPorukeAgentaSaKorisnikom(Long agentId, Long korisnikId) {
		List<Poruka> poruke = this.porukaRepository.findAll();
		poruke.removeIf(x -> !(x.getAgent().getId().equals(agentId) && x.getKorisnik().getId().equals(korisnikId)));
		return poruke;
	}
	
	public Poruka posaljiPoruku(Poruka poruka, Long agentId, Long korisnikId) {
		Agent agent = this.agentService.preuzmiJednogAgent(agentId);
		Korisnik korisnik = this.korisnikService.preuzmiJednog(korisnikId);
		if(this.dozvoljenaKomunikacija(agent, korisnik)) {
			poruka.setAgent(this.agentService.preuzmiJednogAgent(agentId));
			poruka.setKorisnik(this.korisnikService.preuzmiJednog(korisnikId));
			this.porukeServiceClient.posaljiPoruku(this.kreirajZahtev(poruka, agentId, korisnikId));
			return this.porukaRepository.save(poruka);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	private PosaljiPorukuRequest kreirajZahtev(Poruka poruka, Long agentId, Long korisnikId) {
		PosaljiPorukuRequest zahtev = new PosaljiPorukuRequest();
		zahtev.getPoruka().setSadrzaj(poruka.getSadrzaj());
		zahtev.getPoruka().setAgentId(agentId);
		zahtev.getPoruka().setKorisnikId(korisnikId);
		return zahtev;
	}
	
	private boolean dozvoljenaKomunikacija(Agent agent, Korisnik korisnik) {
		Set<SmestajnaJedinica> poseceniSmestaji = this.smestajnaJedinicaService.preuzmiSmestajeKojeJePosetioKorisnik(korisnik);
		Set<SmestajnaJedinica> smestajiKojimaAgentUpravlja = this.smestajnaJedinicaService.preuzmiSmestajeKojimaUpravljaAgent(agent);
		poseceniSmestaji.retainAll(smestajiKojimaAgentUpravlja);
		return !poseceniSmestaji.isEmpty();
	}
	
	public List<Korisnik> preuzmiKorisnikeSaKojimaCetujeAgent(Long id) {
		List<Poruka> poruke = this.preuzmiPorukeAgenta(id);
		Set<Long> identifikatori = new HashSet<>();
		for(Poruka poruka : poruke) {
			identifikatori.add(poruka.getKorisnik().getId());
		}
		List<Korisnik> rezultat = new ArrayList<>();
		for(Long identifikator : identifikatori) {
			rezultat.add(this.korisnikService.preuzmiJednog(identifikator));
		}
		return rezultat;
	}

	public List<Poruka> preuzmiSvePoruke() {
		return this.porukaRepository.findAll();
	}

	public void dodajPorukuZaSinhronizaciju(PorukaDTO porukaDTO) {
		if(this.porukaRepository.findById(porukaDTO.getId()).isPresent()) return;
		Agent agent = this.agentService.preuzmiJednogAgent(porukaDTO.getAgentId());
		Korisnik korisnik = this.korisnikService.preuzmiJednog(porukaDTO.getKorisnikId());
		Poruka poruka = new Poruka();
		poruka.setAgent(agent);
		poruka.setKorisnik(korisnik);
		poruka.setSadrzaj(porukaDTO.getSadrzaj());
		poruka.setVreme(porukaDTO.getVreme().toGregorianCalendar().getTime());
		this.porukaRepository.save(poruka);
	}
	
}
