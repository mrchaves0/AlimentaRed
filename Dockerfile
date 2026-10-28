FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN if [ -f alimentared/pom.xml ]; then cd alimentared && mvn clean package -DskipTests; else mvn clean package -DskipTests; fi

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/**/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
