FROM java:8

VOLUME /tmp

COPY bookSystem.jar app.jar

RUN bash -c "touch /app.jar"

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]