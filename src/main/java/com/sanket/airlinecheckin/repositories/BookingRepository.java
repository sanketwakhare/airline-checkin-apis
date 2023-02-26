package com.sanket.airlinecheckin.repositories;

import com.sanket.airlinecheckin.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select b from Booking b where b.trip.id=:tripId and b.seat.id=:seatId", nativeQuery = false)
    Optional<Booking> findByTripIdAndSeatId(@Param("tripId") Long tripId,
                                            @Param("seatId") Long seatId);

    @Query(value = "select * from booking where trip_id=:tripId and seat_id=:seatId for UPDATE", nativeQuery = true)
    Optional<Booking> findByTripIdAndSeatIdNative(@Param("tripId") Long tripId,
                                            @Param("seatId") Long seatId);
}
