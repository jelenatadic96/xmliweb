package com.megatravel.korisniciservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.korisniciservice.dto.AdresaDTO;
import com.megatravel.korisniciservice.model.Agent;
import com.megatravel.korisniciservice.model.SpojAgentSmestaj;
import com.megatravel.korisniciservice.repository.AgentRepository;

@Service
public class AgentService {

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private EmailCheckService emailCheckService;
	
	public List<Agent> preuzmiSveAgente() {
		return this.agentRepository.findAll();
	}
	
	public Agent preuzmiJednogAgent(Long id) {
		Optional<Agent> agent = this.agentRepository.findById(id);
		if(agent.isPresent()) {
			return agent.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public Agent kreiraj(Agent agent, AdresaDTO adresaDTO) {
		if(this.emailCheckService.mejlJeSlobodan(agent.getMejl())) {
			return this.agentRepository.save(agent);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	public Set<Long> preuzmiUpravljaneSmestaje(Long agentId) {
		Agent agent = this.preuzmiJednogAgent(agentId);
		Set<Long> rezultat = new HashSet<>();
		for(SpojAgentSmestaj spoj : agent.getSmestaji()) {
			rezultat.add(spoj.getSmestajId());
		}
		return rezultat;
	}

	public Agent ulogujAgenta(String mejl, String lozinka) {
		Optional<Agent> agent = this.agentRepository.findByMejl(mejl);
		if(agent.isPresent()) {
			if(agent.get().getLozinka().equals(lozinka)) {
				return agent.get();
			} else {
				throw new ResponseStatusException(HttpStatus.LOCKED);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}
