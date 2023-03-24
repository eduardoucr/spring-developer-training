package com.pfcti.springbeans.business;

import com.pfcti.springbeans.dto.ClienteQueryDto;
import com.pfcti.springdata.dto.ClienteDto;

import java.util.List;

public interface BuscadorClientes {
    List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto);


}
