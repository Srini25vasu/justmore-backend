FROM openjdk:11-jre-slim
LABEL maintainer="srini25vasu@gmail.com"
VOLUME /tmp
EXPOSE 8080

#Application jar file
ARG JAR_FILE="target/justmehr-backend-0.0.1-SNAPSHOT.jar"

# add jar file to conainer
ADD ${JAR_FILE} justmehr.jar
# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/justmehr.jar"]
