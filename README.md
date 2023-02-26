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

        user_id | username | bio | total_blogs
2. Blog

        blog_id | title | body | blog_status
3. UserBlog

        user_id | blog_id

---

### Relationships

User (1 -> M) Blog

Blog (1 -> M) User

Many to Many relation

---

### Resources:
1. https://howtodoinjava.com/kafka/getting-started-windows-10/
2. https://howtodoinjava.com/kafka/spring-boot-with-kafka/
3. Arpit Bhayani's system design problem
