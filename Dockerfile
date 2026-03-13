# syntax=docker/dockerfile:1.7

# ============================
# BUILD STAGE
# ============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# Copy build inputs
COPY pom.xml ./
COPY src ./src

# Build application in a single Maven pass.
# Using a BuildKit cache keeps ~/.m2 between builds.
RUN --mount=type=cache,id=trade-service-m2,target=/root/.m2 \
    sh -c "mvn -B -ntp -o -Dmaven.test.skip=true package || mvn -B -ntp -Dmaven.test.skip=true package"


# ============================
# RUNTIME STAGE
# ============================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Create non-root user (important in regulated environments)
RUN addgroup -S spring && adduser -S spring -G spring

# Copy built JAR
COPY --from=build /build/target/*.jar app.jar

# Change ownership
RUN chown -R spring:spring /app

USER spring

ARG SERVICE_PORT=8080
EXPOSE ${SERVICE_PORT}

# JVM options are injected via environment variables
ENTRYPOINT ["java","-jar","/app/app.jar"]
