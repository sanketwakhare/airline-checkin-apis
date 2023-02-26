package com.sanket.airlinecheckin.repositories;

import com.sanket.airlinecheckin.models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    @Query("select airline from Airline airline where airline.name=:name")
    Optional<Airline> findByName(@Param("name") String airlineName);
}
