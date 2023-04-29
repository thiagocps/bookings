FROM openjdk:17-oracle
WORKDIR /app
COPY . .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/booking-0.0.1-SNAPSHOT.jar"]
