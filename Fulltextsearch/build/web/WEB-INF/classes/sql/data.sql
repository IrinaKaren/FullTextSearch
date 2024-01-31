INSERT INTO categorie (nom)
VALUES 
('Telephone'),
('Chaussure');

INSERT INTO marque (idcategorie,nom)
VALUES
(5,'Redmi'),
(5,'Samsung'),
(5,'Iphone'),
(6,'Nike'),
(6,'Adidas'),
(6,'Converse');

INSERT INTO produit (idmarque,nom,qualite,prix,devise,description)
VALUES
-------- Telephone -------------------------------------------------------------
(19,'redmi not 9 pro',3,2500,'ARIARY','Telephone gameur.'),
(20,'S10 plus',3,2200,'ARIARY','Photo de très bonne qualité.'),
(21,'Iphone 14',3,3000,'ARIARY','Zoom de qualité.'),

--------- Chaussure ------------------------------------------------------------
(22,'Sandale',3,900,'ARIARY','très comfortable.'),
(23,'Tennis',3,1000,'ARIARY','très durable.'),
(24,'Tennis',3,1200,'ARIARY','très élégant.');