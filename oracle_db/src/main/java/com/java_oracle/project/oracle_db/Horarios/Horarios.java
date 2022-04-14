package com.java_oracle.project.oracle_db.Horarios;

import com.java_oracle.project.oracle_db.Staff.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Horarios {

    private int idHorario;
    private String Nombre;
    private String Description;
    private Staff Entrenador;
}