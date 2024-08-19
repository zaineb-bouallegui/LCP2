FROM openjdk:17
# Set the working directory inside the container
WORKDIR /app

# Copy the configuration files
COPY src/main/resources/configurations /app/configurations

# Copy the application JAR file to the container
COPY target/*.jar /app/config-server.jar

# Make port 8888 available to the world outside this container
EXPOSE 8888

# Run the application
ENTRYPOINT ["java", "-jar", "/app/config-server.jar"]