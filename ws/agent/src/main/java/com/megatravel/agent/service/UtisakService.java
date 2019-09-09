package com.megatravel.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.agent.model.Utisak;
import com.megatravel.agent.repository.UtisakRepository;
import com.megatravel.agent.soap.generated.UtisakDTO;

@Service
public class UtisakService {

	@Autowired
	private UtisakRepository utisakRepository;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	public List<Utisak> preuzmiSveUtiske() {
		return this.utisakRepository.findAll();
	}

	public void dodajZaSinhronizaciju(UtisakDTO utisakDTO) {
		if(!this.utisakRepository.findById(utisakDTO.getId()).isPresent()) {
			Utisak utisak = new Utisak();
			utisak.setKomentar(utisakDTO.getKomentar());
			utisak.setKomentarOdobren(utisakDTO.isKomentarOdobren());
			utisak.setOcena(utisakDTO.getOcena());
			utisak.setRezervacija(this.rezervacijaService.preuzmiJednu(utisakDTO.getRezervacijaId()));
			this.utisakRepository.save(utisak);
		}
	}
	
}
