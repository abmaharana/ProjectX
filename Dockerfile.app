FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Install curl
RUN apt-get update && apt-get install -y curl

# Copy Maven files first for dependency caching
COPY pom.xml .
COPY src ./src

# Install dependencies (skips tests to speed up)
RUN mvn dependency:go-offline -B

EXPOSE 8080

# Default command to start app just like local
CMD ["mvn", "spring-boot:run"]
