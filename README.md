# What's this?
It's a project for a Java Technical Test. Thanks for the opportunity.

# How many are frameworks here?
In this project, I used:
* Spring Boot
* Spring Data JPA
* Spring Rest
* Lombok
* Javax validation
* H2 in memory
* OpenAPI documentation
* Unit Tests
* Maven
* Java 17

# Getting Started

### To run
mvn spring-boot:run

### URLs available
* For documentation: http://localhost:8080/swagger-ui.html
* For access the database: http://localhost:8080/h2-ui
* For list all bookings: (GET) http://localhost:8080/v1/bookings
* For select a specific booking: (GET) http://localhost:8080/v1/bookings/{id}
* For insert a new booking: (POST) http://localhost:8080/v1/bookings
* For update a booking: (PUT) http://localhost:8080/v1/bookings/{id}
* For delete a booking: (DELETE) http://localhost:8080/v1/bookings/{id}