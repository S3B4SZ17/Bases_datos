package com.java_oracle.project.oracle_db.Staff;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("staff")
public class Staff_Controller {

    @Autowired
    private Staff_Service service;


    @GetMapping("/allStaff")
    public ResponseEntity<List<Staff>> getStaff(){
        List<Staff> listStaff = service.findAllStaff();
        return new ResponseEntity<>(listStaff, HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }

    @GetMapping("/search")
    public ResponseEntity<List<Staff>> getClienteEmail(@RequestBody String email) {
        List<Staff> listStaff = service.findStaffByEmail(email);
        if (listStaff == null) {
            return new ResponseEntity<>(listStaff, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listStaff, HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addClient(@RequestBody Staff staff) {
        int result = service.addStaff(staff);
        if (result != 0) {
            return ResponseEntity.internalServerError().body("Error while trying to add a client");
        }
        return new ResponseEntity<>(HttpStatus.OK);
        //with the responseEntity class we are sending a response of OK to the client
    }
}
