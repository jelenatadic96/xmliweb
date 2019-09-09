package com.megatravel.agent.soap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.agent.model.SmestajnaJedinica;
import com.megatravel.agent.service.AdresaService;
import com.megatravel.agent.service.CenovnikService;
import com.megatravel.agent.service.RezervacijaService;
import com.megatravel.agent.service.SmestajnaJedinicaService;
import com.megatravel.agent.service.TipSmestajaService;
import com.megatravel.agent.service.UslugaService;
import com.megatravel.agent.soap.generated.AdresaDTO;
import com.megatravel.agent.soap.generated.CenovnikDTO;
import com.megatravel.agent.soap.generated.DodavanjeSmestajneJediniceRequest;
import com.megatravel.agent.soap.generated.GetSveAdreseRequest;
import com.megatravel.agent.soap.generated.GetSveAdreseResponse;
import com.megatravel.agent.soap.generated.GetSveRezervacijeRequest;
import com.megatravel.agent.soap.generated.GetSveRezervacijeResponse;
import com.megatravel.agent.soap.generated.GetSveSmestajneJediniceRequest;
import com.megatravel.agent.soap.generated.GetSveSmestajneJediniceResponse;
import com.megatravel.agent.soap.generated.GetSveUslugeRequest;
import com.megatravel.agent.soap.generated.GetSveUslugeResponse;
import com.megatravel.agent.soap.generated.GetSviCenovniciRequest;
import com.megatravel.agent.soap.generated.GetSviCenovniciResponse;
import com.megatravel.agent.soap.generated.GetSviTipoviRequest;
import com.megatravel.agent.soap.generated.GetSviTipoviResponse;
import com.megatravel.agent.soap.generated.PotvrdiRezervacijuRequest;
import com.megatravel.agent.soap.generated.RezervacijaDTO;
import com.megatravel.agent.soap.generated.SmestajnaJedinicaDTO;
import com.megatravel.agent.soap.generated.TipSmestajaDTO;
import com.megatravel.agent.soap.generated.UslugaDTO;

@Service
public class SmestajServiceClient {

	private static final String URI = "http://localhost:8762/smestaj-service/services";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private AdresaService adresaService;
	
	@Autowired
	private TipSmestajaService tipSmestajaService;
	
	@Autowired
	private UslugaService uslugaService;
	
	@Autowired
	private SmestajnaJedinicaService smestajnaJedinicaService;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private CenovnikService cenovnikService;
	
	public void sinhronizujAdrese(GetSveAdreseRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSveAdreseResponse odgovor = (GetSveAdreseResponse)  this.template.marshalSendAndReceive(SmestajServiceClient.URI, zahtev);
		for(AdresaDTO adresaDTO : odgovor.getAdrese()) {
			this.adresaService.dodajZaSinhronizaciju(adresaDTO);
		}
	}

	public void sinhronizujTipoveSmestaja(GetSviTipoviRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSviTipoviResponse odgovor = (GetSviTipoviResponse)  this.template.marshalSendAndReceive(SmestajServiceClient.URI, zahtev);
		for(TipSmestajaDTO tipDTO : odgovor.getTipovi()) {
			this.tipSmestajaService.dodajZaSinhronizaciju(tipDTO);
		}
	}

	public void sinhronizujUsluge(GetSveUslugeRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSveUslugeResponse odgovor = (GetSveUslugeResponse)  this.template.marshalSendAndReceive(SmestajServiceClient.URI, zahtev);
		for(UslugaDTO uslugaDTO : odgovor.getUsluge()) {
			this.uslugaService.dodajZaSinhronizaciju(uslugaDTO);
		}
	}

	public void sinhronizujSmestajneJedinice(GetSveSmestajneJediniceRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSveSmestajneJediniceResponse odgovor = (GetSveSmestajneJediniceResponse)  this.template.marshalSendAndReceive(SmestajServiceClient.URI, zahtev);
		for(SmestajnaJedinicaDTO jedinicaDTO : odgovor.getSmestaji()) {
			this.smestajnaJedinicaService.dodajZaSinhronizaciju(jedinicaDTO);
		}
	}
	
	public void sinhronizujRezervacije(GetSveRezervacijeRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSveRezervacijeResponse odgovor = (GetSveRezervacijeResponse)  this.template.marshalSendAndReceive(SmestajServiceClient.URI, zahtev);
		for(RezervacijaDTO rezervacijaDTO : odgovor.getRezervacije()) {
			this.rezervacijaService.dodajZaSinhronizaciju(rezervacijaDTO);
		}
	}
	
	public void sinhronizujCenovnike(GetSviCenovniciRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetSviCenovniciResponse odgovor = (GetSviCenovniciResponse)  this.template.marshalSendAndReceive(SmestajServiceClient.URI, zahtev);
		for(CenovnikDTO cenovnikDTO : odgovor.getCenovnici()) {
			this.cenovnikService.dodajZaSinhronizaciju(cenovnikDTO);
		}
	}
	
	public void dodajSmestajnuJedinicu(SmestajnaJedinica smestajnaJedinica) {
		DodavanjeSmestajneJediniceRequest zahtev = new DodavanjeSmestajneJediniceRequest();
		zahtev.setAgent(0);
		SmestajnaJedinicaDTO soapDTO = new SmestajnaJedinicaDTO(smestajnaJedinica);
		zahtev.setSmestaj(soapDTO);
		this.template = new WebServiceTemplate(this.marshaller);
		this.template.marshalSendAndReceive(SmestajServiceClient.URI, zahtev);
	}
	
	public void potvrdiRezervaciju(Long id) {
		PotvrdiRezervacijuRequest zahtev = new PotvrdiRezervacijuRequest();
		zahtev.setRezervacija(id);
		this.template = new WebServiceTemplate(this.marshaller);
		this.template.marshalSendAndReceive(SmestajServiceClient.URI, zahtev);
	}
	
}
