package com.java_oracle.project.oracle_db.Clientes;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Clientes_repo {

    List<Clientes> findClientByEmail(String nombre);

    int addClient(Clientes client);

    List<Clientes> findAllClientes();
}
