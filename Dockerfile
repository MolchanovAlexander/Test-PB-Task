# Build stage
FROM openjdk:17-jdk-alpine AS builder
WORKDIR /application

# Copy the WAR file into the image
COPY target/*.war application.war

# Extract the WAR file contents
RUN java -Djarmode=layertools -jar application.war extract

# Final stage
FROM openjdk:17-jdk-alpine
WORKDIR /application

# Copy the extracted files from the build stage
COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./

# Set the entry point for the application
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.WarLauncher"]

EXPOSE 8080
