package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Cliente;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findClientesByPaisNacimientoAndCuentas_EstadoIsTrue(String paisNacimiento);

    List<Cliente> findClientesByPaisNacimientoAndTarjetas_EstadoIsFalse(String paisNacimiento);




    @Query(value = "select c from Cliente c where c.apellidos= :apellidos")
    List<Cliente> buscarPorApellidos(String apellidos);

    @Query(value = "select nombre, apellidos, cedula, telefono, id from Cliente c where c.apellidos := apellidos",
            nativeQuery = true)
    List<Tuple> buscarPorApellidosNativo(String apellidos);


}

