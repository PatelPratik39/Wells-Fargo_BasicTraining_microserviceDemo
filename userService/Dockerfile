FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/userService-0.0.1-SNAPSHOT.jar userService.jar

ENTRYPOINT ["java", "-jar", "userService.jar"]