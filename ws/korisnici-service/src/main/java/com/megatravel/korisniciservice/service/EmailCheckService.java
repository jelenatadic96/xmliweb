package com.megatravel.korisniciservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.korisniciservice.repository.AdministratorRepository;
import com.megatravel.korisniciservice.repository.AgentRepository;
import com.megatravel.korisniciservice.repository.KorisnikRepository;

@Service
public class EmailCheckService {

	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public boolean mejlJeSlobodan(String mejl) {
		return !this.korisnikPostoji(mejl) && !this.agentPostoji(mejl) && !this.administratorPostoji(mejl);
	}
	
	private boolean korisnikPostoji(String mejl) {
		return this.korisnikRepository.findByMejl(mejl).isPresent();
	}
	
	private boolean agentPostoji(String mejl) {
		return this.agentRepository.findByMejl(mejl).isPresent();
	}
	
	private boolean administratorPostoji(String mejl) {
		return this.administratorRepository.findByMejl(mejl).isPresent();
	}
	
}
