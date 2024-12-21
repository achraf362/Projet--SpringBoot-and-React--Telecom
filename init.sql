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
