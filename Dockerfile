FROM openjdk:8-jre-alpine
VOLUME /tmp
ARG JAR_FILE=target/shoe-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prd","-jar","/app.jar"]
