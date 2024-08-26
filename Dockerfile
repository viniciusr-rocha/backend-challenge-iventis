FROM gradle:8.8-jdk17 AS build
WORKDIR /app

COPY gradle/ gradle/
COPY build.gradle settings.gradle ./
COPY src/ src/

RUN gradle build --no-daemon -x test

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
VOLUME /tmp

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
