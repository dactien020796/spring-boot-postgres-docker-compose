FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*-SNAPSHOT.jar
COPY build/libs/${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]