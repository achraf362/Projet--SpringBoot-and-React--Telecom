# Utilise l'image officielle de OpenJDK
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR de l'application dans le conteneur
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port de l'application (par défaut Spring Boot utilise 8080)
EXPOSE 8080

# Commande pour lancer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
