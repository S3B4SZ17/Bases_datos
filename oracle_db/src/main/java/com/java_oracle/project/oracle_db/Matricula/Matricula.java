package com.java_oracle.project.oracle_db.Matricula;

import com.java_oracle.project.oracle_db.Clientes.Clientes;

import com.java_oracle.project.oracle_db.Nutricion.Nutricion;
import com.java_oracle.project.oracle_db.Suscripciones.Suscripciones;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Matricula {

    private int idMatricula;
    private Clientes cliente;
    private Suscripciones suscripcion;
    private Nutricion nutricion;
}