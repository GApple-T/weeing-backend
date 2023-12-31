FROM openjdk:17
ARG JAR_PATH=build/libs/weeing-0.0.1-SNAPSHOT.jar
COPY ${JAR_PATH} /home/server.jar
ENTRYPOINT ["java","-jar","/home/server.jar"]