# Use Maven image with JDK 17
FROM maven:3.9.4-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build the application
RUN ./mvnw clean install -DskipTests

# Run the application
CMD ["java", "-jar", "target/spring-boot-0.0.1-SNAPSHOT.jar"]