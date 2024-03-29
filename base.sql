
CREATE SEQUENCE seq_employe;
CREATE TABLE employe(
    id_employe INTEGER PRIMARY KEY default nextval('seq_employe'),
    nom VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    dtn DATE NOT NULL
);

CREATE SEQUENCE seq_user;
CREATE TABLE users(
    id_user INTEGER  PRIMARY KEY default nextval('seq_user'),
    username VARCHAR(100),
    mdp VARCHAR(100),
    role VARCHAR(50)
);

CREATE SEQUENCE seq_token;
CREATE TABLE token(
    id_token INTEGER PRIMARY KEY default nextval('seq_token'),
    value VARCHAR(100),
    date_start TIMESTAMP,
    date_end TIMESTAMP
);

insert into employe(nom, prenom, dtn) values ('Rakoto', 'Jean', '2023-06-24');
insert into users(username, mdp, role) values ('chalman', 'chalman','admin');
insert into users(username, mdp, role) values ('zaid', 'zaid', 'client');