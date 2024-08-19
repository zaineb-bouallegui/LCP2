FROM openjdk:17
# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/discovery-0.0.1-SNAPSHOT.jar /app/discovery.jar

# Make port 8761 available to the world outside this container
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "/app/discovery.jar"]