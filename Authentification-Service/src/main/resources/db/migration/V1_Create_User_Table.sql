CREATE TABLE Utilisateur (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             username VARCHAR(255) NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             email VARCHAR(255) NOT NULL,
                             telephone VARCHAR(255),
                             role VARCHAR(255) NOT NULL
);
CREATE TABLE User (
                      id BIGINT PRIMARY KEY,
                      FOREIGN KEY (id) REFERENCES utilisateur(id)
);
CREATE TABLE Admin (
                       id BIGINT PRIMARY KEY,
                       FOREIGN KEY (id) REFERENCES utilisateur(id)
);