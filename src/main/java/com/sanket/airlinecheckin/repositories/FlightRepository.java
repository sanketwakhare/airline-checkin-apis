package com.sanket.airlinecheckin.repositories;

import com.sanket.airlinecheckin.models.Airline;
import com.sanket.airlinecheckin.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select flight from Flight flight where flight.name=:name")
    Optional<Flight> findByName(@Param("name") String flightName);
}
