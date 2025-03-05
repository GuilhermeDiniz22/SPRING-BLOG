# Estágio de construção
FROM maven:3-eclipse-temurin-17 AS BUILD

# Copiar pom.xml e src para o container
COPY pom.xml ./
COPY src ./src

# Executar o Maven com uma configuração explícita de codificação UTF-8
RUN mvn clean package -DskipTests -Dfile.encoding=UTF-8

# Estágio final
FROM eclipse-temurin:17-alpine

# Copiar o JAR gerado
COPY --from=BUILD /target/*.jar demo.jar

# Expor a porta
EXPOSE 8080

# Definir o comando para executar o JAR
ENTRYPOINT ["java", "-jar", "/demo.jar"]



