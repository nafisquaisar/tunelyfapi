# Stage 1: Build the app
FROM gradle:8.4.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Stage 2: Run the jar
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /app/build/libs/TunelyfUI-1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
