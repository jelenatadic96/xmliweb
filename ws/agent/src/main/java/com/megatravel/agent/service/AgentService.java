package com.megatravel.agent.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.model.Agent;
import com.megatravel.agent.repository.AgentRepository;

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
	
}
