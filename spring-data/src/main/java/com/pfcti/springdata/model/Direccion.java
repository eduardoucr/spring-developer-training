package com.pfcti.springdata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String direccion;


    @ManyToOne
    @JoinColumn(name="cliente_id",referencedColumnName = "id")
    private Cliente cliente;

    private String nomenclatura;
}
