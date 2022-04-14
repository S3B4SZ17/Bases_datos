package com.java_oracle.project.oracle_db.Matricula;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.java_oracle.project.oracle_db.Clientes.Clientes;

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
public class Matricula_Service implements Matricula_Repo {
    
    private static final Logger log = LoggerFactory.getLogger(Matricula_Service.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall_find_matricula;
    private SimpleJdbcCall simpleJdbcCall_add_matricula;
    
    // init SimpleJdbcCall
    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        // Intialization for the find_client procedure
        simpleJdbcCall_find_matricula = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("Matricula_pack")
            .withProcedureName("find_Matricula")
            .returningResultSet("o_matricula", BeanPropertyRowMapper.newInstance(Matricula.class));

        // Initialization for the add_client procedure.
        simpleJdbcCall_add_matricula = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("Matricula_pack")
            .withProcedureName("add_Matricula");
        
    }

    @Override
    public List<Matricula> findMatriculaByClient(Clientes client) {
         log.info("Calling find_matricula procedure");

        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("v_cliente", client.getIdcliente());
        
        Map result = simpleJdbcCall_find_matricula.execute(parameters_in);
        
        if (result == null) {
            log.warn("No matricula found with client ID");
            return Collections.emptyList();
        } else {
            return (List) result.get("o_matricula");
        }
    }

    @Override
    public int addMatricula(Matricula matricula) {
        log.info("Calling add_Matricula procedure");

        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("v_cliente", matricula.getCliente())
                .addValue("v_suscripcion", matricula.getSuscripcion())
                .addValue("v_nutricionista", matricula.getNutricion());
        
        try {
            simpleJdbcCall_add_matricula.execute(parameters_in);
            return 0;
        } catch (Exception e) {
            log.error(e.toString());
            return 1;
        }
    }

    @Override
    public List<Matricula> findAllMatriculas() {
        List<Matricula> matriculaList = jdbcTemplate.query("SELECT * FROM Matricula",
                BeanPropertyRowMapper.newInstance(Matricula.class));
        return matriculaList;
    }
    
    
}
