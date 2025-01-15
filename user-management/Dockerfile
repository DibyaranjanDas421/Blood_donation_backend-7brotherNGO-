# Use a smaller OpenJDK base image
FROM amazoncorretto:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/user-management-0.0.1-SNAPSHOT.jar user-management-0.0.1-SNAPSHOT.jar

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "user-management-0.0.1-SNAPSHOT.jar"]
