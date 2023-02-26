package com.sanket.airlinecheckin.repositories;

import com.sanket.airlinecheckin.models.Seat;
import com.sanket.airlinecheckin.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
