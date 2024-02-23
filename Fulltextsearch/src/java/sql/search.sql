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


------- TOP 3 MEILLEUR PRIX ----------------------------------------------------
------ CATEGORIE MARQUE ----------------------
select * from v_produits where categorie = 'Telephone' AND marque = 'Redmi' order by prix ASC limit 3;

------ CATEGORIE -----------------------------
select * from v_produits where categorie = 'Telephone' order by prix ASC limit 3;

------ MARQUE --------------------------------
select * from v_produits where marque = 'Samsung' order by prix ASC limit 3;

------ NORMAL --------------------------------
select * from v_produits order by prix ASC limit 3;



------- TOP 3 PIRE PRIX --------------------------------------------------------
------ CATEGORIE MARQUE ----------------------
select * from v_produits where categorie = 'Telephone' AND marque = 'Redmi' order by prix DESC limit 3;

------ CATEGORIE -----------------------------
select * from v_produits where categorie = 'Telephone' order by prix DESC limit 3;

------ MARQUE --------------------------------
select * from v_produits where marque = 'Samsung' order by prix DESC limit 3;

------- NORMAL ------------------------
select * from v_produits order by prix DESC limit 3;



------- LES PLUS RECENT --------------------------------------------------------
------- CATEGORIE MARQUE --------------------------------
select * from v_produits where date_produit = (SELECT max(date_produit) from v_produits) AND categorie = 'Telephone' AND marque = 'Samsung';

------- CATEGORIE --------------------------------
select * from v_produits where date_produit = (SELECT max(date_produit) from v_produits) AND categorie = 'Telephone';

------- MARQUE --------------------------------
select * from v_produits where date_produit = (SELECT max(date_produit) from v_produits) AND marque = 'Samsung';

------- NORMAL --------------------------------
select * from v_produits where date_produit = (SELECT max(date_produit) from v_produits);




------- LES PLUS ANCIEN --------------------------------------------------------
------- CATEGORIE MARQUE --------------------------------
select * from v_produits where date_produit = (SELECT min(date_produit) from v_produits) AND categorie = 'Telephone' AND marque = 'Samsung';

------- CATEGORIE --------------------------------
select * from v_produits where date_produit = (SELECT min(date_produit) from v_produits) AND categorie = 'Telephone';

------- MARQUE --------------------------------
select * from v_produits where date_produit = (SELECT min(date_produit) from v_produits) AND marque = 'Samsung';

------- NORMAL --------------------------------
select * from v_produits where date_produit = (SELECT min(date_produit) from v_produits);



--------- LES PRODUITS ENTRE 2 prix ---------------------------------------------------
select * from v_produits where prix <= 3000 AND prix >= 2500 AND categorie = 'Telephone';

