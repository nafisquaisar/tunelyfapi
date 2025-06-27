# Stage 1: Build the app using Gradle
FROM gradle:8.4.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run the JAR using OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/TunelyfUI-1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
