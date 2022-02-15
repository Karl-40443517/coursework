#Creates docker container with a copy of the self contained jar file
FROM openjdk:latest
COPY ./target/group-project-0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "group-project-0.1-jar-with-dependencies.jar"]