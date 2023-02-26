package com.sanket.airlinecheckin.repositories;

import com.sanket.airlinecheckin.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

}
