FROM openjdk:17-jdk-alpine
RUN addgroup -S msg && adduser -S msg -G msg
COPY vat-rates/target/vat-rates-0.0.1-SNAPSHOT.jar vat-rates.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","/vat-rates.jar"]