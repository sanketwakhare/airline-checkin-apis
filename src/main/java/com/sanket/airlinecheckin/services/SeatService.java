package com.sanket.airlinecheckin.services;

import com.sanket.airlinecheckin.models.Flight;
import com.sanket.airlinecheckin.models.Seat;
import com.sanket.airlinecheckin.models.SeatType;
import com.sanket.airlinecheckin.repositories.FlightRepository;
import com.sanket.airlinecheckin.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    private final FlightRepository flightRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository, FlightRepository flightRepository) {
        this.seatRepository = seatRepository;
        this.flightRepository = flightRepository;
    }

    public Seat createSeat(SeatType seatType, String seatNumber, Long flightId) {
        Seat seat = new Seat();
        seat.setSeatType(seatType);
        seat.setSeatNumber(seatNumber);

        Optional<Flight> flight = flightRepository.findById(flightId);
        if (flight.isEmpty()) {
            // not found
            return null;
        }
        seat.setFlight(flight.get());
        return seatRepository.saveAndFlush(seat);
    }
}
