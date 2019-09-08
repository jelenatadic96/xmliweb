package com.megatravel.agent.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public void uloguj(String mejl, String lozinka) {
		System.out.println("Pokusan login sa: " + mejl + " : " + lozinka);
	}
	
}
