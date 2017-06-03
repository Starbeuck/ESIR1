;*------------------------------------------------------------------------
;*MODULE                      TP3-système
;*------------------------------------------------------------------------
;* Auteur(s) : BRUNOY KEROULLAS  
;*
;* Formation :     ESIR1          Groupe: 1
;*-------------------------------------------------------------------------
;*--------------------------------------------------------------------------
              .MODEL  SMALL
              .STACK
              .CODE
              PUBLIC  LIRECH,ECRIRECH,CONCATCH
;*------------------------------------------------------------------------
;*        DECLARATION GLOBALES
;*------------------------------------------------------------------------
              .DATA
;
;constantes pour les E/S
;
LIRCAR        EQU     1           		; code MS/DOS de la lecture de caractere
ECRCAR        EQU     2           		; code MS/DOS de l'ecriture de caractere
;
;constantes du module
;
TMAX          EQU     100         		; taille de la zone de stockage
FIN_CH        EQU     0DH         		; code de fin de chaine en saisie (CR)
;
VRAI          EQU     1           		; code de vrai
FAUX          EQU     0          		; code de faux
;
D_TAILLE      EQU     0           		; deplacement pour champ "taille" du descripteur.
D_AD          EQU     2           		; deplacement pour champ "adresse" du descripteur.
;
;variables globales du module
;
ZONECAR       DB      TMAX DUP (?)    	; zone de stockage des caracteres

ADCARLIB      DW      TMAX            	; Nombre de caractŠres libres restant
              DW      OFFSET ZONECAR  	; adresse 1er caractère libre dans ZONECAR
;
;*
;*------------------------------------------------------------------------
;*SOUS-PROGRAMME      LIRECH
;*------------------------------------------------------------------------
;*SPECIFICATION
;*		lis des caractères jusque la rencontre de retour chariot et
;*		affecte les caractères lus non compris retour chariot à la
;*		chaine en cas d'anomalie, la chaine resultat est vide et erreur
;*		vaut vrai sinon erreur vaut faux
;*-------------------------------------------------------------------------
;* PASSAGE DES PARAMETRES
;*		VALEUR OU ADRESSE
;*------------------------------------------------------------------------
;* MISE EN OEUVRE
;* 		D_AD 		deplacement pour champ "adresse" du descripteur.
;*		D_TAILLE	deplacement pour champ "taille" du descripteur.
;*		ADCARLIB	zone de stockage
;*------------------------------------------------------------------------	

              .DATA
lch_ch        EQU     4
lch_err       EQU     6
lch_TPARAM    EQU     4

              .CODE

LIRECH:       
			  PUSH    BP
              MOV     BP,SP

              PUSH    AX
              PUSH    BX
              PUSH    DI
              PUSH    SI
                 
              MOV     BX,ADCARLIB+D_Ad 			;
              MOV     SI,0        				;
              MOV     CX,ADCARLIB+D_taille 		;
	
LCH_repeter:									; repeter jqa debordement ou fin suite
	
              MOV     AH,LIRCAR					;    	lire (car);
              INT     21H						;  
	  
              CMP     AL,FIN_CH					;    	qd car. lu = fin_ch --> fin suite
              JE      LCH_LURC					;
	
              CMP     CX,0						;
              JE      LCH_DEB					;    qd TRESTE=0 --> debordement
	
              SUB     CX,1						;    decremente RESTE
	
              MOV     [BX+SI],AL				;    stocke car. lu et met a jour
              ADD     SI,1						;    l'index
	
              JMP     LCH_repeter				; fin repeter
LCH_LURC:                             ;quand toi arriver fin 
												;--> On a lu "retour chariot"
              MOV     BX,ADCARLIB+D_ad			;      mise à jour du descripteur 
              MOV     DI,[BP]+lch_ch			;       de la chaine
              MOV     [DI]+D_ad,BX    			;
              MOV     [DI]+D_TAILLE,SI			;

              ADD     ADCARLIB+D_Ad,SI			;   Mise à jour du descripteur  ADCARLIB
              MOV     adcarlib+D_Taille,SI		;

              MOV     BX,[BP]+lch_err 			; 
              MOV     byte ptr[BX],Faux         ;   erreur:=faux        
              JMP     LCH_FIN                   ;
LCH_DEB:				;quand toi déborder 
              MOV     BX,[BP]+lch_ch             ;--> debordement
              MOV     word ptr [BX]+D_TAILLE,0   ;       met taille=0 dans 
                                                 ;       le desc
            
              MOV     BX,[BP]+LCH_ERR            ;
              MOV     byte ptr[BX],vrai          ;   erreur:=vrai
LCH_FIN:

              POP     SI
              POP     DI
              POP     BX
              POP     AX
              
              POP     BP
              RET     LCH_Tparam

;*
;*------------------------------------------------------------------------
;*SOUS-PROGRAMME      ECRIRECH
;*------------------------------------------------------------------------
;*SPECIFICATION
;*		affiche la suite de caractères de la chaîne ch
;*-------------------------------------------------------------------------
;* PASSAGE DES PARAMETRES
;*		VALEUR OU ADRESSE
;*------------------------------------------------------------------------
;* MISE EN OEUVRE
;* 		D_AD 		deplacement pour champ "adresse" du descripteur.
;*------------------------------------------------------------------------	

.DATA
lch_ch 	      EQU     4
lch_TPARAM    EQU     4

.CODE

