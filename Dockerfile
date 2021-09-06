#Не хватило ресурсов компа проверить этот конфиг!!!

#FROM gradle:latest as buildImage
#COPY . /home/gradle/source
#WORKDIR /home/gradle/source
#RUN gradle build
#
#FROM openjdk:latest
#COPY --from=buildImage /home/gradle/source/build/libs/cinema-0.0.1-SNAPSHOT.jar /opt/app.jar
#ENTRYPOINT ["java", "-jar", "/opt/app.jar"]


FROM openjdk:latest
ARG JAR_FILE=build/libs/cinema-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]
