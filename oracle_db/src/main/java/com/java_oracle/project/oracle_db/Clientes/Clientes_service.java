package com.java_oracle.project.oracle_db.Clientes;

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
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class Clientes_service implements Clientes_repo {
    
    private static final Logger log = LoggerFactory.getLogger(Clientes_service.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall_find_client;
    private SimpleJdbcCall simpleJdbcCall_add_client;
    
    // init SimpleJdbcCall
    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        // Intialization for the find_client procedure
        simpleJdbcCall_find_client = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("clientes_pack")
            .withProcedureName("find_client")
            .returningResultSet("o_client", BeanPropertyRowMapper.newInstance(Clientes.class));

        // Initialization for the add_client procedure.
        simpleJdbcCall_add_client = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("clientes_pack")
            .withProcedureName("clientes_pack.add_client");
        
    }

    @Override
    public List<Clientes> findClientByEmail(String email) {
        
        log.info("Calling find_client procedure");

        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("v_correo", email);
        
        Map result = simpleJdbcCall_find_client.execute(parameters_in);
        
        if (result == null) {
            log.warn("No clients found with email");
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
    
    @Override
    public void addClient(Clientes client) {
        log.info("Calling add_client procedure");

        System.out.println(client);

        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("v_nombre", client.getNombre())
                .addValue("v_apellido", client.getApellido())
                .addValue("v_meses_activos", client.getMeses_activos())
                .addValue("v_correo", client.getCorreo());
        
        Map result = simpleJdbcCall_add_client.execute(parameters_in);

        System.out.println(result);
        
        if (result == null) {
            log.warn("An error occured while adding a new Client ...");
        } else {
            System.out.println(result);
        }
        
    }
    
    
}
