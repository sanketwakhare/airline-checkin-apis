package com.sanket.airlinecheckin.services;

import com.sanket.airlinecheckin.models.*;
import com.sanket.airlinecheckin.repositories.BookingRepository;
import com.sanket.airlinecheckin.repositories.SeatRepository;
import com.sanket.airlinecheckin.repositories.TripRepository;
import com.sanket.airlinecheckin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, TripRepository tripRepository, SeatRepository seatRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookSeat(Long tripId, String seatNumber, Long userId) {

        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isEmpty()) {
            // not found
            return null;
        }
        Flight flight = trip.get().getFlight();
        Optional<Seat> seat = seatRepository.findSeatBySeatNumberAndFlight(seatNumber, flight.getId());
        if(seat.isEmpty()) {
            // not found
            return null;
        }

        Optional<Booking> booking = bookingRepository.findByTripIdAndSeatId(tripId, seat.get().getId());
        if(booking.isEmpty()) {
            // not found
            return null;
        }
        Booking dbBooking = booking.get();
        if(BookingStatus.AVAILABLE.equals(dbBooking.getBookingStatus())) {
            dbBooking.setBookingStatus(BookingStatus.LOCKED);
        } else {
            // not available
            return null;
        }

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            // not found
            return null;
        }
        dbBooking.setUser(user.get());
        dbBooking.setBookingStatus(BookingStatus.BOOKED);
        return bookingRepository.saveAndFlush(dbBooking);
    }
}
