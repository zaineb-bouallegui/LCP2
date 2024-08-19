FROM openjdk:17
# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/*.jar /app/gateway.jar

# Make port 8761 available to the world outside this container
EXPOSE 8222

# Run the application
ENTRYPOINT ["java", "-jar", "/app/gateway.jar"]