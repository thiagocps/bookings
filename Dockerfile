FROM openjdk:17-oracle
WORKDIR /app
COPY . .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "booking-0.0.1-SNAPSHOT.jar"]
