package com.pfcti.springdata.repository;


import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.model.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InversionRepository  extends JpaRepository<Inversion, Integer>, JpaSpecificationExecutor<Inversion> {

    void findByCliente_Id(int clienteId);
    void deleteAllByCliente_Id(int clienteId);
    List<Inversion> findByCliente_IdAndEstadoIsTrue(int clienteId);

}
