;*------------------------------------------------------------------------
;*MODULE                      TP2 - machine pgcd
;*------------------------------------------------------------------------
;* Auteur(s) :   Sophy BRUNOY & Solenn KEROULLAS
;*
;* Formation : info              Groupe: 1
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
		 .model small
         .stack
         .data

		 DIX DB 10
		 MSG1 DB "L'user veut arrêter. Au revoir.",10,13,"$"
		 MSG2 DB "Le PGCD de    et de    est  .",10,13,"$"
		 
		 .code
start :  MOV AX, @DATA
		 MOV DS, AX				; initialisation du data segment
;*------------------------------------------------------------------------
;*SOUS-PROGRAMME      LIRE
;*------------------------------------------------------------------------
;*SPECIFICATION
;*  lit un nombre de deux chiffres en base 10 et rend le resultat de sa
;*  conversion en base 2
;*  La donnee est supposee correcte
;*
;*PASSAGE DES PARAMETRES
;*   RESULTAT : AL = valeur du nombre en base 2
;*-------------------------------------------------------------------------
lire :	 MOV AH, 1				; on prend la valeur des dizaines du premier chiffre (ascii)
		 INT 21h
		 SUB AL, '0'			; on la transforme en binaire
		 MOV BH, AL				; on l'enregistre dans le registre BH
		
		 MOV AH, 1				; on prend la valeur des unités du premier chiffre (ascii)
		 INT 21h				
		 SUB AL, '0'			; on la transforme en binaire
		 MOV BL, AL				; on l'enregistre dans le registre BL
		 
		 MOV AH, 1				; on prend la valeur des dizaines du second chiffre (ascii)
		 INT 21h
		 SUB AL, '0'			; on la transforme en binaire
		 MOV CH, AL				; on l'enregistre dans le registre CH
		 
		 MOV AH, 1				; on prend la valeur des unités du second chiffre (ascii)
		 INT 21h
		 SUB AL, '0'			; on la transforme en binaire
		 MOV CL, AL				; on l'enregistre dans le registe CL
		 
		 ; on regarde si le PGCD est calculable où si on doit stopper l'exécution du programme
		 
		 CMP BL, 0				; on regarde si le chiffre des unités du premier nombre est égal à 0
		 JE suite				; si ce n'est pas le cas, on va à next, sinon
		 JNE formatage
		 
suite :	 CMP BH, 0
		 JE impossible 
		 JNE formatage
		 
next :   CMP CL, 0				; on regarde si les unités du second nombre est différent de 0		 
		 JNE formatage
		 
		 CMP CH, 0				; on regarde si le chiffre des unités du second nombre est égal à 0
		 JE impossible 			; si c'est le cas, l'utilisateur a rentré un entier égal à 0, on s'arrête
		 JNE formatage
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

formatage : 
		; on remet les valeurs en ascii pour pouvoir les afficher
		 ADD CL, '0'
		 ADD CH, '0'
		 ADD BL, '0'
		 ADD BH, '0'
		 
		 ; on "remplit" le message d'affichage
		 MOV MSG2[12],CL
		 MOV MSG2[11],CH
		 MOV MSG2[21],BL
		 MOV MSG2[20],BH
		 
		 ; on le "reprépare" à travailler avec
		 SUB CL, '0'
		 SUB CH, '0'
		 SUB BL, '0'
		 SUB BH, '0'
		 
		 ; on range les chiffres car ils étaient stockées en 0109 et on veut 0019
		 ; on le fait pour le premier chiffre
		 MOV AL, BH
		 MOV BH, 0
		 IMUL DIX
		 ADD BX, AX
		 
		 ; puis pour le second
		 MOV AL, CH
		 MOV CH, 0
		 IMUL DIX
		 ADD CX, AX
		 
tq : 	 CMP CX, BX
		 JE affichage
		 JLE cond
	
         SUB CX, BX
		 JMP tq
		 
cond :   SUB BX, CX
		 JMP tq
;*------------------------------------------------------------------------
;*SOUS-PROGRAMME       ECRIRE
;*------------------------------------------------------------------------
;*SPECIFICATION
;*   convertit une valeur binaire <=99 en base 10, et affiche le resultat
;*   a l'ecran. Cette valeur est supposee etre <=99
;*
;*PASSAGE DES PARAMETRES
;*   ENTREE : AL = valeur a afficher en base 2
;*-------------------------------------------------------------------------

affichage : 
		 ; on a trouvé le PGCD, on l'affiche
		 MOV AX, BX
		 IDIV DIX
			
		 ; on prépare le stockage dans la chaine
		 ADD AH, '0'
		 ADD AL, '0'
		 
		 ; on stocke le PGCD dans la chaine qui sera affichée
		 MOV MSG2[28], AH
		 MOV MSG2[27], AL
		 
		 ; on affiche
		 MOV DX, offset MSG2
		 MOV AH, 09h
		 INT 21H
		 JMP lire
		 
impossible : 		 
		 MOV DX, offset MSG1
		 MOV AH, 09h
		 INT 21H
		 JMP fin
		 
fin : 
		 MOV AH, 4CH
		 INT 21H
		 end start 