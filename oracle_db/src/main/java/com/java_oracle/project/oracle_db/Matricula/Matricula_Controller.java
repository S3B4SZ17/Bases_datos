package com.java_oracle.project.oracle_db.Matricula;

import java.util.List;

import com.java_oracle.project.oracle_db.Clientes.Clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("matricula")
public class Matricula_Controller {
    
    @Autowired
    private Matricula_Service service;

    
    @GetMapping("/allMatriculas")
    public ResponseEntity<List<Matricula>> getMatriculas(){
        List<Matricula> listMatriculas = service.findAllMatriculas();
        return new ResponseEntity<>(listMatriculas, HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Matricula>> getMatriculasByCient(@RequestBody Clientes cliente) {
        List<Matricula> listMatriculas = service.findMatriculaByClient(cliente);
        if (listMatriculas == null) {
            return new ResponseEntity<>(listMatriculas, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listMatriculas, HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMatricula(@RequestBody Matricula matricula) {
        int result = service.addMatricula(matricula);
        if (result != 0) {
            return ResponseEntity.internalServerError().body("Error while trying to add a Matricula");
        }
        return new ResponseEntity<>(HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }
}
