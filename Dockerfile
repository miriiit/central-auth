# Use a base image with Java and Maven pre-installed
# Stage 1: Download Maven dependencies
FROM maven:3.8.4 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
#COPY . .
#COPY .dockerignore .
COPY ./src /app/src
RUN mvn package -DskipTests


# Use a lightweight base image for the final image
FROM openjdk:17-oracle
# Set Java options
ENV _JAVA_OPTIONS="-Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

WORKDIR /app
# Copy the JAR file from the builder stage
COPY --from=builder /app/target/central-auth-0.0.1-SNAPSHOT.jar /ca/app.jar
# Expose the port your application runs on (e.g., 8080)
EXPOSE 8080
# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "/ca/app.jar"]
#CMD ["ls", "-l", "/az/app.jar"]