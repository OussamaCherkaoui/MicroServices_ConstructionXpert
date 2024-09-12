FROM openjdk:21



ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8082
ENTRYPOINT ["top","-b"]

ENTRYPOINT ["java", "-jar" ,"/app.jar"]