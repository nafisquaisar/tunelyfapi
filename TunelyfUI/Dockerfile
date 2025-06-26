# Stage 1: Build
FROM gradle:8.4.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Stage 2: Package
FROM openjdk:17-jdk-slim
COPY --from=build /build/libs/TunelyfUI-1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
