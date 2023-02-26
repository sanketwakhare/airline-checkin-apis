package com.sanket.airlinecheckin.utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomGenerator {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en-IND"));

        for (int i=0; i<120; i++) {
            String name = faker.name().fullName();
            System.out.println(name);
            String email = RandomUserDataGenerator.generateEmail(name);
            System.out.println(email);
        }

        for(int i=0; i<10; i++) {
            String aircraft = faker.aviation().aircraft();
            System.out.println(aircraft);
        }

        for(int i=0; i<10; i++) {
            String airport = faker.aviation().airport();
            System.out.println(airport);
        }
    }
}