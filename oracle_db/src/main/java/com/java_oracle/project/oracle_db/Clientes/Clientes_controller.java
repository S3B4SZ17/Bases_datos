package com.java_oracle.project.oracle_db.Clientes;

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
@RequestMapping("clientes")
public class Clientes_controller {
    
    @Autowired
    private Clientes_service clientes_service;

    
    @GetMapping("/allClientes")
    public ResponseEntity<List<Clientes>> getAllUsers(){
        List<Clientes> listClientes = clientes_service.getAllClientes();
        System.out.println(listClientes.toString());
        return new ResponseEntity<>(listClientes, HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }

}
