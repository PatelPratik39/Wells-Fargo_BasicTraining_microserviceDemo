# Use OpenJDK image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy built jar from target/
COPY target/productService-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8083

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]