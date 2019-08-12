package com.megatravel.smestajservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestajservice.model.TipSmestaja;
import com.megatravel.smestajservice.repository.TipSmestajaRepository;

@Service
public class TipSmestajaService {

	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;
	
	public TipSmestaja kreiraj(TipSmestaja tipSmestaja) {
		return this.tipSmestajaRepository.save(tipSmestaja);
	}

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

	public TipSmestaja azuriraj(Long id, TipSmestaja noviTip) {
		TipSmestaja stariTip = this.preuzmiJedan(id);
		stariTip.prekopiraj(noviTip);
		return this.tipSmestajaRepository.save(stariTip);
	}

	public void obrisi(Long id) {
		TipSmestaja tip = this.preuzmiJedan(id);
		if(this.dozvoljenoBrisanje(tip)) {
			this.tipSmestajaRepository.delete(tip);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}
	
	private boolean dozvoljenoBrisanje(TipSmestaja tip) {
		return tip.getSmestajneJedinice().isEmpty();
	}
	
}
