FROM openjdk:17-jdk-slim

ENV APP_HOME=/app
WORKDIR $APP_HOME
COPY target/*.jar employee-management-api.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "employee-management-api.jar"]
