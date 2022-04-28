package com.java_oracle.project.oracle_db.Nutricion;

import java.util.List;

import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("nutricion")
public class Nutricion_Controller {

    @Autowired
    private Nutricion_Service service;
    
    @GetMapping("/search")
    public ResponseEntity<String> getMatriculasByCient(@RequestBody String nombre) {
        JsonObject res = new JsonObject();
        String nutri_des = service.descripcionPlanNutri(nombre);
        res.addProperty("descripcion", nutri_des);
        if (nutri_des == "") {
            return new ResponseEntity<>(nutri_des, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }
}