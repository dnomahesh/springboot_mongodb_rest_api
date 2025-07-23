# FROM maven:3.9-ibm-semeru-17-focal AS build
# COPY . .
# RUN mvn clean package -DskipTests

# FROM ibm-semeru-runtimes:open-17-jre-focal
# COPY --from=build /target/*.jar demo.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","demo.jar"]
FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
