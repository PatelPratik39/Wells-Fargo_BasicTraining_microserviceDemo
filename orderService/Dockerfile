# Base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar and rename
COPY target/orderService-0.0.1-SNAPSHOT.jar orderService.jar

# Run app
ENTRYPOINT ["java", "-jar", "orderService.jar"]