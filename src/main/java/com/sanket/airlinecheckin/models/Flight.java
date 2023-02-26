package com.sanket.airlinecheckin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Flight extends BaseModel{
    private String name;

    @ManyToOne
    private Airline airline;

    @OneToMany(mappedBy = "flight")
    private List<Trip> trip;

    @OneToMany(mappedBy = "flight")
    private List<Seat> seats;
}
