CREATE VIEW v_produits AS
SELECT
    categorie.id AS idcategorie,
    categorie.nom AS categorie,
    marque.id AS idmarque,
    marque.nom AS marque,
    produit.id AS idproduit,
    produit.nom AS produit,
    produit.date_produit AS date_produit,
    produit.qualite AS qualite,
    produit.prix AS prix,
    produit.devise AS devise,
    produit.description AS description
FROM
    categorie
JOIN 
    marque ON marque.idcategorie = categorie.id
JOIN
    produit ON produit.idmarque = marque.id;