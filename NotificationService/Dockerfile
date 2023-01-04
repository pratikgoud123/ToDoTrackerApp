FROM openjdk:11-jre-slim
WORKDIR usr/lib
ADD ./target/NotificationService-0.0.1-SNAPSHOT.jar /usr/lib/NotificationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","NotificationService-0.0.1-SNAPSHOT.jar"]

#    FROM openjdk
#
#    WORKDIR usr/lib
#
#    ADD ./target/NotificationService-0.0.1-SNAPSHOT.jar /usr/lib/NotificationService-0.0.1-SNAPSHOT.jar
#
#    ENTRYPOINT ["java","-jar","NotificationService-0.0.1-SNAPSHOT.jar"]