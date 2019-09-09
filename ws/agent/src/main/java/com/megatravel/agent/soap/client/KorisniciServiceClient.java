package com.megatravel.agent.soap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.agent.service.AgentService;
import com.megatravel.agent.service.KorisnikService;
import com.megatravel.agent.service.SmestajnaJedinicaService;
import com.megatravel.agent.soap.generated.AgentDTO;
import com.megatravel.agent.soap.generated.GetAgentLoginRequest;
import com.megatravel.agent.soap.generated.GetAgentLoginResponse;
import com.megatravel.agent.soap.generated.GetSviAgentiRequest;
import com.megatravel.agent.soap.generated.GetSviAgentiResponse;
import com.megatravel.agent.soap.generated.GetSviKorisniciRequest;
import com.megatravel.agent.soap.generated.GetSviKorisniciResponse;
import com.megatravel.agent.soap.generated.GetSviSpojeviAgentSmestajRequest;
import com.megatravel.agent.soap.generated.GetSviSpojeviAgentSmestajResponse;
import com.megatravel.agent.soap.generated.KorisnikDTO;
import com.megatravel.agent.soap.generated.SpojAgentSmestajDTO;

@Service
public class KorisniciServiceClient {

	private static final String URI = "http://localhost:8762/korisnici-service/services";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private SmestajnaJedinicaService smestajnaJedinicaService;
	
	public boolean uloguj(GetAgentLoginRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetAgentLoginResponse odgovor = (GetAgentLoginResponse)  this.template.marshalSendAndReceive(KorisniciServiceClient.URI, zahtev);
		return odgovor.getAgent() != null;
	}
	
	public void sinhronizujAgente(GetSviAgentiRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSviAgentiResponse odgovor = (GetSviAgentiResponse)  this.template.marshalSendAndReceive(KorisniciServiceClient.URI, zahtev);
		for(AgentDTO agentDTO : odgovor.getAgenti()) {
			this.agentService.dodajAgentaZaSinhronizaciju(agentDTO);
		}
	}
	
	public void sinhronizujKorisnike(GetSviKorisniciRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSviKorisniciResponse odgovor = (GetSviKorisniciResponse)  this.template.marshalSendAndReceive(KorisniciServiceClient.URI, zahtev);
		for(KorisnikDTO korisnikDTO : odgovor.getKorisnici()) {
			this.korisnikService.dodajKorisnikZaSinhronizaciju(korisnikDTO);
		}
	}
	
	public void sinhronizujSpojeveAgenta(GetSviSpojeviAgentSmestajRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSviSpojeviAgentSmestajResponse odgovor = (GetSviSpojeviAgentSmestajResponse)  this.template.marshalSendAndReceive(KorisniciServiceClient.URI, zahtev);
		for(SpojAgentSmestajDTO spojDTO : odgovor.getSpojevi()) {
			this.smestajnaJedinicaService.dodajSpojeveZaSinhronizaciju(spojDTO);
		}
	}
	
}
