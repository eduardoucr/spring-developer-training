package com.pfcti.springdata.repository;


import com.pfcti.springdata.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository  extends JpaRepository<Cuenta, Integer> {
    void deleteAllByCliente_Id(int clienteId);
}
