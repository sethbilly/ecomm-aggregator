FROM openjdk:8-jdk-alpine

VOLUME /tmp

# Make port 8084 available outside this container
EXPOSE 8084

# Add the application's jar file
ARG JAR_FILE=target/ectools-aggregator-1.0.jar

ADD ${JAR_FILE} ectools-aggregator.jar

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "/ectools-aggregator.jar"]