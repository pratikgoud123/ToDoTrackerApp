FROM openjdk:11-jre-slim
WORKDIR usr/lib
ADD ./target/UserTask-0.0.1-SNAPSHOT.jar /usr/lib/UserTask-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserTask-0.0.1-SNAPSHOT.jar"]