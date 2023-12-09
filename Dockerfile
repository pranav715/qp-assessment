FROM openjdk:8
EXPOSE 8081
ARG JAR_FILE=./qp-assessment//build//libs//grocery-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} grocery.jar
ENTRYPOINT ["java","-jar","/grocery.jar"]