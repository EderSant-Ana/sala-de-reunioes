FROM openjdk:11
VOLUME /tmp
EXPOSE 8082
ADD ./target/saladereuniao-0.0.1-SNAPSHOT.jar saladereuniao-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/saladereuniao-0.0.1-SNAPSHOT.jar"]