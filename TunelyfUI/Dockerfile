FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# Set JAVA_HOME explicitly
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

RUN chmod +x ./gradlew
RUN ./gradlew build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/TunelyfUI-0.0.1-SNAPSHOT.jar"]
