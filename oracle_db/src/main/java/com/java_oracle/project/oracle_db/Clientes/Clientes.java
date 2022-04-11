package com.java_oracle.project.oracle_db.Clientes;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Clientes {

    private int idcliente;
    private String apellido;
    private String nombre;
    private int meses_activos;
    private String correo;
}
