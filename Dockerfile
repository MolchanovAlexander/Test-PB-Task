# Build stage
FROM openjdk:17-jdk-alpine AS builder
WORKDIR /application

COPY target/*.war application.war

RUN java -Djarmode=layertools -jar application.war extract

FROM openjdk:17-jdk-alpine
WORKDIR /application

COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.WarLauncher"]

EXPOSE 8080
