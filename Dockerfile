FROM ibm-semeru-runtimes:open-17-jre-centos7
ARG JAR_FILE=build/libs/PersonalRegistry-0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]