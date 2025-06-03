FROM maven:3.9.9 AS build
WORKDIR /app

COPY / /app
RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /app/target/gimnasio-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8085
CMD ["java", "-jar", "app.jar"]

