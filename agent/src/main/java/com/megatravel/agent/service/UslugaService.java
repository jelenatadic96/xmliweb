package com.megatravel.agent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agent.dto.UslugaDTO;
import com.megatravel.agent.model.SmestajnaJedinica;
import com.megatravel.agent.model.SpojUslugaJedinica;
import com.megatravel.agent.model.Usluga;
import com.megatravel.agent.repository.SpojUslugaJedinicaRepository;
import com.megatravel.agent.repository.UslugaRepository;

@Service
public class UslugaService {

	@Autowired
	private UslugaRepository uslugaRepository;
	
	@Autowired
	private SpojUslugaJedinicaRepository spojUslugaJedinicaRepository;
	
	private Usluga preuzmiJednu(Long id) {
		Optional<Usluga> usluga = this.uslugaRepository.findById(id);
		if(usluga.isPresent()) {
			return usluga.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public void kreirajSpojeve(SmestajnaJedinica smestaj, List<UslugaDTO> uslugeDTO) {
		for(UslugaDTO uslugaDTO : uslugeDTO) {
			Usluga usluga = this.preuzmiJednu(uslugaDTO.getId());
			SpojUslugaJedinica spoj = new SpojUslugaJedinica();
			spoj.setSmestajnaJedinica(smestaj);
			spoj.setUsluga(usluga);
			this.spojUslugaJedinicaRepository.save(spoj);
		}
	}
	
}
