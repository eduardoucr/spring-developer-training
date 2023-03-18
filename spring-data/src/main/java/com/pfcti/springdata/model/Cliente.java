package com.pfcti.springdata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="cliente")
@Setter
@Getter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(columnDefinition = "varchar(50)")
    private String apellidos;
    @Column(length = 15)
    private String cedula;
    private String telefono;


    @OneToMany(mappedBy = "cliente")
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "cliente")
   private List<Tarjeta> tarjetas;
    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;


}
