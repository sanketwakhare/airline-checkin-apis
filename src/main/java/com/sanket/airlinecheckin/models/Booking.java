package com.sanket.airlinecheckin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking extends BaseModel{

    @ManyToOne
    private User user;

    @ManyToOne
    private Trip trip;

    @ManyToOne
    private Seat seat;
}
