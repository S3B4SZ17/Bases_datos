package com.java_oracle.project.oracle_db.Suscripciones;

import com.java_oracle.project.oracle_db.Horarios.Horarios;
import com.java_oracle.project.oracle_db.Staff.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Suscripciones {

    private int idSuscripcion;
    private String Nombre;
    private String Description;
    private Staff Entrenador;
    private Horarios Horario;
    private Double Precio;
}