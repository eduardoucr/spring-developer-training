package com.pfcti.springbeans.business;

import com.pfcti.springbeans.dto.ClienteQueryDto;
import com.pfcti.springbeans.dto.CuentaQueryDto;
import com.pfcti.springdata.dto.ClienteDto;
import com.pfcti.springdata.dto.CuentaDto;

import java.util.List;

public interface BuscadorCuentas {



    List<CuentaDto> buscarCuentasPorCliente(int idCliente);



}
