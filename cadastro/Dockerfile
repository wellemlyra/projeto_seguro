# Usar imagem Maven para construir o projeto
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o código-fonte para dentro do container
COPY . .

# Compilar o projeto e gerar o JAR
RUN mvn clean package -DskipTests

# Segunda fase: Usar uma imagem mais leve para rodar o JAR
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar o JAR construído da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expôr a porta
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
