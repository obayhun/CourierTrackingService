package com.migros.demo.controller;
import com.migros.demo.models.Tracker;
import com.migros.demo.models.TrackerLogRequest;
import com.migros.demo.service.TrackerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;


@RestController
public class TrackerController {

    private static final Logger logger = LoggerFactory.getLogger(TrackerController.class);

    @Autowired
    private TrackerService trackerService;

    @PostMapping(value = "/log-courier")
    public ResponseEntity<?> createTrackerLog(@RequestBody TrackerLogRequest trackerRequest) {
        logger.info("-> {}: {}, {}", "createTrackerLog", trackerRequest.getLng(), trackerRequest.getLat());
        return ResponseEntity
                .status(HttpStatus.OK).body(trackerService.create(trackerRequest));
    }

    @PostMapping(value = "/total-distance/{courierID}")
    public ResponseEntity<?> getTotalDistance(@PathVariable int courierID){
        logger.info("-> {}: {}", "Calculating total distance for courier id:", courierID);
        double distance = trackerService.calculateTotalDistance(courierID);
        return ResponseEntity
                .status(HttpStatus.OK).body(distance+" meters ( ~" + (int) (distance/1000) +" km) travelled by courier : "+ courierID);
    }
}
