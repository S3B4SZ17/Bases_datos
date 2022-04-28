package com.java_oracle.project.oracle_db.Horarios;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
@Service
public class Horarios_Service implements Horarios_Repo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall_find_horarios;
    private static final Logger log = LoggerFactory.getLogger(Horarios_Service.class);

        // init SimpleJdbcCall
    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        simpleJdbcCall_find_horarios = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("f_consulta_horarios");

        
    }

    @Override
    public String findHorario(String horario) {

        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("h_nombre", horario);
        try {
            String result = simpleJdbcCall_find_horarios.executeFunction(String.class, parameters_in);
            return result;
        } catch (Exception e) {
            log.error(e.toString());
            return "";
        }

    }
    
    @Override
    public List<Horarios> getAllHorarios() {
        log.info("Getting all Horarios");
         List<Horarios> horariosList = jdbcTemplate.query("SELECT * FROM Horarios",
                BeanPropertyRowMapper.newInstance(Horarios.class));
        return horariosList;
    }


}
