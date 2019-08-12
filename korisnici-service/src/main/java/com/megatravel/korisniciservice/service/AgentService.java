package com.megatravel.korisniciservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.korisniciservice.model.Agent;
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
	
	public Agent kreiraj(Agent agent) {
		if(this.emailCheckService.mejlJeSlobodan(agent.getMejl())) {
			return this.agentRepository.save(agent);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}
	
}
