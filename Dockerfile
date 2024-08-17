# Use the official Amazon Corretto 17 as a base image
FROM amazoncorretto:17

# Set the working directory inside the container
WORKDIR /app

# Copy the build.gradle and other necessary Gradle files
# Copy the Gradle wrapper
COPY ./build/libs/jejuddai-0.0.1-SNAPSHOT.jar /app/jejuddai.jar
ENV TZ=Asia/Seoul

# Download and cache dependencies
CMD ["java", "-jar", "jejuddai.jar"]

# Copy the source code