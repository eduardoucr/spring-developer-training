package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.ClienteDto;
import com.pfcti.springdata.dto.TarjetaDto;
import com.pfcti.springdata.model.Tarjeta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TarjetaServiceTest {

    @Autowired
    private TarjetaService tarjetaService;

    @Test
    void testActualizarTarjeta() {

        TarjetaDto tarjetaDto = tarjetaService.obtenerTarjeta(1);

        tarjetaDto.setEstado(false);

        tarjetaService.actualizarTarjeta(tarjetaDto);


        assertEquals(false, tarjetaDto.getEstado());

    }
}