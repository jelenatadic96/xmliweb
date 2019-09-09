package com.megatravel.agent.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.model.Agent;
import com.megatravel.agent.repository.AgentRepository;
import com.megatravel.agent.soap.generated.AgentDTO;

@Service
public class AgentService {

	@Autowired
	private AgentRepository agentRepository;

	public Agent preuzmiJednogAgent(Long id) {
		Optional<Agent> agent = this.agentRepository.findById(id);
		if(agent.isPresent()) {
			return agent.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public Agent preuzmiPoMejlu(String email) {
		Optional<Agent> agent = this.agentRepository.findByMejl(email);
		if(agent.isPresent()) {
			return agent.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public void dodajAgentaZaSinhronizaciju(AgentDTO agentDTO) {
		try {
			preuzmiJednogAgent(agentDTO.getId());
		} catch(Exception e) {
			Agent agent = new Agent();
			agent.setId(agentDTO.getId());
			agent.setIme(agentDTO.getIme());
			agent.setPrezime(agentDTO.getPrezime());
			agent.setMejl(agentDTO.getMejl());
			agent.setLozinka(agentDTO.getLozinka());
			agent.setPoslovniMaticniBroj(agentDTO.getPoslovniMaticniBroj());
			this.agentRepository.save(agent);
		}
	}
	
}
