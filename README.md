# Design Airline Check-in System

### Requirements Gathering
1. There are multiple Airlines
2. Every Airline has multiple Flights
3. Each Flight has 120 seats
4. Each Flight has multiple Trips
5. User can book a seat for available Trip
6. Once seat is assigned to a passenger, the same seat can not be reassigned or transferred
7. Multiple people should not be assigned to same seat

---

### Non Functions Requirements
1. Check-in should be as fast as possible
2. When a passenger is booking a seat, it should not wait for other passengers bookings
3. Ensure data is always in consistent state
4. Ensure system is free of deadlocks
5. Throughput should not be affected by db locking

---

### Database Setup
    postgres=# create user sanket with password 'sanket';
    
    CREATE ROLE
    
    postgres=# create database airline__checkin;
    
    CREATE DATABASE
    
    postgres=# grant all privileges on database airline__checkin to sanket;
    
    GRANT

---

### Entities
1. User

        id | name | email | phone
2. Airline

        id | name
3. Flight

        id | name | airline_id
4. Trip

        id | source | destination | starttime | flight_id
5. Seat

        id | seat_number | seat_type | flight_id
6. Booking

        id | booking_status | seat_id | trip_id | user_id
---

### Relationships

      User (1 -> M) Booking
      Airline (1 -> M) Flight
      Flight (1 -> M) Trip
      Flight (1 -> M) Seat
      Trip (1 -> M) Booking
      Seat (1 -> M) Booking

---

### Resources:
1. Arpit Bhayani's system design problem 

   https://github.com/arpitbbhayani/system-design-questions/blob/master/airline-checkin.md
