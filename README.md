# Projet Spring Boot et React - Télécom

Ce projet est une application web full-stack permettant de gérer des équipes et des joueurs. Elle est construite avec **Spring Boot** pour le backend, **React** pour le frontend, et une base de données **MySQL** pour le stockage des données. Le projet suit les principes REST et inclut des tests unitaires pour assurer sa robustesse.

## Fonctionnalités principales
- **Backend (Spring Boot)** :
  - Gestion des équipes et des joueurs (CRUD : création, lecture, mise à jour, suppression).
  - API REST pour les opérations sur les équipes et les joueurs.
  - Persistance des données via JPA dans une base de données MySQL.

- **Frontend (React)** :
  - Pages pour afficher, ajouter, modifier et supprimer des équipes et des joueurs.
  - Interface utilisateur moderne avec React et Bootstrap.

- **Base de données (MySQL)** :
  - Stockage des données des équipes et des joueurs.
  - Script SQL fourni pour l'initialisation de la base.

- **Tests unitaires** :
  - Tests pour les contrôleurs et services.
  - Validation des entités avec des tests spécifiques.

---

## Structure du projet
```
Projet--SpringBoot-and-React--Telecom/
├── backend/          # Code Spring Boot (backend)
├── frontend/         # Code React (frontend)
├── Dockerfile        # Fichier Dockerfile
├── docker-compose.yml # Fichier Docker-compose pour simplifier le déploiement
├── README.md         # Documentation
└── .gitignore        # Fichiers à ignorer par Git
```

---

## Étapes pour faire fonctionner le projet

### Prérequis
- **Java 17+**
- **Maven** installé
- **Node.js** et **npm** pour le frontend
- **Docker** et **Docker Compose**

### 1. Cloner le projet
```bash
git clone https://github.com/achraf362/Projet--SpringBoot-and-React--Telecom.git
cd Projet--SpringBoot-and-React--Telecom
```

2. Gestion de la base de données avec Docker Compose
La base de données MySQL est automatiquement configurée lors de l’exécution de Docker Compose. Voici ce qui est pris en charge :

- **Création automatique de la base de données `teamsdb`.**
- **Initialisation des tables et des données grâce au fichier `init.sql`.**
- **Aucune configuration manuelle n’est nécessaire.**

Voici le contenu du fichier `init.sql` qui gère l'initialisation de la base de données :

```sql
CREATE DATABASE IF NOT EXISTS teamsdb;

USE teamsdb;

CREATE TABLE IF NOT EXISTS team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    coach VARCHAR(100),
    city VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(50),
    team_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES team(id) ON DELETE SET NULL
);
```

### 3. Backend : Compilation et packaging
1. Nettoyer les anciens fichiers de build :
   ```bash
   mvn clean
   ```
   - **Objectif** : Supprime les fichiers précédents dans le répertoire `target`.

2. Compiler et générer l'application Spring Boot :
   ```bash
   mvn package install
   ```
   - **Objectif** : Compile et package l'application dans un fichier exécutable `.jar`.


---

### 4. Utilisation de Docker Compose
1. Construisez les images Docker :
   ```bash
   docker-compose build
   ```
   - **Objectif** : Prépare les images Docker pour le backend et la base de données.

2. Lancez les conteneurs Docker :
   ```bash
   docker-compose up -d
   ```
   - **Objectif** : Démarre les services (backend et base de données) en arrière-plan.


---

### 5. Frontend : Installation et lancement
1. Naviguez dans le dossier `frontend` :
   ```bash
   cd frontend
   ```

3. Installez les dépendances nécessaires :
   ```bash
   npm install
   ```

   - **Objectif** : Télécharge toutes les bibliothèques nécessaires.

2. Naviguez dans le dossier `team-player-management` :
   ```bash
    cd .\team-player-management\
   ```

4. Lancez l'application React :
   ```bash
   npm start
   ```
   - **Objectif** : Démarre le serveur de développement React à l'adresse `http://localhost:3000`.

---

## Tests unitaires

### Tests inclus
1. **Tests des contrôleurs** :
   - `PlayerControllerTest` : Valide les endpoints CRUD pour les joueurs.
   - `TeamControllerTest` : Valide les endpoints CRUD pour les équipes.

2. **Tests des services** :
   - `PlayerServiceTest` : Valide la logique métier pour les joueurs.
   - `TeamServiceTest` : Valide la logique métier pour les équipes.

3. **Tests des entités** :
   - `PlayerEntityTest` : Valide l'intégrité des entités `Player`.
   - `TeamEntityTest` : Valide l'intégrité des entités `Team`.

### Exécution des tests
1. Exécutez les tests unitaires avec Maven :
   ```bash
   mvn test
   ```
2. Résultats attendus :
   - Tous les tests doivent passer sans erreurs.

---

## Fonctionnement des API
### API Teams (/api/teams)

- **GET /api/teams** : Liste toutes les équipes.
  - Exemple de réponse :
    ```json
    [
        {
            "id": 1,
            "name": "Team A",
            "coach": "Coach A",
            "city": "City A"
        },
        {
            "id": 2,
            "name": "Team B",
            "coach": "Coach B",
            "city": "City B"
        }
    ]
    ```

- **POST /api/teams** : Crée une nouvelle équipe.
  - Exemple de requête :
    ```json
    {
        "name": "Team C",
        "coach": "Coach C",
        "city": "City C"
    }
    ```
  - Exemple de réponse :
    ```json
    {
        "id": 3,
        "name": "Team C",
        "coach": "Coach C",
        "city": "City C"
    }
    ```

- **PUT /api/teams/{id}** : Met à jour une équipe existante.
  - Exemple de requête :
    ```json
    {
        "name": "Team A Updated",
        "coach": "Coach A Updated",
        "city": "City A Updated"
    }
    ```

- **DELETE /api/teams/{id}** : Supprime une équipe.

### API Players (/api/players)

- **GET /api/players** : Liste tous les joueurs.
  - Exemple de réponse :
    ```json
    [
        {
            "id": 1,
            "name": "Player A",
            "position": "Forward",
            "team_id": 1
        },
        {
            "id": 2,
            "name": "Player B",
            "position": "Midfielder",
            "team_id": 2
        }
    ]
    ```

- **POST /api/players** : Crée un nouveau joueur.
  - Exemple de requête :
    ```json
    {
        "name": "Player C",
        "position": "Defender",
        "team_id": 1
    }
    ```
  - Exemple de réponse :
    ```json
    {
        "id": 3,
        "name": "Player C",
        "position": "Defender",
        "team_id": 1
    }
    ```

- **PUT /api/players/{id}** : Met à jour un joueur existant.
  - Exemple de requête :
    ```json
    {
        "name": "Player A Updated",
        "position": "Goalkeeper",
        "team_id": 2
    }
    ```

- **DELETE /api/players/{id}** : Supprime un joueur.

---

## Points importants
- **Docker** : Simplifie le déploiement et la gestion des dépendances.
- **Tests unitaires** : Garantissent la robustesse et la fiabilité du projet.
- **Code maintenable** : Conception en couches avec séparation des responsabilités.

---

## Auteur
- **Achraf**
- Télécom Saint-Étienne

