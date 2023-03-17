package com.pfcti.springdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Direccion {
    @Id
    private int id;
    private String direccion;
    private String nomenclatura;

    @ManyToOne
    @JoinColumn(name="cliente_id",referencedColumnName = "id")
    private Cliente cliente;
}
