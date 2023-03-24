package com.pfcti.springdata.service;

import com.pfcti.springdata.criteria.ClienteSpecification;
import com.pfcti.springdata.dto.*;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.model.Inversion;
import com.pfcti.springdata.model.Tarjeta;
import com.pfcti.springdata.repository.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ClienteService {
    ClienteRepository clienteRepository;

    DireccionRepository direccionRepository;

    CuentaRepository cuentaRepository;
    TarjetaRepository tarjetaRepository;
    InversionRepository inversionRepository;

    ClienteSpecification clienteSpecification;



    public void insertarCliente (ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setApellidos(clienteDto.getApellidos());
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
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setCedula(cliente.getCedula());
        return clienteDto;
    }

    public ClienteDto actualizarCliente(ClienteDto clienteDto){
        Cliente cliente = clienteRepository.findById(clienteDto.getId()).
                orElseThrow(() -> {throw new RuntimeException("Cliente No Existe");});
        cliente.setId(clienteDto.getId());
        cliente.setApellidos(clienteDto.getApellidos());
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
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setCedula(cliente.getCedula());
        return clienteDto;
    }


    public List<ClienteDto> obtenerClientesPorCodigoISOPaisYCuentasActivas(String codigoISOPais) {
        List<ClienteDto> resultadoClientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClientesByPaisNacimientoAndCuentas_EstadoIsTrue(codigoISOPais);
        clientes.forEach(cliente -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setId(cliente.getId());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setPaisNacimiento(cliente.getPaisNacimiento());
            resultadoClientesDto.add(clienteDto);
            System.out.println(clienteDto);
        });
        return resultadoClientesDto;
    }

    public List<ClienteDto> obtenerClientesExtrajerosYTarjetasInactivas(String codigoISOPais) {
        List<ClienteDto> resultadoClientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClientesByPaisNacimientoAndTarjetas_EstadoIsFalse(codigoISOPais);
        clientes.forEach(cliente -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setId(cliente.getId());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setPaisNacimiento(cliente.getPaisNacimiento());
            resultadoClientesDto.add(clienteDto);
            System.out.println(clienteDto);
        });
        return resultadoClientesDto;
    }



    public List<Cliente> buscarClientesPorApellido(String apellidos){
        return clienteRepository.buscarPorApellidos(apellidos);
    }


    public List<ClienteDto> buscarClientesPorApellidoNativo(String apellidos){
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Tuple> tuples = clienteRepository.buscarPorApellidosNativo(apellidos);

        tuples.forEach(tuple -> {

            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setApellidos((String) tuple.get("apellidos"));
            clienteDto.setNombre((String) tuple.get("nombre"));
            clienteDto.setCedula((String) tuple.get("cedula"));


            clienteDtos.add(clienteDto);
            System.out.println(tuple.get("apellidos"));
        });
        return clienteDtos;
    }

    public List<ClienteDto> buscarClientesDinamicamentePorCriterio(ClienteDto clienteDtoFilter){
        return clienteRepository.findAll(clienteSpecification.buildFilter(clienteDtoFilter))
                .stream().map(this::fromClienteToDto).collect(Collectors.toList());
    }
    private ClienteDto fromClienteToDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    private CuentaDto fromCuentaToDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }

    private TarjetaDto fromTarjetaToDto(Tarjeta tarjeta) {
        TarjetaDto tarjetaDto = new TarjetaDto();
        BeanUtils.copyProperties(tarjeta, tarjetaDto);
        return tarjetaDto;
    }

    private InversionDto fromInversionToDto(Inversion inversion) {
        InversionDto inversionDto = new InversionDto();
        BeanUtils.copyProperties(inversion, inversionDto);
        return inversionDto;
    }


    public ProductoDto obtenerTodosLosProductosDeUnCliente(int id) {
        ProductoDto productosDto = new ProductoDto();
        List<CuentaDto> cuentaDtos = new ArrayList<>();
        cuentaRepository.findByCliente_IdAndEstadoIsTrue(id).forEach(cuenta -> {
            CuentaDto cuentaDto;
            cuentaDto = fromCuentaToDto(cuenta);
            cuentaDtos.add(cuentaDto);
        });
        productosDto.setCuentaDtos(cuentaDtos);

        List<TarjetaDto> tarjetaDtos = new ArrayList<>();
        tarjetaRepository.findByCliente_IdAndEstadoIsTrue(id).forEach(tarjeta -> {
            TarjetaDto tarjetaDto;
            tarjetaDto = fromTarjetaToDto(tarjeta);
            tarjetaDtos.add(tarjetaDto);
        });
        productosDto.setTarjetaDtos(tarjetaDtos);

        List<InversionDto> inversionDtos = new ArrayList<>();
        inversionRepository.findByCliente_IdAndEstadoIsTrue(id).forEach(inversion -> {
            InversionDto inversionDto;
            inversionDto = fromInversionToDto(inversion);
            inversionDtos.add(inversionDto);
        });
        productosDto.setInversionDtos(inversionDtos);
        return productosDto;
    }

    public void eliminarCliente(int clienteId){

        // primero los hijos
        direccionRepository.deleteAllByCliente_Id(clienteId);
        cuentaRepository.deleteAllByCliente_Id(clienteId);
        //luego la tabla padre
        clienteRepository.deleteById(clienteId);
    };

    public List<ClienteDto> listarTodosLosClientes(){
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        clienteRepository
                .findAll()
                .stream()
                .map(cliente -> {

                    clienteDtoList.add(fromClienteToDto(cliente));
                    return cliente;
                }).collect(Collectors.toList());
        return clienteDtoList;
    }

}

