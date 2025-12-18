# ============================
# BUILD STAGE
# ============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# Copy pom first to leverage Docker cache
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copy source code
COPY src ./src

# Build application
RUN mvn -B -q clean package -DskipTests


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

EXPOSE ${SERVICE_PORT}

# JVM options are injected via environment variables
ENTRYPOINT ["java","-jar","/app/app.jar"]
