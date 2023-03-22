package com.pfcti.springbeans.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class ClienteQueryDto {
    private String textoBusqueda;
    private ClienteQueryType tipoBusqueda;

}
