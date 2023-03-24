package com.pfcti.rest.api;

import com.pfcti.springdata.dto.ClienteDto;
import com.pfcti.springdata.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/cliente")
@Slf4j
public class ClienteApi {
    @Autowired

    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDto buscarCliente(@PathVariable int id){
        log.info("Busqueda de Cliente: {}" , id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/xml/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ClienteDto buscarClienteXml(@PathVariable int id){
        log.info("Busqueda de Cliente: {}" , id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/xml/json/{id}", produces  ={MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ClienteDto buscarClienteXmlJson(@PathVariable int id){
        log.info("Busqueda de Cliente: {}" , id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/parameter", produces  ={MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ClienteDto buscarClienteParameter(@RequestParam int id){
        log.info("Busqueda de Cliente: {}" , id);
        return clienteService.obtenerCliente(id);
    }

    @PostMapping
    public void guardarCliente(@RequestBody ClienteDto clienteDto){
        log.info("Cliente a crear {}" , clienteDto);
        clienteService.insertarCliente(clienteDto);
    }


}
