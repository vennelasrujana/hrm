# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk as build
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the Maven project file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application (skip tests for faster build)
RUN mvn package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built JAR file from build stage
COPY --from=build /app/target/dgsmeclone-0.0.1-SNAPSHOT.jar app.jar

# Set environment variables for JVM and Spring Boot
# ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:MinRAMPercentage=50.0 -Xss512k -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:-UseAdaptiveSizePolicy"
ENV JAVA_OPTS="-Xmx300m -Xms128m"

ENV SPRING_PROFILES_ACTIVE=prod

ENV PORT=8080

# Expose the application port
EXPOSE ${PORT}

# Start the application
CMD java $JAVA_OPTS -jar app.jar
