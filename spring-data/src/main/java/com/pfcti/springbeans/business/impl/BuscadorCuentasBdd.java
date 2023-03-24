package com.pfcti.springbeans.business.impl;

import com.pfcti.springbeans.business.BuscadorCuentas;
import com.pfcti.springbeans.dto.ClienteQueryType;
import com.pfcti.springbeans.dto.CuentaQueryDto;
import com.pfcti.springbeans.dto.CuentaQueryType;
import com.pfcti.springdata.dto.ClienteDto;
import com.pfcti.springdata.dto.CuentaDto;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.repository.ClienteRepository;
import com.pfcti.springdata.repository.CuentaRepository;
import com.pfcti.springdata.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("servicioCuentasBdd")
public class BuscadorCuentasBdd implements BuscadorCuentas {

    @Autowired
    private CuentaService cuentaService;

    @Override
    public List<CuentaDto> buscarCuentasPorCliente(int idCliente) {
        return cuentaService.buscarCuentasPorCliente(idCliente);
    }
}
