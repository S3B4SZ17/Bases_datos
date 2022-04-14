package com.java_oracle.project.oracle_db.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Staff {

    private int idStaff;
    private String nombre;
    private String apellido;
    private String correo;
    private String especialidad;
}