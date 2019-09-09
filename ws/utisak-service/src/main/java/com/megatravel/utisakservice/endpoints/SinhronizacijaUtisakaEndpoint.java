package com.megatravel.utisakservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.utisakservice.service.UtisakService;
import com.megatravel.utisakservice.soap.dto.GetStanjeBazePodatakaUtisakaRequest;
import com.megatravel.utisakservice.soap.dto.GetStanjeBazePodatakaUtisakaResponse;
import com.megatravel.utisakservice.soap.dto.UtisakDTO;

@Endpoint
public class SinhronizacijaUtisakaEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/utisakservice/soap/dto";
	
	@Autowired
	private UtisakService utisakService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStanjeBazePodatakaUtisakaRequest")
    @ResponsePayload
	public GetStanjeBazePodatakaUtisakaResponse preuzmiSve(@RequestPayload GetStanjeBazePodatakaUtisakaRequest zahtev) {
		GetStanjeBazePodatakaUtisakaResponse odgovor = new GetStanjeBazePodatakaUtisakaResponse();
		odgovor.getUtisci().addAll(UtisakDTO.transformisi(utisakService.preuzmiSveUtiske()));
		return odgovor;
	}
    
}
