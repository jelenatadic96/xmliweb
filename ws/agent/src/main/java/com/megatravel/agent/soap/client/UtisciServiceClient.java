package com.megatravel.agent.soap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.agent.service.UtisakService;
import com.megatravel.agent.soap.generated.GetStanjeBazePodatakaUtisakaRequest;
import com.megatravel.agent.soap.generated.GetStanjeBazePodatakaUtisakaResponse;
import com.megatravel.agent.soap.generated.UtisakDTO;

@Service
public class UtisciServiceClient {

	private static final String URI = "http://localhost:8762/utisak-service/services";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private UtisakService utisakService;
	
	public void sinhronizujUtiske(GetStanjeBazePodatakaUtisakaRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetStanjeBazePodatakaUtisakaResponse odgovor = (GetStanjeBazePodatakaUtisakaResponse) template.marshalSendAndReceive(URI, zahtev);
		for(UtisakDTO utisak : odgovor.getUtisci()) {
			utisakService.dodajZaSinhronizaciju(utisak);
		}
	}
	
}
