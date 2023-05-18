FROM eclipse-temurin:17-jdk-jammy
COPY build/libs/kul-logistics-0.0.1-SNAPSHOT.jar kul-logistics.jar

ENTRYPOINT ["java","-jar","/kul-logistics.jar"]