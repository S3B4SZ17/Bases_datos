package com.java_oracle.project.oracle_db.Horarios;


import org.springframework.stereotype.Repository;

@Repository
public interface Horarios_Repo {

    String findHorario(String horario);
}