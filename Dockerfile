# https://spring.io/guides/topicals/spring-boot-docker/

FROM openjdk:17-jdk-slim-buster

VOLUME /tmp

EXPOSE 8081

ARG JAR_FILE=customer-handler-api-test-app-boot/target/customer-handler-api-test-app-exec.jar

COPY ${JAR_FILE} customer-handler-api-test-app-exec.jar

ENTRYPOINT ["java","-jar","/customer-handler-api-test-app-exec.jar"]