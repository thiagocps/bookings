FROM openjdk:17-oracle
COPY . /
ENTRYPOINT ["java", "-jar", "booking-0.0.1-SNAPSHOT.jar"]
EXPOSE 2424
