# --- Build Stage ---
FROM gradle:8.4-jdk17 AS build

WORKDIR /app
COPY . .

RUN ./gradlew clean bootJar --no-daemon

# --- Runtime Stage ---
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/build/libs/TunelyfUI-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
