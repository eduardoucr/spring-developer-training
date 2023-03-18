package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository  extends JpaRepository<Direccion, Integer> {
}
