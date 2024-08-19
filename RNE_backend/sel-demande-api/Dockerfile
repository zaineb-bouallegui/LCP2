FROM openjdk:17
# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/*.jar /app/sel-demande-api.jar

# Make port 8082 available to the world outside this container
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "/app/sel-demande-api.jar"]