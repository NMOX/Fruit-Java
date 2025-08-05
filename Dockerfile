# Multi-stage Dockerfile for Java OOP Fruit Example
# This provides a consistent development and runtime environment

# Build stage - compile the Java code
FROM openjdk:21-jdk-slim AS builder

# Set metadata
LABEL maintainer="Java OOP Fruit Example"
LABEL description="Educational Java project demonstrating OOP concepts"
LABEL version="1.0"

# Set working directory
WORKDIR /app

# Install useful development tools
RUN apt-get update && apt-get install -y \
    make \
    git \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Copy source files
COPY *.java ./
COPY Makefile ./

# Compile Java files
RUN javac *.java

# Verify compilation by running tests
RUN java FruitTests

# Runtime stage - smaller image for running the application
FROM openjdk:21-jre-slim AS runtime

# Set working directory
WORKDIR /app

# Copy compiled classes from builder stage
COPY --from=builder /app/*.class ./

# Copy source files for reference (optional, for educational purposes)
COPY *.java ./
COPY *.md ./

# Create a non-root user for security
RUN groupadd -r javauser && useradd -r -g javauser javauser
RUN chown -R javauser:javauser /app
USER javauser

# Set default command to run the demo
CMD ["java", "FruitDemo"]

# Alternative commands that can be run:
# docker run <image> java FruitTests    # Run tests
# docker run <image> java FruitDemo     # Run demo (default)

# Expose port (not needed for this console app, but good practice)
EXPOSE 8080

# Health check (verify Java is working)
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD java -version || exit 1