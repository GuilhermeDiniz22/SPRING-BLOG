# Estágio de construção
FROM maven:3-eclipse-temurin-17 AS BUILD
COPY . .
RUN mvn clean package -DskipTests

# Estágio final
FROM eclipse-temurin:17-alpine
COPY --from=BUILD /target/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/demo.jar"]


