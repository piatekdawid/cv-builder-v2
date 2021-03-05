FROM java:8
EXPOSE 8080
ADD target/application.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]