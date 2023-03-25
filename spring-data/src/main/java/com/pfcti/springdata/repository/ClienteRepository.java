package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Cliente;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

    List<Cliente> findClientesByPaisNacimientoAndCuentas_EstadoIsTrue(String paisNacimiento);

    List<Cliente> findClientesByPaisNacimientoAndTarjetas_EstadoIsFalse(String paisNacimiento);


    List<Cliente>  findByCedula(String cedula);

    List<Cliente> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String texto1, String text2);

    @Query(value = "select c from Cliente c where c.apellidos= :apellidos")
    List<Cliente> buscarPorApellidos(String apellidos);


    @Query(value = "select nombre, apellidos, cedula, telefono, id from Cliente  c where c.apellidos := apellidos",
            nativeQuery = true)
    List<Tuple> buscarPorApellidosNativo(String apellidos);

    Cliente findClienteById(int id);


}

