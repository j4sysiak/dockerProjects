FROM openjdk:8-jdk-alpine
ADD target/*.jar api.jar
#EXPOSE 9090
ENTRYPOINT ["java","-jar","api.jar"]