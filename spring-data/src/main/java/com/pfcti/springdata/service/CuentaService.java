package com.pfcti.springdata.service;

import com.pfcti.springdata.criteria.CuentaSpecification;
import com.pfcti.springdata.dto.ClienteDto;
import com.pfcti.springdata.dto.CuentaDto;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.repository.ClienteRepository;
import com.pfcti.springdata.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class CuentaService {
    CuentaRepository cuentaRepository;


    CuentaSpecification cuentaSpefication;

    ClienteRepository clienteRepository;


    public List<CuentaDto> buscarCuentasDinamicamentePorCriterio(CuentaDto cuentaDtoFilter) {
        return cuentaRepository.findAll(cuentaSpefication.buildFilter(cuentaDtoFilter))
                .stream().map(this::fromCuentaToDto).collect(Collectors.toList());
    }

    private CuentaDto fromCuentaToDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();


        BeanUtils.copyProperties(cuenta, cuentaDto);
        cuentaDto.setIdCliente(cuenta.getCliente().getId());
        return cuentaDto;
    }

    private Cuenta fromDtoToCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        BeanUtils.copyProperties(cuentaDto, cuenta);

          Cliente cliente = new Cliente();
          cliente.setId(cuentaDto.getIdCliente());
          cuenta.setCliente(cliente);

        return cuenta;
    }

    public List<CuentaDto> buscarCuentasPorCliente(int idCliente) {
        List<CuentaDto> cuentasPorCliente = new ArrayList<>();
        cuentaRepository.findByCliente_IdAndEstadoIsTrue(idCliente)
                .stream()
                .map(cuenta -> {
                            cuentasPorCliente.add(fromCuentaToDto(cuenta));
                            log.info("Cuenta de Cliente :{}", cuenta);
                            return cuenta;
                        }
                ).collect(Collectors.toList());
        return cuentasPorCliente;
    }

    public void creacionDeCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        cuenta = fromDtoToCuenta(cuentaDto);

        cuentaRepository.save(cuenta);
        log.info("Cuenta: {} ", cuenta);
    }


    public void desactivarCuentasActivasCliente(int idCliente) {

        List<Cuenta> cuentas = cuentaRepository.findByCliente_IdAndEstadoIsTrue(idCliente);
        cuentas.forEach(cuenta -> {
                    cuenta.setEstado(false);
                    cuentaRepository.save(cuenta);
                }
        );
    }

    public CuentaDto desactivarCuentaPorId(CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaRepository.findById(cuentaDto.getId()).orElseThrow(() -> {
            throw new RuntimeException("cuenta de Cliente No Existe");
        });
        cuenta.setEstado(false);
        cuentaRepository.save(cuenta);
        return fromCuentaToDto(cuenta);
    }

}
