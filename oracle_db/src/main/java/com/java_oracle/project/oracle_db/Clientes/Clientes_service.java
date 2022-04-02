package com.java_oracle.project.oracle_db.Clientes;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Clientes_service implements Clientes_repo{
    
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Clientes findClienteByNombre(String nombre) {
        Clientes cliente = jdbcTemplate.queryForObject("SELECT * FROM Clientes WHERE NOMBRE = ?",
                BeanPropertyRowMapper.newInstance(Clientes.class), nombre);
        return cliente;
    }

    @Override
    public List<Clientes> findAllClientes() {
        List<Clientes> clientesList = jdbcTemplate.query("SELECT * FROM Clientes",
                BeanPropertyRowMapper.newInstance(Clientes.class));
        // System.out.println(clientesList);
        return clientesList;
    }
    
}
