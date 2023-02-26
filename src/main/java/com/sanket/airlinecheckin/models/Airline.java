package com.sanket.airlinecheckin.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Entity
@Getter
@Setter
public class Airline extends BaseModel {
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "airline")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Flight> flight;
}
