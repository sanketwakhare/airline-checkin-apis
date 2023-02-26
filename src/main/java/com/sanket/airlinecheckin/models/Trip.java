package com.sanket.airlinecheckin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.Instant;
import java.util.List;

@Entity
public class Trip extends BaseModel {
    @ManyToOne
    private Flight flight;

    private Instant startTime;

    private String source;

    private String destination;

    @OneToMany(mappedBy = "trip")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Booking> Booking;
}
