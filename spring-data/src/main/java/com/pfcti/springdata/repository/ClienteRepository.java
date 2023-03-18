package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}
