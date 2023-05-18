FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=build/*.jar
COPY ${JAR_FILE} kul-logistics.jar

ENTRYPOINT ["java","-jar","/kul-logistics.jar"]