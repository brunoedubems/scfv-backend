# Usar a imagem oficial do OpenJDK 17 (ou sua versão do Java)
FROM openjdk:17-jdk-alpine

# Diretório dentro do container para colocar o app
WORKDIR /app

# Copiar o jar gerado para o container
COPY target/app.jar app.jar

# Expor a porta que seu app usa (geralmente 8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

