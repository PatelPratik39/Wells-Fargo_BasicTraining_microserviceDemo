# Use OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy built jar file into container
COPY target/notification-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the default port for the notification service (change if different)
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]