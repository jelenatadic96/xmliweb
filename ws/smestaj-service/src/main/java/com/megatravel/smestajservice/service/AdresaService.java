package com.megatravel.smestajservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.smestajservice.model.Adresa;
import com.megatravel.smestajservice.repository.AdresaRepository;

@Service
public class AdresaService {

	@Autowired
	private AdresaRepository adresaRepository;
	
	public List<Adresa> preuzmiSve() {
		return this.adresaRepository.findAll();
	}
	
	public Adresa kreiraj(Adresa adresa) {
		return this.adresaRepository.save(adresa);
	}
	
}
