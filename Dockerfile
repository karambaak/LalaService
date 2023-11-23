FROM openjdk:17-jdk-slim
RUN mkdir /app
COPY ./lalaservice*.jar ./app/lalaservice.jar
WORKDIR /app

EXPOSE 8089
CMD ["java", "-jar", "lalaservice.jar"]