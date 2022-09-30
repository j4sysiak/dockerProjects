FROM openjdk:8-jdk-alpine as mybuilder
RUN mkdir /application
WORKDIR /application
COPY ./ ./
RUN ./gradlew clean build --no-daemon