ECRIRECH:

			PUSH BP
			MOV	 BP,	SP
													; libération mémoire
			PUSH AX
            PUSH BX
            PUSH CX
			PUSH DX
            PUSH SI
			
			MOV BX,		[BP]+lch_ch+D_AD 			;  on met l'adresse de la chaine dans BX
			MOV CX,		[BP]+lch_ch					;  on met la longueur de la chaine dans CX
			MOV SI, 	0
			
boucle:		MOV AH, 	02H							
			MOV DL, 	[BX+SI]						; on affiche le caractere à l'indice SI
			INT 21H
			
			ADD SI, 	1
			CMP SI, 	CX							; tant que l'indice < longueur(chaine), on continue
			JL boucle
			
l_fin:			
													; restauration de la pile
			POP AX
            POP BX
            POP CX
			POP DX
            POP SI	
			POP BP
			RET lch_TPARAM
			
;*
;*------------------------------------------------------------------------
;*PROCEDURE      CONCATCH
;*------------------------------------------------------------------------
;* SPECIFICATION
;*		affecte en utilisant la procedure COPIECH, à chr le résultat de 
;*		la concaténation de ch1 et ch2 ; ch1 et ch2 ne sont pas modifiées
;*		le descripteur chr doit être différent de ch1 et ch2.
;*		en cas d'anomalie, la chine résultat est vide et erreur vaut vrai
;*		sinon erreur vaut faux
;* PASSAGE DES PARAMETRES
;*		VALEUR OU ADRESSE
;*------------------------------------------------------------------------
;* MISE EN OEUVRE
;*		COPIECH		sous programme
;* 		D_AD 		deplacement pour champ "adresse" du descripteur.
;*		D_TAILLE	deplacement pour champ "taille" du descripteur.
;*		ADCARLIB	zone de stockage
;*------------------------------------------------------------------------			
.DATA
lch_TPARAM    EQU     4

.CODE

CONCATCH:	

			PUSH BP
			MOV	 BP,	SP	
													; libération mémoire
			PUSH AX									; indice de la chaine en cours
            PUSH BX									; longueur de la chaine
            PUSH CX									; indice de la nouvelle chaine
			PUSH DX									; longeuru de la nouvelle chaine
            PUSH DI
			
			MOV  CX, 	ADCARLIB+D_AD	        	; on met dans CX l'indice de la nouvelle chaine
			MOV  DX, 	[BP]+dep_ch1+D_TAILLE		; on met dans DX la longueur de la chaine 1
			ADD  DX, 	[BP]+dep_ch2+D_TAILLE		; et on ajoute la longueur de la chaine 2 à DX on a donc la longueur totale de la concatenation
			;code
			
con_fin:		  
			POP  SI
			POP  DX
			POP  CX
            POP  BX
            POP  AX

			POP	 BP
			RET	lch_TPARAM		
			
;*
;*------------------------------------------------------------------------
;*PROCEDURE      COPIECH
;*------------------------------------------------------------------------
;* SPECIFICATION
;*		recopie la chaine de caractères ch1 dans la zone de stockage
;* PASSAGE DES PARAMETRES
;*		VALEUR OU ADRESSE
;*------------------------------------------------------------------------
;* MISE EN OEUVRE
;* 		D_AD 		deplacement pour champ "adresse" du descripteur.
;*		D_TAILLE	deplacement pour champ "taille" du descripteur.
;*		ADCARLIB	zone de stockage
;*------------------------------------------------------------------------			
.DATA
lch_TPARAM    EQU     4
lch_ch 	      EQU     4
			
.CODE

COPIECH:
		
		PUSH BP
		MOV	 BP,SP	
												; libération mémoire
		PUSH AX									; registre tampon pour le caractere
        PUSH BX									; l'adresse du premier caractere de la chaine
        PUSH CX									; l'adresse du 1e caractere libre dans la memoire, avant traitement
		PUSH DX									; le nombre de caracteres libres restants
        PUSH DI									; longueur de la chaine
		PUSH SI									; indice de la bouche
		

		;code
		MOV BX,		[BP]+lch_ch+D_AD 			;  on met l'adresse de la chaine dans BX
		MOV  CX, 	ADCARLIB+D_AD 	       		; l'adresse du 1e libre dans CX
		MOV  DX,	ADCARLIB+D_TAILLE	    	; le nombre de caracteres restants
		MOV  DI, 	[BP]+lch_ch+D_TAILLE		; on met la taille de la chaine dans DI
		MOV  SI, 	0			                ; on initialise l'indice SI
		
c_boucle:
		MOV  AL, 	[BX+SI]						; AL <- chaine.charAt(SI)
		PUSH BX
		MOV  BX,	CX
		MOV  [BX+SI],AL							; on met AX dans la zone de données
		POP  BX
		
		ADD  SI , 1								; caractere suivant
		SUB  DX , 1								; il reste un caractere libre de moins
		CMP  SI , DI	  						; tant que SI < length(chaine), on boucle
		JL   c_boucle

c_done:		

		ADD  ADCARLIB+D_AD,SI  	    		;on deplace le pointeur sur la 1e case libre du nombre de caracteres lus
		SUB  ADCARLIB+D_TAILLE,SI     ;et on soustrait le nombre de caracteres lus du nombre de caracteres libres
		JMP  c_fin
		
c_fin:		
		POP  SI
		POP  DI
		POP  DX
		POP  CX
        POP  BX
        POP  AX

		POP	 BP
		RET	 lch_TPARAM	
			
END

			