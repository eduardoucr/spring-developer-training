package com.pfcti.springdata.dto;

import com.pfcti.springdata.model.Cuenta;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class ProductoDto {

    private List<CuentaDto> cuentaDtos;
    private List<TarjetaDto> tarjetaDtos;
    private List<InversionDto> inversionDtos;

}
