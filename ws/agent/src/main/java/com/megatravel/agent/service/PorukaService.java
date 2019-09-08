package com.megatravel.agent.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.agent.model.Korisnik;
import com.megatravel.agent.model.Poruka;
import com.megatravel.agent.repository.PorukaRepository;

@Service
public class PorukaService {

	@Autowired
	private PorukaRepository porukaRepository;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	public List<Poruka> preuzmiPorukeAgenta(Long agentId) {
		List<Poruka> poruke = this.porukaRepository.findAll();
		poruke.removeIf(x -> x.getAgent().getId().equals(agentId));
		return poruke;
	}

	public List<Poruka> preuzmiPorukeAgentaSaKorisnikom(Long agentId, Long korisnikId) {
		List<Poruka> poruke = this.porukaRepository.findAll();
		poruke.removeIf(x -> x.getAgent().getId().equals(agentId) && x.getKorisnik().getId().equals(korisnikId));
		return poruke;
	}
	
	public Poruka posaljiPoruku(Poruka poruka, Long agentId, Long korisnikId) {
		// TODO : Proveriti da li je dozvoljena komunikacija izmedju agenta i korisnika. Za to je neophodno
		// da korisnik ima rezervaciju u smestajnoj jedinici za koju je agent povezan.
		poruka.setAgent(this.agentService.preuzmiJednogAgent(agentId));
		poruka.setKorisnik(this.korisnikService.preuzmiJednog(korisnikId));
		return this.porukaRepository.save(poruka);
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
	
}
