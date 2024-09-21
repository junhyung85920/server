FROM openjdk:17
LABEL authors="parkseyeon"
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]