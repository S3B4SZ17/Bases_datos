package com.java_oracle.project.oracle_db.Horarios;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Horarios_Service implements Horarios_Repo {
    
    private static final Logger log = LoggerFactory.getLogger(Horarios_Service.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Horarios> getAllHorarios() {
        log.info("Getting all Horarios");
         List<Horarios> horariosList = jdbcTemplate.query("SELECT * FROM Horarios",
                BeanPropertyRowMapper.newInstance(Horarios.class));
        return horariosList;
    }
    
    
}
