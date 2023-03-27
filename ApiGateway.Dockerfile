FROM openjdk:17-jdk-alpine
RUN addgroup -S msg && adduser -S msg -G msg && apk --no-cache add curl
COPY api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","/api-gateway.jar"]