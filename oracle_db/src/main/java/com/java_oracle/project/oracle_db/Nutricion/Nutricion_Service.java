package com.java_oracle.project.oracle_db.Nutricion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class Nutricion_Service implements Nutricion_Repo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall_find_planNutri;
    private static final Logger log = LoggerFactory.getLogger(Nutricion_Service.class);

        // init SimpleJdbcCall
    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        simpleJdbcCall_find_planNutri = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("get_PlanNutri");

        
    }
    @Override
    public String descripcionPlanNutri(String nombre) {
        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("p_nombre", nombre);
        try {
            String plan_nutri = simpleJdbcCall_find_planNutri.executeFunction(String.class, parameters_in);
            return plan_nutri;
        } catch (Exception e) {
            log.error(e.toString());
            return "";
        }
    }

}