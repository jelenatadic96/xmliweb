package com.megatravel.agent.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

	public Adresa preuzmiJednu(Long id) {
		Optional<Adresa> adresa = this.adresaRepository.findById(id);
		if(adresa.isPresent()) {
			return adresa.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public void dodajZaSinhronizaciju(com.megatravel.agent.soap.generated.AdresaDTO adresaDTO) {
		if(!this.adresaRepository.findById(adresaDTO.getId()).isPresent()) {
			Adresa adresa = new Adresa();
			adresa.setZemlja(adresaDTO.getZemlja());
			adresa.setGrad(adresaDTO.getGrad());
			adresa.setGeografskaDuzina(adresaDTO.getGeografskaDuzina());
			adresa.setGeografskaSirina(adresaDTO.getGeografskaSirina());
			this.adresaRepository.save(adresa);
		}
	}
	
}
