FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

ARG SERVICE_NAME
RUN mvn clean package -DskipTests -pl ${SERVICE_NAME} -am

FROM eclipse-temurin:21-jdk
WORKDIR /app

ARG SERVICE_NAME
COPY --from=build /app/${SERVICE_NAME}/target/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
