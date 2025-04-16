FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/eurekaServer-0.0.1-SNAPSHOT.jar eurekaserver.jar

ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]