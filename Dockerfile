FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY account-payable-service/pom.xml .

COPY account-payable-service/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/account-payable-service-1.0.0.jar app.jar

EXPOSE 8003

ENTRYPOINT ["java", "-jar", "app.jar"]