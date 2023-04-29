FROM openjdk:17-oracle
WORKDIR /app
COPY ./target/booking-0.0.1-SNAPSHOT.jar /app/booking-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "booking-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
