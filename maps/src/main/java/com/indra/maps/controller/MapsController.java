package com.indra.maps.controller;

import com.indra.maps.model.Address;
import com.indra.maps.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps")
public class MapsController {

    @Autowired
    MockAddressRepository mockAddressRepository;

    @GetMapping
    public ResponseEntity<Address> get(@RequestParam Double lat, @RequestParam Double lon) {

        return new ResponseEntity<>(MockAddressRepository.getRandom(), HttpStatus.OK);
    }
}
