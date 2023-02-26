package com.sanket.airlinecheckin.services;

import com.sanket.airlinecheckin.models.Airline;
import com.sanket.airlinecheckin.models.Flight;
import com.sanket.airlinecheckin.repositories.AirlineRepository;
import com.sanket.airlinecheckin.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirlineRepository airlineRepository) {
        this.flightRepository = flightRepository;
        this.airlineRepository = airlineRepository;
    }

    public Flight createFlight(String name, String airlineName) {
        Flight flight = new Flight();
        flight.setName(name);

        Optional<Airline> airline = airlineRepository.findByName(airlineName);
        if(airline.isEmpty()) {
            // not found
            return null;
        }
        flight.setAirline(airline.get());
        return flightRepository.saveAndFlush(flight);
    }
}
