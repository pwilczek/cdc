FROM openjdk:19
COPY ./build/libs/cdc-0.0.1-SNAPSHOT.jar /opt/cdc-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/opt/cdc-0.0.1-SNAPSHOT.jar"]