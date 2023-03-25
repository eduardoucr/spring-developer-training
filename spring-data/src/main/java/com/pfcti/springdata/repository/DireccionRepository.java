package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DireccionRepository  extends JpaRepository<Direccion, Integer> {
    void deleteAllByCliente_Id(int clienteId);

    List<Direccion> findByCliente_Id(int clienteId);

}
