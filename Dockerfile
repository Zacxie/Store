FROM gradle:7.6-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle build -x test --no-daemon

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/Store-1.0-SNAPSHOT.jar /app/Store-1.0.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/Store-1.0.jar"]
