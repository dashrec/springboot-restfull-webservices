FROM eclipse-temurin:20

LABEL maintainer ="testemail@gmx.de"

WORKDIR /app/

COPY target/springboot-restfull-webservices-0.0.1-SNAPSHOT.jar /app/springboot-restfull-webservices.jar

ENTRYPOINT ["java", "-jar", "springboot-restfull-webservices.jar"]