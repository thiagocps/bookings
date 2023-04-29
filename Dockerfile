FROM openjdk:17-oracle
WORKDIR /app
COPY . /app/
ENTRYPOINT ["java", "-jar", "booking-0.0.1-SNAPSHOT.jar"]
EXPOSE 2424
