FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/configserver-0.0.1-SNAPSHOT.jar configserver.jar
ENTRYPOINT ["java", "-jar", "configserver.jar"]