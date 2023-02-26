package com.sanket.airlinecheckin.repositories;

import com.sanket.airlinecheckin.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("select seat from Seat seat where seat.seatNumber=:seatNumber and seat.flight.id=:flightId")
    Optional<Seat> findSeatBySeatNumberAndFlight(@Param("seatNumber") String seatNumber,
                                                 @Param("flightId") Long flightId);
}
