package com.megatravel.agent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.agent.dto.AdresaDTO;
import com.megatravel.agent.model.Adresa;
import com.megatravel.agent.repository.AdresaRepository;

@Service
public class AdresaService {
	
	@Autowired
	private AdresaRepository adresaRepository;
	
	public Adresa kreirajAdresu(AdresaDTO adresaDTO) {
		return this.adresaRepository.save(new Adresa(adresaDTO));
	}
	
}
