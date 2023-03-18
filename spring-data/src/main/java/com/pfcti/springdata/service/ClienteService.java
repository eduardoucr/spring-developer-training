package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.ClienteDto;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {
    ClienteRepository clienteRepository;

    public void insertarCliente (ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setApellidos(clienteDto.getApellido());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        clienteRepository.save(cliente);

    }

    public ClienteDto obtenerCliente (int idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).
                orElseThrow(() -> {throw new RuntimeException("Cliente No Existe");});
        ClienteDto clienteDto= new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setApellido(cliente.getApellidos());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setCedula(cliente.getCedula());
        return clienteDto;
    }

    public ClienteDto actualizarCliente(ClienteDto clienteDto){
        Cliente cliente = clienteRepository.findById(clienteDto.getId()).
                orElseThrow(() -> {throw new RuntimeException("Cliente No Existe");});
        cliente.setId(clienteDto.getId());
        cliente.setApellidos(clienteDto.getApellido());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        clienteRepository.save(cliente);
        return clienteDto;
    };

    public ClienteDto obtenerClienteReport (int idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).
                orElseThrow(() -> {throw new RuntimeException("Cliente No Existe");});
        ClienteDto clienteDto= new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setApellido(cliente.getApellidos());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setCedula(cliente.getCedula());
        return clienteDto;
    }



    public void eliminarCliente(int clienteId){
       clienteRepository.deleteById(clienteId);
    };

}

