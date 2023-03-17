package com.pfcti.springdata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cliente")
@Setter
@Getter
public class Cliente {
    @Id
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(columnDefinition = "varchar(50)")
    private String apellidos;
    @Column(length = 15)
    private String cedula;
    private String telefono;

}
