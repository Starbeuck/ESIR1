--question 1
--SELECT nom_p FROM Produit WHERE origine='Dijon';

--question 2
SELECT DISTINCT nom_f, Fournisseur.f FROM Fournisseur, Fourniture, Produit WHERE nom_p='salade';
-- c'est de la merde
