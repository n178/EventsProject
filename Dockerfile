FROM openjdk:11
EXPOSE 8765
RUN curl -o app.jar http://172.17.0.2:8081/#browse/browse:maven-releases/tn/esprit/eventsProject/1.0.0/eventsProject-1.0.0.jar
ENTRYPOINT ["java", "-jar", "app.jar"]