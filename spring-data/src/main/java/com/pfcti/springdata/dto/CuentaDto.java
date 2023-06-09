package com.pfcti.springdata.dto;

import com.pfcti.springdata.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class CuentaDto {
    private Boolean estado;
    private int id;

    private String numero;
    private String tipo;

    private int idCliente;



}
