FROM openjdk:17-oracle
WORKDIR /app
COPY booking-0.0.1-SNAPSHOT.jar /app/booking-0.0.1-SNAPSHOT.jar
EXPOSE 2424
ENTRYPOINT ["java", "-jar", "booking-0.0.1-SNAPSHOT.jar"]
