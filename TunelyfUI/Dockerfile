# Stage 1: Build JAR using Gradle and Java 17
FROM gradle:8.4.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run JAR in slim image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/TunelyfUI-1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
