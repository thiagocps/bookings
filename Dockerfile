FROM openjdk:17-oracle
WORKDIR /app
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} booking-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "booking-0.0.1-SNAPSHOT.jar"]
