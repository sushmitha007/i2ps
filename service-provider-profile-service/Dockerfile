FROM  openjdk:11-jdk-stretch
WORKDIR /i2ps/user

ADD target/service-provider-profile-service-0.0.1-SNAPSHOT.jar /i2ps/user/service-provider-profile-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT  ["java","-jar","service-provider-profile-service-0.0.1-SNAPSHOT.jar"]