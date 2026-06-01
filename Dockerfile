FROM eclipse-temurin:17

WORKDIR /app

COPY target/LibraryManagementSystemWeb-1.0.jar app.jar

EXPOSE 10000

ENTRYPOINT ["java","-jar","app.jar"]