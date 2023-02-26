package com.sanket.airlinecheckin.models;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Table(name = "users")
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Booking> Booking;
}
