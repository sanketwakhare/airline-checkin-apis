package com.sanket.airlinecheckin.utils;

import java.util.Random;

public class RandomUserDataGenerator {
    private static final String[] VOWELS = {"a", "e", "i", "o", "u", "y"};
    private static final String[] CONSONANTS = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m",
            "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"};
    private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "example.com"};
    private static final Random rand = new Random();

    public static String generateName() {
        StringBuilder sb = new StringBuilder();
        int length = rand.nextInt(5) + 4;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                sb.append(VOWELS[rand.nextInt(VOWELS.length)]);
            } else {
                sb.append(CONSONANTS[rand.nextInt(CONSONANTS.length)]);
            }
        }
        return sb.toString().substring(0, 1).toUpperCase() + sb.toString().substring(1);
    }

    public static int generateAge() {
        return rand.nextInt(63) + 18;
    }

    public static String generateEmail(String name) {
        String domain = DOMAINS[rand.nextInt(DOMAINS.length)];
        return name.toLowerCase().trim().replaceAll(" ", "_") + "@" + domain;
    }

    public static String generatePhoneNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("+91-9");
        for (int i = 0; i < 9; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String name = generateName();
        int age = generateAge();
        String email = generateEmail(name);
        String phone = generatePhoneNumber();
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    }
}