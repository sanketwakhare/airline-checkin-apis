package com.sanket.airlinecheckin.models;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Entity
public class Seat extends BaseModel{
    @ManyToOne
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private String seatNumber;

    @OneToMany(mappedBy = "seat")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Booking> Booking;
}
