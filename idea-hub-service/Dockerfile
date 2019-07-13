FROM openjdk:11

ADD ./target/idea-hub-service-0.0.1-SNAPSHOT.jar /usr/src/idea-hub-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "idea-hub-service-0.0.1-SNAPSHOT.jar"]