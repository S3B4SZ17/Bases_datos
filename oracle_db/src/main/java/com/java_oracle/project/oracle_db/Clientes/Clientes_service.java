package com.java_oracle.project.oracle_db.Clientes;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Clientes_service implements Clientes_repo{
    
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Clientes> findClienteByNombre(String nombre) {
        Optional<Clientes> cliente = jdbcTemplate.queryForObject("SELECT * FROM Clientes WHERE Nombre = ?",
        (rs,rowNum) -> 
            Optional.of(new Clientes(   
                    rs.getLong("idCliente"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getInt("Meses_Activos")
                    )
                ));
        return cliente;
    }

    @Override
    public List<Clientes> findAllClientes() {
        List<Clientes> clientesList = jdbcTemplate.query("SELECT * FROM Clientes",
        (rs,rowNum) -> 
            new Clientes(
                    rs.getLong("idCliente"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getInt("Meses_Activos")
                    )
                );
        System.out.println(clientesList);
        
        return clientesList;
    }

    
}
