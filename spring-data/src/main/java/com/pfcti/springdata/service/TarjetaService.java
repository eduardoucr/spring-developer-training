package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.ClienteDto;
import com.pfcti.springdata.dto.TarjetaDto;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Tarjeta;
import com.pfcti.springdata.repository.TarjetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarjetaService {
    TarjetaRepository tarjetaRepository;


    public TarjetaDto actualizarTarjeta(TarjetaDto tarjetaDto){
        Tarjeta tarjeta = tarjetaRepository.findById(tarjetaDto.getId()).
                orElseThrow(() -> {throw new RuntimeException("Tarjeta No Existe");});
        tarjeta.setEstado(tarjetaDto.getEstado());

        tarjetaRepository.save(tarjeta);
        return tarjetaDto;
    };


    public TarjetaDto obtenerTarjeta (int idTarjeta){
        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta).
                orElseThrow(() -> {throw new RuntimeException("Tarjeta No Existe");});
        TarjetaDto tarjetaDto= new TarjetaDto();

        tarjetaDto.setId(tarjeta.getId());
        tarjetaDto.setEstado(tarjeta.getEstado());
        tarjetaDto.setTipo(tarjeta.getTipo());
        tarjetaDto.setNumero(tarjeta.getNumero());


        return tarjetaDto;
    }


}
