package com.java_oracle.project.oracle_db.Staff;

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
public class Staff_Service implements Staff_Repo {

    private static final Logger log = LoggerFactory.getLogger(Staff_Service.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall_find_staff;
    private SimpleJdbcCall simpleJdbcCall_add_staff;

    // init SimpleJdbcCall
    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        // Intialization for the find_client procedure
        simpleJdbcCall_find_staff = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("staff_pack")
                .withProcedureName("find_staff")
                .returningResultSet("o_staff", BeanPropertyRowMapper.newInstance(Staff.class));

        // Initialization for the add_client procedure.
        simpleJdbcCall_add_staff = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("staff_pack")
                .withProcedureName("add_staff");

    }

    @Override
    public List<Staff> findStaffByEmail(String email) {
        log.info("Calling find_staff procedure");

        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("v_correo", email);

        Map result = simpleJdbcCall_find_staff.execute(parameters_in);

        if (result == null) {
            log.warn("No Staff found with email");
            return Collections.emptyList();
        } else {
            return (List) result.get("o_staff");
        }
    }

    @Override
    public int addStaff(Staff staff) {
        log.info("Calling add_staff procedure");

        SqlParameterSource parameters_in = new MapSqlParameterSource()
                .addValue("v_nombre", staff.getNombre())
                .addValue("v_apellido", staff.getApellido())
                .addValue("v_correo", staff.getCorreo())
                .addValue("v_especialidad", staff.getEspecialidad());

        try {
            simpleJdbcCall_add_staff.execute(parameters_in);
            return 0;
        } catch (Exception e) {
            log.error(e.toString());
            return 1;
        }
    }

    @Override
    public List<Staff> findAllStaff() {
        List<Staff> staffList = jdbcTemplate.query("SELECT * FROM Staff",
                BeanPropertyRowMapper.newInstance(Staff.class));
        // System.out.println(clientesList);
        return staffList;
    }
}
