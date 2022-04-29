package com.java_oracle.project.oracle_db.Matricula;

import java.util.List;

import com.java_oracle.project.oracle_db.Clientes.Clientes;
import com.java_oracle.project.oracle_db.Clientes.Clientes_service;
import com.java_oracle.project.oracle_db.Nutricion.Nutricion;
import com.java_oracle.project.oracle_db.Nutricion.Nutricion_Service;
import com.java_oracle.project.oracle_db.Suscripciones.Suscripciones;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Matricula {

    @Autowired
    private Clientes_service clientes_service;

    @Autowired
    private Nutricion_Service nutricion_service;

    private int idMatricula;
    private Clientes cliente;
    private Suscripciones suscripcion;
    private Nutricion nutricion;

    public Matricula(String cliente_correo, int idSucripcion, int idNutricion) {
        List<Clientes> list_cliente = clientes_service.findClientByEmail(cliente_correo);
        cliente = list_cliente.get(0);
    }
}