package com.megatravel.agent.soap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.agent.service.PorukaService;
import com.megatravel.agent.soap.generated.GetStanjeBazePodatakaPorukaRequest;
import com.megatravel.agent.soap.generated.GetStanjeBazePodatakaPorukaResponse;
import com.megatravel.agent.soap.generated.PorukaDTO;
import com.megatravel.agent.soap.generated.PosaljiPorukuRequest;

@Service
public class PorukeServiceClient {

	private static final String URI = "http://localhost:8762/poruke-service/services";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;

	@Autowired
	private PorukaService porukaService;
	
	public void sinhronizujPoruke(GetStanjeBazePodatakaPorukaRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		GetStanjeBazePodatakaPorukaResponse odgovor = (GetStanjeBazePodatakaPorukaResponse) this.template.marshalSendAndReceive(PorukeServiceClient.URI, zahtev);
		for(PorukaDTO poruka : odgovor.getPoruke()) {
			this.porukaService.dodajPorukuZaSinhronizaciju(poruka);
		}
	}
	
	public void posaljiPoruku(PosaljiPorukuRequest zahtev) {
		this.template = new WebServiceTemplate(this.marshaller);
		this.template.marshalSendAndReceive(PorukeServiceClient.URI, zahtev);
	}
	
}
