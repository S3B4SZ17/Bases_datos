package com.java_oracle.project.oracle_db.Horarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("horarios")
public class Horarios_Controller {
    
    @Autowired
    private Horarios_Service service;

    
    @GetMapping("/allHorarios")
    public ResponseEntity<List<Horarios>> getClientes(){
        List<Horarios> listHorarios = service.getAllHorarios();
        return new ResponseEntity<>(listHorarios, HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }
}
