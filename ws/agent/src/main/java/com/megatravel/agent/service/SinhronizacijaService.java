package com.megatravel.agent.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.agent.soap.client.KorisniciServiceClient;
import com.megatravel.agent.soap.client.PorukeServiceClient;
import com.megatravel.agent.soap.client.SmestajServiceClient;
import com.megatravel.agent.soap.client.UtisciServiceClient;
import com.megatravel.agent.soap.generated.GetStanjeBazePodatakaPorukaRequest;
import com.megatravel.agent.soap.generated.GetStanjeBazePodatakaUtisakaRequest;
import com.megatravel.agent.soap.generated.GetSveAdreseRequest;
import com.megatravel.agent.soap.generated.GetSveRezervacijeRequest;
import com.megatravel.agent.soap.generated.GetSveSmestajneJediniceRequest;
import com.megatravel.agent.soap.generated.GetSveUslugeRequest;
import com.megatravel.agent.soap.generated.GetSviAgentiRequest;
import com.megatravel.agent.soap.generated.GetSviCenovniciRequest;
import com.megatravel.agent.soap.generated.GetSviKorisniciRequest;
import com.megatravel.agent.soap.generated.GetSviSpojeviAgentSmestajRequest;
import com.megatravel.agent.soap.generated.GetSviTipoviRequest;

@Service
public class SinhronizacijaService {

	@Autowired
	private UtisciServiceClient utisciServiceClient;
	
	@Autowired
	private PorukeServiceClient porukeServiceClient;
	
	@Autowired
	private KorisniciServiceClient korisniciServiceClient;
	
	@Autowired
	private SmestajServiceClient smestajServiceClient;
	
	@PostConstruct
	public void sinhronizuj() {
		this.smestajServiceClient.sinhronizujAdrese(new GetSveAdreseRequest());
		this.korisniciServiceClient.sinhronizujKorisnike(new GetSviKorisniciRequest());
		this.korisniciServiceClient.sinhronizujAgente(new GetSviAgentiRequest());
		this.porukeServiceClient.sinhronizujPoruke(new GetStanjeBazePodatakaPorukaRequest());
		this.smestajServiceClient.sinhronizujTipoveSmestaja(new GetSviTipoviRequest());
		this.smestajServiceClient.sinhronizujUsluge(new GetSveUslugeRequest());
		this.smestajServiceClient.sinhronizujSmestajneJedinice(new GetSveSmestajneJediniceRequest());
		this.smestajServiceClient.sinhronizujRezervacije(new GetSveRezervacijeRequest());
		this.utisciServiceClient.sinhronizujUtiske(new GetStanjeBazePodatakaUtisakaRequest());
		this.korisniciServiceClient.sinhronizujSpojeveAgenta(new GetSviSpojeviAgentSmestajRequest());
		this.smestajServiceClient.sinhronizujCenovnike(new GetSviCenovniciRequest());
	}
	
}
