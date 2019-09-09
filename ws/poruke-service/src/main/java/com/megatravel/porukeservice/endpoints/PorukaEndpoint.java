package com.megatravel.porukeservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.porukeservice.model.Poruka;
import com.megatravel.porukeservice.service.PorukaService;
import com.megatravel.porukeservice.soap.dto.GetStanjeBazePodatakaPorukaRequest;
import com.megatravel.porukeservice.soap.dto.GetStanjeBazePodatakaPorukaResponse;
import com.megatravel.porukeservice.soap.dto.PorukaDTO;
import com.megatravel.porukeservice.soap.dto.PosaljiPorukuRequest;

@Endpoint
public class PorukaEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/porukeservice/soap/dto";
	
	@Autowired
	private PorukaService porukaService;
	
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStanjeBazePodatakaPorukaRequest")
    @ResponsePayload
    public GetStanjeBazePodatakaPorukaResponse preuzmiSve(@RequestPayload GetStanjeBazePodatakaPorukaRequest zahtev) {
    	GetStanjeBazePodatakaPorukaResponse odgovor = new GetStanjeBazePodatakaPorukaResponse();
    	odgovor.getPoruke().addAll(PorukaDTO.transformisi(this.porukaService.preuzmiSvePoruke()));
        return odgovor;
    }
	
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "posaljiPorukuRequest")
    @ResponsePayload
    public void posaljiPoruku(@RequestPayload PosaljiPorukuRequest zahtev) {
    	PorukaDTO porukaDTO = zahtev.getPoruka();
    	this.porukaService.posaljiPoruku(new Poruka(porukaDTO), 
    			porukaDTO.getAgentId(),
    			porukaDTO.getKorisnikId());
    }
    
}
