package com.megatravel.agent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.model.TipSmestaja;
import com.megatravel.agent.repository.TipSmestajaRepository;
import com.megatravel.agent.soap.generated.TipSmestajaDTO;

@Service
public class TipSmestajaService {

	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;
	
	public TipSmestaja preuzmiJedan(Long id) {
		Optional<TipSmestaja> tipSmestaja = this.tipSmestajaRepository.findById(id);
		if(tipSmestaja.isPresent()) {
			return tipSmestaja.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public List<TipSmestaja> preuzmiSve() {
		return this.tipSmestajaRepository.findAll();
	}

	public void dodajZaSinhronizaciju(TipSmestajaDTO tipDTO) {
		try {
			this.preuzmiJedan(tipDTO.getId());
		} catch(Exception e) {
			TipSmestaja tip = new TipSmestaja();
			tip.setNaziv(tipDTO.getNaziv());
			tip.setOpis(tipDTO.getOpis());
			this.tipSmestajaRepository.save(tip);
		}
	}
	
}
