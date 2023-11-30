FROM gradle:8.4-jdk11-alpine

COPY . .

RUN gradle build

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "build/libs/e_commerce-0.0.1-SNAPSHOT.jar"]