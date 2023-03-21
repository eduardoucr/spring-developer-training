package com.pfcti.springdata.dto;

import lombok.Data;

@Data
public class CuentaDto {
    private Boolean estado;
    private int id;

    private String numero;
    private String tipo;


}
