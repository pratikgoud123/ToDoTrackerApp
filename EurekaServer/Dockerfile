FROM openjdk:11-jre-slim
WORKDIR usr/lib
ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar /usr/lib/EurekaServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","EurekaServer-0.0.1-SNAPSHOT.jar"]

#    #here openjdk is docker image for Java 11, as in pom.xml Java version is 11
#    FROM openjdk
#    #creating a working directory inside the image
#    WORKDIR usr/lib
#    #setting environment variable same name that are there in application.properties file
##     ENV MYSQL_DATABASE=wave34
##     ENV MYSQL_URL=mysql://localhost:3306/wave34
#    #Copy executable jar file getting created inside target and add it in usr/lib working director
#    ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar /usr/lib/EurekaServer-0.0.1-SNAPSHOT.jar
#    #Run the jar file
#    ENTRYPOINT ["java","-jar","EurekaServer-0.0.1-SNAPSHOT.jar"]