package com.java_oracle.project.oracle_db.Nutricion;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Nutricion_Repo {

    String descripcionPlanNutri(String nombre);
}