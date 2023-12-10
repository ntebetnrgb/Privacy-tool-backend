FROM openjdk AS build
EXPOSE 80
ADD target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
