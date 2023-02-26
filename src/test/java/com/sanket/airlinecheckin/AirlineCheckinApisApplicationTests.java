package com.sanket.airlinecheckin;

import com.github.javafaker.Aviation;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import com.sanket.airlinecheckin.services.AirlineService;
import com.sanket.airlinecheckin.services.FlightService;
import com.sanket.airlinecheckin.services.TripService;
import com.sanket.airlinecheckin.services.UserService;
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

    @Test
    void contextLoads() {
        System.out.println("tests started");
    }

    @Test
    @Order(1)
    void createUsers() {
        Faker faker = new Faker(new Locale("en-IND"));
        for (int i=0; i<10; i++) {
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
        flightService.createFlight(faker.aviation().aircraft(), "Air India");
        flightService.createFlight(faker.aviation().aircraft(), "Indigo");
        flightService.createFlight(faker.aviation().aircraft(), "Indigo");
        flightService.createFlight(faker.aviation().aircraft(), "Indigo");
        flightService.createFlight(faker.aviation().aircraft(), "Air Asia");
        flightService.createFlight(faker.aviation().aircraft(), "Air Asia");
        flightService.createFlight(faker.aviation().aircraft(), "SpiceJet");
        flightService.createFlight(faker.aviation().aircraft(), "British Airways");
        flightService.createFlight(faker.aviation().aircraft(), "Lufthansa");
    }

    @Test
    @Order(4)
    void createTrips() {
        Faker faker = new Faker(new Locale("en-IND"));
        Aviation aviation = faker.aviation();
        DateAndTime dateAndTime = faker.date();
        for(long i=0; i< 20; i++) {
            String source = aviation.airport();
            String destination = aviation.airport();
            while (destination.equals(source)) {
                destination = aviation.airport();
            }
            Date date = dateAndTime.future(30, TimeUnit.DAYS);
            tripService.createTrip((i % 10) + 1, source, destination, date.toInstant().toEpochMilli());
        }
    }

}
