package com.sanket.airlinecheckin.services;

import com.sanket.airlinecheckin.models.Flight;
import com.sanket.airlinecheckin.models.Trip;
import com.sanket.airlinecheckin.repositories.FlightRepository;
import com.sanket.airlinecheckin.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    private final FlightRepository flightRepository;

    @Autowired
    public TripService(TripRepository tripRepository, FlightRepository flightRepository) {
        this.tripRepository = tripRepository;
        this.flightRepository = flightRepository;
    }

    public Trip createTrip(Long flightId, String source, String destination, Long startTime) {
        Trip trip = new Trip();
        trip.setSource(source);
        trip.setDestination(destination);
        trip.setStartTime(startTime);

        Optional<Flight> flight = flightRepository.findById(flightId);
        if (flight.isEmpty()) {
            // not found
            return null;
        }
        trip.setFlight(flight.get());
        return tripRepository.saveAndFlush(trip);
    }
}
