FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/userService-0.0.1-SNAPSHOT.jar userService.jar

# Expose the default port for the notification service (change if different)
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "userService.jar"]