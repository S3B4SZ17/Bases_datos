package com.java_oracle.project.oracle_db.Clientes;

import java.util.List;

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
@RequestMapping("clientes")
public class Clientes_controller {
    
    @Autowired
    private Clientes_service clientes_service;

    
    @GetMapping("/allClientes")
    public ResponseEntity<List<Clientes>> getClientes(){
        List<Clientes> listClientes = clientes_service.findAllClientes();
        return new ResponseEntity<>(listClientes, HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Clientes>> getClienteEmail(@RequestBody String email) {
        List<Clientes> listClientes = clientes_service.findClientByEmail(email);
        if (listClientes == null) {
            return new ResponseEntity<>(listClientes, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listClientes, HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addClient(@RequestBody Clientes client) {
        int result = clientes_service.addClient(client);
        if (result != 0) {
            return ResponseEntity.internalServerError().body("Error while trying to add a client");
        }
        return new ResponseEntity<>(HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }
}
