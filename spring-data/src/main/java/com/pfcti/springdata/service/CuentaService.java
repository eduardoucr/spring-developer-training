package com.pfcti.springdata.service;

import com.pfcti.springdata.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CuentaService {
    CuentaRepository cuentaRepository;
}
