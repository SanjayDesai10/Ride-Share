package com.example.Ride.App.controller;

import com.example.Ride.App.dto.CreateRideRequest;
import com.example.Ride.App.model.Ride;
import com.example.Ride.App.model.User;
import com.example.Ride.App.repository.UserRepository;
import com.example.Ride.App.service.RideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/rides")
    public ResponseEntity<Ride> createRide(@RequestBody CreateRideRequest req, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Ride ride = rideService.createRide(req, user.getId());
        return ResponseEntity.ok(ride);
    }

    @GetMapping("/rides/my")
    public ResponseEntity<List<Ride>> getMyRides(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.ok(rideService.getUserRides(user.getId()));
    }

    @PostMapping("/rides/{rideId}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable String rideId, Principal principal) {
        String callerUsername = principal.getName();  // JWT subject = username
        Ride ride = rideService.completeRide(rideId, callerUsername);
        return ResponseEntity.ok(ride);
    }

}
