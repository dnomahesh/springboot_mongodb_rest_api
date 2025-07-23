# Use Maven image with JDK 17
FROM maven:3.9.4-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy everything and build
COPY . .
RUN mvn clean install -DskipTests

# Run the Spring Boot app
CMD ["java", "-jar", "target/spring-boot-0.0.1-SNAPSHOT.jar"]