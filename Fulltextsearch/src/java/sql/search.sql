CREATE DATABASE search;

CREATE TABLE categorie (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100)
);

CREATE TABLE marque (
    id SERIAL PRIMARY KEY,
    idcategorie INTEGER REFERENCES categorie(id),
    nom VARCHAR(100)
);

CREATE TABLE produit (
    id SERIAL PRIMARY KEY,
    idmarque Integer REFERENCES marque(id),
    nom VARCHAR(100),
    date_produit TIMESTAMP DEFAULT now(),
    qualite DECIMAL,
    prix DECIMAL,
    devise VARCHAR(50),
    description VARCHAR(300)
);

rapport 
qualité
prix

qualite/prix dia zay plus proche an 1 no meilleur prix


select *,(qualite/prix) AS qualite_prix from v_produits order by (qualite/prix) ASC 


select *,(qualite/prix) AS qualite_prix from v_produits order by (qualite/prix) DESC
