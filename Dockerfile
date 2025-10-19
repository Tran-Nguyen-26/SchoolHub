FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/schoolhub-0.0.1-SNAPSHOT.jar api-service.jar

ENTRYPOINT ["java", "-jar", "api-service.jar"]

EXPOSE 9193