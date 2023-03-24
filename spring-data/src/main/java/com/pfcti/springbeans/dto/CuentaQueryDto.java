package com.pfcti.springbeans.dto;

import lombok.Data;

@Data
public class CuentaQueryDto {

    private Integer valorBusqueda;
    private CuentaQueryType tipoBusqueda;

}
