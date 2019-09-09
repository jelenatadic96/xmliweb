package com.megatravel.porukeservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.porukeservice.dto.AgentDTO;
import com.megatravel.porukeservice.dto.KorisnikDTO;
import com.megatravel.porukeservice.interfaces.KorisnikInterface;
import com.megatravel.porukeservice.interfaces.SmestajInterface;
import com.megatravel.porukeservice.model.Poruka;
import com.megatravel.porukeservice.repository.PorukaRepository;

@Service
public class PorukaService {

	@Autowired
	private PorukaRepository porukaRepository;

	@Autowired
	private KorisnikInterface korisnikInterface;
	
	@Autowired
	private SmestajInterface smestajInterface;
	
	public List<Poruka> preuzmiPorukeAgenta(Long id) {
		return this.porukaRepository.findAllByAgentId(id);
	}

	public List<Poruka> preuzmiPorukeAgentaSaKorisnikom(Long id, Long korisnikId) {
		return this.porukaRepository.findAllByAgentIdAndKorisnikId(id, korisnikId);
	}
	
	public List<Poruka> preuzmiPorukeKorisnika(Long id) {
		return this.porukaRepository.findAllByKorisnikId(id);
	}

	public List<Poruka> preuzmiPorukeKorisnikaSaAgentom(Long id, Long agentId) {
		return this.porukaRepository.findAllByAgentIdAndKorisnikId(agentId, id);
	}
	
	public Poruka posaljiPoruku(Poruka poruka, Long agentId, Long korisnikId) {
		if(this.dozvoljenaKomunikacija(agentId, korisnikId)) {
			poruka.setAgentId(agentId);
			poruka.setKorisnikId(korisnikId);
			return this.porukaRepository.save(poruka);	
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	private boolean dozvoljenaKomunikacija(Long agentId, Long korisnikId) {
		ResponseEntity<Set<Long>> odgovorPoseceniSmestaji = this.smestajInterface.poseceniSmestaji(korisnikId);
		ResponseEntity<Set<Long>> odgovorUpravljaniSmestaji = this.korisnikInterface.preuzmiUpravljaneSmestaje(agentId);
		if(odgovorPoseceniSmestaji.getStatusCode().is2xxSuccessful() &&
				odgovorUpravljaniSmestaji.getStatusCode().is2xxSuccessful()) {
			Set<Long> poseceniSmestaji = odgovorPoseceniSmestaji.getBody();
			Set<Long> smestajiKojimaAgentUpravlja = odgovorUpravljaniSmestaji.getBody();
			poseceniSmestaji.retainAll(smestajiKojimaAgentUpravlja);
			return !poseceniSmestaji.isEmpty();
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public List<KorisnikDTO> preuzmiKorisnikeSaKojimaCetujeAgent(Long id) {
		List<Poruka> poruke = this.preuzmiPorukeAgenta(id);
		Set<Long> korisnici = new HashSet<Long>();
		for(Poruka poruka : poruke) {
			korisnici.add(poruka.getKorisnikId());
		}
		List<KorisnikDTO> rezultat = new ArrayList<KorisnikDTO>();
		for(Long korisnik : korisnici) {
			ResponseEntity<KorisnikDTO> odgovor = korisnikInterface.preuzmiJednogKorisnika(korisnik);
			if(odgovor.getStatusCode().is2xxSuccessful()) {
				rezultat.add(odgovor.getBody());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "korisnici-service nije u funkciji!");
			}
		}
		return rezultat;
	}
	
	public List<AgentDTO> preuzmiAgenteSaKojimaCetujeKorisnik(Long id) {
		List<Poruka> poruke = this.preuzmiPorukeKorisnika(id);
		Set<Long> agenti = new HashSet<Long>();
		for(Poruka poruka : poruke) {
			agenti.add(poruka.getAgentId());
		}
		List<AgentDTO> rezultat = new ArrayList<AgentDTO>();
		for(Long agent : agenti) {
			ResponseEntity<AgentDTO> odgovor = korisnikInterface.preuzmiJednogAgenta(agent);
			if(odgovor.getStatusCode().is2xxSuccessful()) {
				rezultat.add(odgovor.getBody());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "korisnici-service nije u funkciji!");
			}
		}
		return rezultat;
	}

	public List<Poruka> preuzmiSvePoruke() {
		return this.porukaRepository.findAll();
	}
	
}
