FROM ibm-semeru-runtimes:open-17-jre-centos7
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]