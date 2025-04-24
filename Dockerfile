# 1) Builder stage: compile your fat JAR
FROM openjdk:17-slim AS builder
WORKDIR /workspace

# Copy only the Gradle wrapper and build files first (cache deps)
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN ./gradlew --no-daemon dependencies

# Copy source & build
COPY src src
RUN ./gradlew --no-daemon bootJar

# 2) Runtime stage: run the JAR
FROM openjdk:17-slim
WORKDIR /app

COPY --from=builder /workspace/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
