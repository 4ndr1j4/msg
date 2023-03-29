FROM openjdk:17-jdk-alpine
#RUN addgroup -S msg && adduser -S msg -G msg && apk --no-cache add curl
RUN apk --no-cache add curl
COPY config-server/target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/config-server.jar"]