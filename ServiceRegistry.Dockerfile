FROM openjdk:17-jdk-alpine
RUN addgroup -S msg && adduser -S msg -G msg && apk --no-cache add curl
COPY service-registry/target/service-registry-0.0.1-SNAPSHOT.jar service-registry.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","/service-registry.jar"]