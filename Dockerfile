FROM openjdk:17
COPY build/libs/yourscore-0.0.1-SNAPSHOT.jar yourscore-0.0.1.jar
ENTRYPOINT ["java", "-jar", "yourscore-0.0.1.jar"]