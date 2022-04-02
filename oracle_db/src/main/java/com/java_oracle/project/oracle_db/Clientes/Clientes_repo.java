package com.java_oracle.project.oracle_db.Clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface Clientes_repo {
    Clientes findClienteByNombre(String nombre);

    List<Clientes> findAllClientes();
}
