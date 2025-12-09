package com.example.Ride.App.controller;

import com.example.Ride.App.model.Ride;
import com.example.Ride.App.service.RideService;
import com.example.Ride.App.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    @Autowired
    private RideService rideService;

    @Autowired
    private JwtUtil jwtUtil;

    // View pending rides
    @GetMapping("/rides/requests")
    public List<Ride> getPendingRides() {
        return rideService.getPendingRides();
    }

    // Accept ride
    @PostMapping("/rides/{rideId}/accept")
    public Ride acceptRide(@PathVariable("rideId") String rideId, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String driverId = jwtUtil.extractUsername(token); // username stored in JWT

        return rideService.acceptRide(rideId, driverId);
    }
}
