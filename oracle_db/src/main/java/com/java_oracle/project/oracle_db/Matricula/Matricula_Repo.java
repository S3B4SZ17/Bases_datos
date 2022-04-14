package com.java_oracle.project.oracle_db.Matricula;

import java.util.List;

import com.java_oracle.project.oracle_db.Clientes.Clientes;

import org.springframework.stereotype.Repository;

@Repository
public interface Matricula_Repo {

    List<Matricula> findMatriculaByClient(Clientes client);

    int addMatricula(Matricula matricula);

    List<Matricula> findAllMatriculas();
}
