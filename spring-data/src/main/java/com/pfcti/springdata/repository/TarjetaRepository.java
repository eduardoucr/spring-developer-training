package com.pfcti.springdata.repository;


import com.pfcti.springdata.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository  extends JpaRepository<Tarjeta, Integer> {
}
