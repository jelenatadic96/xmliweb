package com.megatravel.porukeservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.porukeservice.gen.GetCurrentDatabaseStateRequest;
import com.megatravel.porukeservice.gen.GetCurrentDatabaseStateResponse;
import com.megatravel.porukeservice.gen.PorukaDTO;
import com.megatravel.porukeservice.service.PorukaService;

@Endpoint
public class PorukaEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/porukeservice/gen";
	
	@Autowired
	private PorukaService porukaService;
	
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrentDatabaseStateRequest")
    @ResponsePayload
    public GetCurrentDatabaseStateResponse getCountry(@RequestPayload GetCurrentDatabaseStateRequest request) {
    	GetCurrentDatabaseStateResponse response = new GetCurrentDatabaseStateResponse();
        response.getPoruke().addAll(PorukaDTO.transformisi(this.porukaService.preuzmiSvePoruke()));
        return response;
    }
	
}
