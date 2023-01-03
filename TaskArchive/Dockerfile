
    FROM openjdk

    WORKDIR usr/lib

    ADD ./target/TaskArchive-0.0.1-SNAPSHOT.jar /usr/lib/TaskArchive-0.0.1-SNAPSHOT.jar

    ENTRYPOINT ["java","-jar","TaskArchive-0.0.1-SNAPSHOT.jar"]