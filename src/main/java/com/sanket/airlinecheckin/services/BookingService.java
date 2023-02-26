package com.sanket.airlinecheckin.services;

import com.sanket.airlinecheckin.models.Booking;
import com.sanket.airlinecheckin.models.BookingStatus;
import com.sanket.airlinecheckin.models.Seat;
import com.sanket.airlinecheckin.models.Trip;
import com.sanket.airlinecheckin.repositories.BookingRepository;
import com.sanket.airlinecheckin.repositories.SeatRepository;
import com.sanket.airlinecheckin.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, TripRepository tripRepository, SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
        this.seatRepository = seatRepository;
    }

    public Booking initBooking(Long tripId, String seatNumber, BookingStatus bookingStatus) {
        Booking booking = new Booking();
        booking.setBookingStatus(bookingStatus);

        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isEmpty()) {
            // not found
            return null;
        }
        booking.setTrip(trip.get());

        Optional<Seat> seat = seatRepository.findSeatBySeatNumberAndFlight(seatNumber, trip.get().getFlight().getId());
        if(seat.isEmpty()) {
            // not found
            return null;
        }
        booking.setSeat(seat.get());

        return bookingRepository.saveAndFlush(booking);
    }
}
