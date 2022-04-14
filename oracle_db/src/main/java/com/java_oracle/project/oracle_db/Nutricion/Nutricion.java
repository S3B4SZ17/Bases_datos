package com.java_oracle.project.oracle_db.Nutricion;

import com.java_oracle.project.oracle_db.Staff.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Nutricion {

    private int idNutricion;
    private String nombre;
    private String descripcion;
    private Staff nutricionista;
}