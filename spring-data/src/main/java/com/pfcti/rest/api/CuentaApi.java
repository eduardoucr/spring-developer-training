package com.pfcti.rest.api;

import com.pfcti.springdata.dto.ClienteDto;
import com.pfcti.springdata.dto.CuentaDto;
import com.pfcti.springdata.service.ClienteService;
import com.pfcti.springdata.service.CuentaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cuenta")
@Slf4j

public class CuentaApi {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{id}")
    public  List<CuentaDto>   buscarCuentas(@PathVariable int id) {
        log.info("Busqueda de Cuenta: {}", id);
        return cuentaService.buscarCuentasPorCliente(id);
    }

    //Revisar
    @PostMapping
    public void guardarCuenta( @RequestBody CuentaDto cuentaDto) {
        log.info("Cuenta a crear {}", cuentaDto);
        cuentaService.creacionDeCuenta(cuentaDto);
    }

    @PutMapping(value = "/parameter")
    public void actualizarDesactivarCuentasActivasPorIdCliente(@RequestParam  int idCliente){
        log.info("Desactivar cuenta del Cliente ", idCliente);
        cuentaService.desactivarCuentasActivasCliente(idCliente);
    }

    @PutMapping
    public void actualizarDesactivarCuentaPorID( @RequestBody CuentaDto cuentaDto){
        log.info("Actualización de Cliente ", cuentaDto);
        cuentaService.desactivarCuentaPorId(cuentaDto) ;
    }



}
