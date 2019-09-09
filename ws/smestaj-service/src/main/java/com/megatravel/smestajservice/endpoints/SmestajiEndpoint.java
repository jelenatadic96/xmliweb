package com.megatravel.smestajservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.smestajservice.service.AdresaService;
import com.megatravel.smestajservice.service.CenovnikService;
import com.megatravel.smestajservice.service.RezervacijaService;
import com.megatravel.smestajservice.service.SmestajnaJedinicaService;
import com.megatravel.smestajservice.service.TipSmestajaService;
import com.megatravel.smestajservice.service.UslugaService;
import com.megatravel.smestajservice.soap.dto.AdresaDTO;
import com.megatravel.smestajservice.soap.dto.CenovnikDTO;
import com.megatravel.smestajservice.soap.dto.DodavanjeSmestajneJediniceRequest;
import com.megatravel.smestajservice.soap.dto.GetSveAdreseRequest;
import com.megatravel.smestajservice.soap.dto.GetSveAdreseResponse;
import com.megatravel.smestajservice.soap.dto.GetSveRezervacijeRequest;
import com.megatravel.smestajservice.soap.dto.GetSveRezervacijeResponse;
import com.megatravel.smestajservice.soap.dto.GetSveSmestajneJediniceRequest;
import com.megatravel.smestajservice.soap.dto.GetSveSmestajneJediniceResponse;
import com.megatravel.smestajservice.soap.dto.GetSveUslugeRequest;
import com.megatravel.smestajservice.soap.dto.GetSveUslugeResponse;
import com.megatravel.smestajservice.soap.dto.GetSviCenovniciRequest;
import com.megatravel.smestajservice.soap.dto.GetSviCenovniciResponse;
import com.megatravel.smestajservice.soap.dto.GetSviTipoviRequest;
import com.megatravel.smestajservice.soap.dto.GetSviTipoviResponse;
import com.megatravel.smestajservice.soap.dto.PotvrdiRezervacijuRequest;
import com.megatravel.smestajservice.soap.dto.RezervacijaDTO;
import com.megatravel.smestajservice.soap.dto.SmestajnaJedinicaDTO;
import com.megatravel.smestajservice.soap.dto.TipSmestajaDTO;
import com.megatravel.smestajservice.soap.dto.UslugaDTO;

@Endpoint
@Component
public class SmestajiEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/smestajservice/soap/dto";
	
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
	
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSveAdreseRequest")
    @ResponsePayload
	public GetSveAdreseResponse preuzmiSveAdrese(@RequestPayload GetSveAdreseRequest zahtev) {
		GetSveAdreseResponse odgovor = new GetSveAdreseResponse();
		odgovor.getAdrese().addAll(AdresaDTO.transformisi(this.adresaService.preuzmiSve()));
		return odgovor;
	}
	
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSviTipoviRequest")
    @ResponsePayload
    public GetSviTipoviResponse preuzmiSveTipove(@RequestPayload GetSviTipoviRequest zahtev) {
    	GetSviTipoviResponse odgovor = new GetSviTipoviResponse();
    	odgovor.getTipovi().addAll(TipSmestajaDTO.transformisi(this.tipSmestajaService.preuzmiSve()));
		return odgovor;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSveUslugeRequest")
    @ResponsePayload
    public GetSveUslugeResponse preuzmiSveUsluge(@RequestPayload GetSveUslugeRequest zahtev) {
    	GetSveUslugeResponse odgovor = new GetSveUslugeResponse();
    	odgovor.getUsluge().addAll(UslugaDTO.transformisi(this.uslugaService.preuzmiSve()));
		return odgovor;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSveSmestajneJediniceRequest")
    @ResponsePayload
    public GetSveSmestajneJediniceResponse preuzmiSveSmestajneJedinice(@RequestPayload GetSveSmestajneJediniceRequest zahtev) {
    	GetSveSmestajneJediniceResponse odgovor = new GetSveSmestajneJediniceResponse();
    	odgovor.getSmestaji().addAll(SmestajnaJedinicaDTO.transformisi(this.smestajnaJedinicaService.preuzmiSve()));
		return odgovor;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSveRezervacijeRequest")
    @ResponsePayload
    public GetSveRezervacijeResponse preuzmiSveRezervacije(@RequestPayload GetSveRezervacijeRequest zahtev) {
    	GetSveRezervacijeResponse odgovor = new GetSveRezervacijeResponse();
    	odgovor.getRezervacije().addAll(RezervacijaDTO.transformisi(this.rezervacijaService.preuzmiSveRezervacije()));
		return odgovor;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSviCenovniciRequest")
    @ResponsePayload
    public GetSviCenovniciResponse preuzmiSveCenovnike(@RequestPayload GetSviCenovniciRequest zahtev) {
    	GetSviCenovniciResponse odgovor = new GetSviCenovniciResponse();
    	odgovor.getCenovnici().addAll(CenovnikDTO.transformisi(this.cenovnikService.preuzmiSve()));
    	return odgovor;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "potvrdiRezervacijuRequest")
    @ResponsePayload
    public void potvrdiRealizacijuRezervacije(@RequestPayload PotvrdiRezervacijuRequest zahtev) {
    	Long rezervacija = zahtev.getRezervacija();
    	this.rezervacijaService.potvrdiRezervaciju(rezervacija);
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "dodavanjeSmestajneJediniceRequest")
    @ResponsePayload
    public void dodajSmestajnuJedinicu(@RequestPayload DodavanjeSmestajneJediniceRequest zahtev) {
    	this.smestajnaJedinicaService.kreiraj(zahtev.getSmestaj(), zahtev.getAgent());
    }
    
}
