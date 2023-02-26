package com.sanket.airlinecheckin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Entity
public class Airline extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "airline")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Flight> flight;
}
