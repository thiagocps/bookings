FROM openjdk:17-oracle
MAINTAINER github/thiagocps
COPY ./target/booking-0.0.1-SNAPSHOT.jar /app/booking-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "booking-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
