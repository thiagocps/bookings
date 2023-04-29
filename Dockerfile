FROM openjdk:17-oracle
WORKDIR /app
COPY . /
EXPOSE 2424
ENTRYPOINT ["java", "-jar", "./app/booking-0.0.1-SNAPSHOT.jar"]
