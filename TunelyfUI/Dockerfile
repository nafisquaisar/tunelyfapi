# Stage 1: Build
FROM gradle:8.4.0-jdk17 AS build
WORKDIR /build
COPY . .
RUN gradle bootJar --no-daemon

# Stage 2: Run
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/build/libs/TunelyfUI-1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
