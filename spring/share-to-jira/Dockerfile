# Use official Maven to build the app
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Copy and build the project
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a lightweight JRE for running the app
FROM eclipse-temurin:17-jre-alpine

# Set working directory inside the image
WORKDIR /app

# Copy built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]