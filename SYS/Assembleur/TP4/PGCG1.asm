         .model small
         .stack
         .data
x 		 db ?          		; initialisation de la variable x
y 		 db ?				; initialisation de la variable y
pgcd	 db ?				; initialisation du résultat du pgcd de x et y

         .code
debut:   
		 mov     ax,@data
         mov     ds,ax          ;initialisation de DS
         
         mov     al, x			; on déplace x dans le registre AL
		
		
tq : 	 						; tant_que (x<>y)
		 cmp		al, y			; 
		 je		fin_tq			; faire 
		
		 jl		sinon			;      si x>y  alors
		 sub 	al,y            ;         x:=x-y
		 jmp     fin_si         ;
		
sinon :							;        sinon
		 sub     y, al          ;            y:=y-x
		
fin_si:                         ;      fsi
         jmp     tq             ; fait
fin_tq:  
		 mov pgcd,al  			; on obtient le pgcd de x et y
            
		 end     debut
