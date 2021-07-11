package com.migros.demo.controller;

import com.migros.demo.models.Courier;
import com.migros.demo.service.CourierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CourierController {

    private static final Logger logger = LoggerFactory.getLogger(CourierController.class);

    @Autowired
    private CourierService courierService;

    @PostMapping(value = "/add-courier")
    public ResponseEntity<?> createTrackerLog(@RequestBody Courier courierRequest) {
        logger.info("-> {}: {}", "createTrackerLog", courierRequest.getName());
        int id = courierService.create(courierRequest);
        return ResponseEntity
                .status(HttpStatus.OK).body(id);
    }

}
