FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /target/article-service-1.0-SNAPSHOT-exec.jar .
ENTRYPOINT ["java","-jar","/article-service-1.0-SNAPSHOT-exec.jar"]