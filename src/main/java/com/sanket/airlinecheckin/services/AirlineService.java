package com.sanket.airlinecheckin.services;

import com.sanket.airlinecheckin.models.Airline;
import com.sanket.airlinecheckin.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public Airline createAirline(String name) {
        Airline airline = new Airline();
        airline.setName(name);
        return airlineRepository.saveAndFlush(airline);
    }
}
