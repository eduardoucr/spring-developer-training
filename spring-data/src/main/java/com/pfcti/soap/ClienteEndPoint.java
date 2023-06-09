package com.pfcti.soap;

import com.pfcti.springdata.service.ClienteService;
import io.spring.guides.gs_producing_web_service.GetClienteRequest;
import io.spring.guides.gs_producing_web_service.GetClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClienteEndPoint {
    private static final String NAMESSPACE_URI = "http://spring.io/guides/gs-producing-web-service";


    @Autowired
    private ClienteService clienteService;
    @PayloadRoot(namespace = NAMESSPACE_URI,localPart = "getClienteRequest")
    @ResponsePayload
    public GetClienteResponse obtenerCliente(@RequestPayload GetClienteRequest request) {
        GetClienteResponse response= new GetClienteResponse();
        response.setCliente(clienteService.obtenerClienteParaSoap(request.getId()));
        return response;
    }
}
