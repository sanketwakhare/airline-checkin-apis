package com.sanket.airlinecheckin.repositories;

import com.sanket.airlinecheckin.models.Booking;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select b from Booking b where b.trip.id=:tripId and b.seat.id=:seatId and b.bookingStatus='AVAILABLE'")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Booking> findByTripIdAndSeatId(@Param("tripId") Long tripId,
                                            @Param("seatId") Long seatId);

    @Query(value = "select * from booking where trip_id=:tripId and booking_status='AVAILABLE' LIMIT 1 for UPDATE SKIP LOCKED",
            nativeQuery = true)
    Optional<Booking> findByTripIdAndSeatIdNative(@Param("tripId") Long tripId);
}
