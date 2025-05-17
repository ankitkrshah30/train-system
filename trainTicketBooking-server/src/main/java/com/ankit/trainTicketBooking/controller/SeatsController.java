package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seats")
public class SeatsController {

    @Autowired
    public SeatService seatService;

    @PostMapping("/create/{trainNumber}")
    public ResponseEntity<?> createSeats(@PathVariable String trainNumber){
        try{
            seatService.seedSeatsForTrain(trainNumber);
            return new ResponseEntity<>("Seats have been successfully added to the train -"+trainNumber,HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Seats may have been added or train Number is wrong.",HttpStatus.BAD_REQUEST);
        }
    }
}
