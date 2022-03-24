#Creates docker container with a copy of the self contained jar file
FROM openjdk:latest
COPY ./target/coursework.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "coursework.jar", "db:3306", "30000"]