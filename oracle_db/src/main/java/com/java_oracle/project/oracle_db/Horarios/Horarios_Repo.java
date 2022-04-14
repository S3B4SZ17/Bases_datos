package com.java_oracle.project.oracle_db.Horarios;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Horarios_Repo {

    List<Horarios> getAllHorarios();
}