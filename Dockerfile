FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/sb-crud-0.0.1-SNAPSHOT.jar sb-crud.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "sb-crud.jar"]