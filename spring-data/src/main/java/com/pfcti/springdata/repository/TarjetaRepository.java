package com.pfcti.springdata.repository;


import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface TarjetaRepository  extends JpaRepository<Tarjeta, Integer> , JpaSpecificationExecutor<Tarjeta> {

    void findByCliente_Id(int clienteId);

    List<Tarjeta> findByCliente_IdAndEstadoIsTrue(int clienteId);

}
