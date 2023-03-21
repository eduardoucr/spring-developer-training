package com.pfcti.springdata.dto;

import com.pfcti.springdata.model.Cuenta;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class ProductoDto {

    private List<CuentaDto> cuentaDto;
    private List<TarjetaDto> tarjetaDto;
    private List<InversionDto> inversionDto;

}
