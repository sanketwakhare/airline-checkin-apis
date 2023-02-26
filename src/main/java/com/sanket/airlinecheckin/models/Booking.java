package com.sanket.airlinecheckin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking extends BaseModel{

    @ManyToOne
    private User user;

    @ManyToOne
    private Trip trip;

    @ManyToOne
    private Seat seat;
}
