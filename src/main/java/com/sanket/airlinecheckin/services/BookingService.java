package com.sanket.airlinecheckin.services;

import com.github.javafaker.Faker;
import com.sanket.airlinecheckin.models.*;
import com.sanket.airlinecheckin.repositories.BookingRepository;
import com.sanket.airlinecheckin.repositories.SeatRepository;
import com.sanket.airlinecheckin.repositories.TripRepository;
import com.sanket.airlinecheckin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
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

    @Transactional(isolation = Isolation.SERIALIZABLE)
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
    public void bookSeat(Long tripId, String seatNumber, Long userId) {

        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isEmpty()) {
            // not found
            return;
        }
        Flight flight = trip.get().getFlight();
        Optional<Seat> seat = seatRepository.findSeatBySeatNumberAndFlight(seatNumber, flight.getId());
        if(seat.isEmpty()) {
            // not found
            return;
        }

        Optional<Booking> booking = bookingRepository.findByTripIdAndSeatId(tripId, seat.get().getId());
        // native query similar to above hql query->
        // Optional<Booking> booking = bookingRepository.findByTripIdAndSeatIdNative(tripId);
        if(booking.isEmpty()) {
            // not found
            return;
        }
        Booking dbBooking = booking.get();
        if(BookingStatus.AVAILABLE.equals(dbBooking.getBookingStatus())) {
            dbBooking.setBookingStatus(BookingStatus.LOCKED);
        } else {
            // not available
            return;
        }

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            // not found
            return;
        }
        dbBooking.setUser(user.get());
        dbBooking.setBookingStatus(BookingStatus.BOOKED);
        bookingRepository.saveAndFlush(dbBooking);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void allocateSeats(Long tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if (trip.isEmpty()) {
            // not found
            return;
        }

        Faker faker = new Faker(new Locale("en-IND"));
        for (int i = 1; i <= 20; i++) {
            long randomUserId = faker.number().numberBetween(1L, 50L);
            String seatNumber = String.valueOf(i);
            bookSeat(tripId, seatNumber, randomUserId);
        }
    }
}
