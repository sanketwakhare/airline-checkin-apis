package com.sanket.airlinecheckin;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import com.sanket.airlinecheckin.models.BookingStatus;
import com.sanket.airlinecheckin.models.SeatType;
import com.sanket.airlinecheckin.services.*;
import com.sanket.airlinecheckin.utils.RandomUserDataGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AirlineCheckinApisApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private TripService tripService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private BookingService bookingService;

    @Test
    @Order(1)
    void createUsers() {
        Faker faker = new Faker(new Locale("en-IND"));
        for (int i=0; i<50; i++) {
            String name = faker.name().fullName();
            String email = RandomUserDataGenerator.generateEmail(name);
            String phone = faker.phoneNumber().phoneNumber();
            userService.createUser(name, email, phone);
        }
    }

    @Test
    @Order(2)
    void createAirlines() {
        airlineService.createAirline("Air India");
        airlineService.createAirline("Indigo");
        airlineService.createAirline("Air Asia");
        airlineService.createAirline("SpiceJet");
        airlineService.createAirline("British Airways");
        airlineService.createAirline("Lufthansa");
    }

    @Test
    @Order(3)
    void createFlights() {
        Faker faker = new Faker(new Locale("en-IND"));
        flightService.createFlight(faker.aviation().aircraft(), "Air India");
        flightService.createFlight(faker.aviation().aircraft(), "Indigo");
        flightService.createFlight(faker.aviation().aircraft(), "Air Asia");
        flightService.createFlight(faker.aviation().aircraft(), "SpiceJet");
        flightService.createFlight(faker.aviation().aircraft(), "British Airways");
        flightService.createFlight(faker.aviation().aircraft(), "Lufthansa");
    }

    @Test
    @Order(4)
    void createTrips() {
        Faker faker = new Faker(new Locale("en-IND"));
        DateAndTime dateAndTime = faker.date();
        for (long i = 1; i <= 10; i++) {
            String source = faker.address().city();
            String destination = faker.address().city();
            while (destination.equals(source)) {
                destination = faker.address().city();
            }
            Date date = dateAndTime.future(30, TimeUnit.DAYS);
            tripService.createTrip((i % 7), source, destination, date.toInstant().toEpochMilli());
        }
    }

    @Test
    @Order(5)
    void createSeats() {
        for(long flightNumber = 1; flightNumber<= 6; flightNumber++) {
            SeatType seatType = SeatType.ECONOMY_CLASS;
            for (int seatNumber = 1; seatNumber <= 20; seatNumber++) {
                if (seatNumber < 5) {
                    seatType = SeatType.BUSINESS_CLASS;
                } else if (seatNumber > 5 && seatNumber < 15) {
                    seatType = SeatType.FIRST_CLASS;
                }
                seatService.createSeat(seatType, String.valueOf(seatNumber), flightNumber);
            }
        }
    }

    @Test
    @Order(6)
    void initializeBookings() {
        // trips
        for (long i = 1; i <= 10; i++) {
            for (int seatNumber = 1; seatNumber <= 20; seatNumber++) {
                bookingService.initBooking(i, String.valueOf(seatNumber), BookingStatus.AVAILABLE);
            }
        }
    }

}
