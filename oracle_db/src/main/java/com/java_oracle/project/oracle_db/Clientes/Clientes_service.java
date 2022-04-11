package com.java_oracle.project.oracle_db.Clientes;

import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;


@Service
public class Clientes_service implements Clientes_repo {
    
    private static final Logger log = LoggerFactory.getLogger(Clientes_service.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall_find_client;
    
    // init SimpleJdbcCall
    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall_find_client = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_client")
                .returningResultSet("o_client", BeanPropertyRowMapper.newInstance(Clientes.class));
        
    }
    @Override
    public Clientes findClienteByNombre(String nombre) {
        Clientes cliente = jdbcTemplate.queryForObject("SELECT * FROM Clientes WHERE NOMBRE = ?",
                BeanPropertyRowMapper.newInstance(Clientes.class), nombre);
        return cliente;
    }

    @Override
    public List<Clientes> findClienteByEmail(String email) {
        
        log.info("Calling find_client procedure");

        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("v_correo", email);
        
        Map result = simpleJdbcCall_find_client.execute(parameters_in);
        
        if (result == null) {
            log.warn("No clients found with email provided");
            return Collections.emptyList();
        } else {
            return (List) result.get("o_client");
        }

    }

    @Override
    public List<Clientes> findAllClientes() {
        List<Clientes> clientesList = jdbcTemplate.query("SELECT * FROM Clientes",
                BeanPropertyRowMapper.newInstance(Clientes.class));
        // System.out.println(clientesList);
        return clientesList;
    }
    
}
