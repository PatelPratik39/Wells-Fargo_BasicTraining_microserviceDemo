FROM openjdk:21-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y curl

COPY target/*.jar configserver.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "configserver.jar"]