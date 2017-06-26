CREATE TABLE Client(
		    id_c CHAR PRIMARY KEY,
			 nom_c CHAR(20),
			 telephone CHAR(8),
			 adresse CHAR(40)
			 );
			 
CREATE TABLE Produit(
		     id_p CHAR PRIMARY KEY,
			  nom_p CHAR(20),
			  prixUni INT,
			  tva INT);
			  
CREATE TABLE Facture(
		     numFac CHAR PRIMARY KEY,
			    id_c CHAR(20) REFERENCES Client,
			    id_p CHAR(20) REFERENCES Produit,
			    qte INT,
			    prixT INT, 
			    date CHAR(20)
			    );

CREATE TRIGGER test AFTER INSERT 
			ON Facture
			BEGIN
			UPDATE Facture SET PrixT= (SELECT )    prixUni*qte+prixUni*qte*tva  WHERE numFac=NEW.numFac AND id_p=Produit.id_p; 
		END;


INSERT INTO Client VALUES('c1','Alpha Co', '1231231231','3, Rue du Thabor, 35000 Rennes');
INSERT INTO Client VALUES('c2', 'Beta Co', '3213213213', '43, Place de la République, 35000 Rennes');
INSERT INTO Client VALUES('c3', 'Gamma Co', '7417417417', '32, Rue dOrléans, 44000 Nantes');

INSERT INTO Produit VALUES('p1', 'pomme', 1, 2);
INSERT INTO Produit VALUES('p2', 'tomate', 0.5, 2);
INSERT INTO Produit VALUES('p3', 'orange', 1, 2);

INSERT INTO Facture VALUES('123','c1','p1',2,NULL,'2.3.2013');
INSERT INTO Facture VALUES('124','c2','p2',22,NULL,'3.4.2013');
INSERT INTO Facture VALUES('125','c3','p3',13,NULL,'4.4.2013');


