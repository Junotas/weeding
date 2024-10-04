# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the project's build artifact to the container
COPY target/*.jar app.jar

# Copy the .env file to the container
COPY .env .env

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
