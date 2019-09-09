package com.megatravel.korisniciservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.korisniciservice.repository.AdministratorRepository;
import com.megatravel.korisniciservice.repository.AgentRepository;
import com.megatravel.korisniciservice.repository.KorisnikRepository;

@Service
public class LoginService {

	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public void uloguj(String mejl, String lozinka) {
		System.out.println("Pokusan login sa: " + mejl + " : " + lozinka);
	}
	
}
