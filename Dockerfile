FROM openjdk:17-oracle
WORKDIR /app
COPY target/booking-0.0.1-SNAPSHOT.jar booking-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "booking-0.0.1-SNAPSHOT.jar"]
