package com.megatravel.korisniciservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.korisniciservice.repository.SpojAgentSmestajRepository;
import com.megatravel.korisniciservice.service.AgentService;
import com.megatravel.korisniciservice.service.KorisnikService;
import com.megatravel.korisniciservice.soap.dto.AgentDTO;
import com.megatravel.korisniciservice.soap.dto.GetAgentLoginRequest;
import com.megatravel.korisniciservice.soap.dto.GetAgentLoginResponse;
import com.megatravel.korisniciservice.soap.dto.GetSviAgentiRequest;
import com.megatravel.korisniciservice.soap.dto.GetSviAgentiResponse;
import com.megatravel.korisniciservice.soap.dto.GetSviKorisniciRequest;
import com.megatravel.korisniciservice.soap.dto.GetSviKorisniciResponse;
import com.megatravel.korisniciservice.soap.dto.GetSviSpojeviAgentSmestajRequest;
import com.megatravel.korisniciservice.soap.dto.GetSviSpojeviAgentSmestajResponse;
import com.megatravel.korisniciservice.soap.dto.KorisnikDTO;
import com.megatravel.korisniciservice.soap.dto.SpojAgentSmestajDTO;

@Endpoint
public class KorisnikEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/korisniciservice/soap/dto";
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private SpojAgentSmestajRepository spojAgentSmestajRepository;
	
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentLoginRequest")
    @ResponsePayload
	public GetAgentLoginResponse uloguj(@RequestPayload GetAgentLoginRequest zahtev) {
    	GetAgentLoginResponse odgovor = new GetAgentLoginResponse();
    	odgovor.setAgent(new AgentDTO(this.agentService.ulogujAgenta(zahtev.getMejl(), zahtev.getLozinka())));
    	return odgovor;
	}
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSviAgentiRequest")
    @ResponsePayload
    public GetSviAgentiResponse preuzmiSveAgente(@RequestPayload GetSviAgentiRequest zahtev) {
    	GetSviAgentiResponse odgovor = new GetSviAgentiResponse();
    	odgovor.getAgenti().addAll(AgentDTO.transformisi(this.agentService.preuzmiSveAgente()));
    	return odgovor;
    }
	
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSviKorisniciRequest")
    @ResponsePayload
    public GetSviKorisniciResponse preuzmiSveKorisnik(@RequestPayload GetSviKorisniciRequest zahtev) {
    	GetSviKorisniciResponse odgovor = new GetSviKorisniciResponse();
    	odgovor.getKorisnici().addAll(KorisnikDTO.transformisi(this.korisnikService.preuzmiSve()));
    	return odgovor;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSviSpojeviAgentSmestajRequest")
    @ResponsePayload
    public GetSviSpojeviAgentSmestajResponse preuzmiSveSpojeve(@RequestPayload GetSviSpojeviAgentSmestajRequest zahtev) {
    	GetSviSpojeviAgentSmestajResponse odgovor = new GetSviSpojeviAgentSmestajResponse();
    	odgovor.getSpojevi().addAll(SpojAgentSmestajDTO.transformisi(this.spojAgentSmestajRepository.findAll()));
    	return odgovor;
    }
    
}
