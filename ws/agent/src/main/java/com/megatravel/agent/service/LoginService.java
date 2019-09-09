package com.megatravel.agent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.model.Agent;
import com.megatravel.agent.soap.client.KorisniciServiceClient;
import com.megatravel.agent.soap.generated.GetAgentLoginRequest;

@Service
public class LoginService {

	@Autowired
	private AgentService agentService;

	@Autowired
	private KorisniciServiceClient client;
	
	public Agent uloguj(String mejl, String lozinka) {
		Agent agent = this.agentService.preuzmiPoMejlu(mejl);
		if(this.proveriKredencijale(mejl, lozinka)) {
			GetAgentLoginRequest zahtevZaLogin = new GetAgentLoginRequest();
			zahtevZaLogin.setMejl(mejl);
			zahtevZaLogin.setLozinka(lozinka);
			if(this.client.uloguj(zahtevZaLogin)) {
				return agent;
			} else {
				throw new ResponseStatusException(HttpStatus.LOCKED);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.LOCKED);
		}
	}

	public boolean proveriKredencijale(String mejl, String lozinka) {
		Agent agent = this.agentService.preuzmiPoMejlu(mejl);
		return agent.getLozinka().equals(lozinka);
	}

}
