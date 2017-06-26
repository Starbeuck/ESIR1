;*------------------------------------------------------------------------
;*MODULE                      TP2 - machine pgcd
;*------------------------------------------------------------------------
;* Auteur(s) :   
;*
;* Formation :               Groupe: 
;*-------------------------------------------------------------------------
;*OBJET
;*      Ce programme permet de lire une suite de couples d'entiers,
;*      et de calculer le pgcd de chaque couple
;*
;*      le programme s'arrette sur la lecture d'un entier egal a 0
;*POINTS d'ENTREE
;*      neant (programme principal)
;*DONNEES EXPORTEES
;*      neant
;*REFERENCES EXTERNES
;*      neant
;*--------------------------------------------------------------------------
;*SOUS-PROGRAMMES :
;*      LIRENT   : lit un entier de deux chiffres en base 10
;*      ECRIRENT : ecrit un entier de deux chiffres en base 10
;*      PGCD     : calcule le PGCD de deux entiers
;*-------------------------------------------------------------------------



;*------------------------------------------------------------------------
;*SOUS-PROGRAMME      LIRENT
;*------------------------------------------------------------------------
;*SPECIFICATION
;*  lit un nombre de deux chiffres en base 10 et rend le resultat de sa
;*  conversion en base 2
;*  La donnee est supposee correcte
;*
;*PASSAGE DES PARAMETRES
;*   RESULTAT : AL = valeur du nombre en base 2
;*-------------------------------------------------------------------------



;*------------------------------------------------------------------------
;*SOUS-PROGRAMME       ECRIRENT
;*------------------------------------------------------------------------
;*SPECIFICATION
;*   convertit une valeur binaire <=99 en base 10, et affiche le resultat
;*   a l'ecran. Cette valeur est supposee etre <=99
;*
;*PASSAGE DES PARAMETRES
;*   ENTREE : AL = valeur a afficher en base 2
;*-------------------------------------------------------------------------



;*---------------------------------------------------------------------------
;*SOUS-PROGRAMME      PGCD
;*
;*---------------------------------------------------------------------------
;*SPECIFICATION
;*   calcule le PGCD de deux entiers >0
;*
;*PASSAGE DES PARAMETRES
;*   ENTREE : AL = entier n1
;*            BL = entier n2
;*
;*   RESULTAT : CL = pgcd(n1,n2)
;*---------------------------------------------------------------------------
