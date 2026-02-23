# Use uma imagem base do JDK 17
FROM eclipse-temurin:17-jdk-alpine

COPY target/bff-agendador-tarefas-0.0.1-SNAPSHOT.jar /app/bff-agendador-tarefas.jar

# porta da aplicação
EXPOSE 8083

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/bff-agendador-tarefas.jar"]