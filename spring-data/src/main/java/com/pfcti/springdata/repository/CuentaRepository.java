package com.pfcti.springdata.repository;


import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CuentaRepository  extends JpaRepository<Cuenta, Integer> ,  JpaSpecificationExecutor<Cuenta> {
    void deleteAllByCliente_Id(int clienteId);

    void findByCliente_Id(int clienteId);

    List<Cuenta> findByCliente_IdAndEstadoIsTrue(int clienteId);

}
