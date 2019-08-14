package com.megatravel.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.agent.model.Utisak;
import com.megatravel.agent.repository.UtisakRepository;

@Service
public class UtisakService {

	@Autowired
	private UtisakRepository utisakRepository;
	
	public List<Utisak> preuzmiSveUtiske() {
		return this.utisakRepository.findAll();
	}
	
}
