package com.example.Ride.App.service;

import com.example.Ride.App.dto.CreateRideRequest;
import com.example.Ride.App.model.Ride;
import com.example.Ride.App.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride createRide(CreateRideRequest req, String userId) {
        Ride ride = new Ride();
        ride.setUserId(userId);
        ride.setPickupLocation(req.getPickupLocation());
        ride.setDropLocation(req.getDropLocation());
        ride.setStatus("REQUESTED");

        return rideRepository.save(ride);
    }

    public List<Ride> getUserRides(String userId) {
        return rideRepository.findByUserId(userId);
    }

    public List<Ride> getPendingRides() {
        return rideRepository.findByStatus("REQUESTED");
    }

    public Ride acceptRide(String rideId, String driverId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (!"REQUESTED".equals(ride.getStatus())) {
            throw new RuntimeException("Ride already accepted or completed");
        }

        ride.setDriverId(driverId);
        ride.setStatus("ACCEPTED");

        return rideRepository.save(ride);
    }

    public Ride completeRide(String rideId, String callerId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (!"ACCEPTED".equals(ride.getStatus())) {
            throw new RuntimeException("Ride not in progress");
        }

        // User OR driver can complete the ride
        if (!callerId.equals(ride.getUserId()) && !callerId.equals(ride.getDriverId())) {
            throw new RuntimeException("Not allowed to complete this ride");
        }

        ride.setStatus("COMPLETED");
        return rideRepository.save(ride);
    }
}
