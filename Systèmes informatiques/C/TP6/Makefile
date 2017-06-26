# petit tutoriel : https://perso.univ-rennes1.fr/jean-christophe.engel/make

#------------------------------------------------------------------------
# les variables
#------------------------------------------------------------------------

# compilateur C
CC	 = gcc

# options GÉNÉRALES de compilation (a priori, rien à ajouter)
CFLAGS   = -ggdb -Wall -std=c99

# options GÉNÉRALES d'édition de liens (a priori, rien à ajouter)
LDFLAGS  =

# les programmes exécutables à fabriquer (À COMPLÉTER)
PROGS	= ptrfn_v1 ptrfn_v2 dynload_v1 main_calcul_v1

# pour générer tout par défaut
all : $(PROGS)

# 1.1 ptrfn_v1 et ptrfn_v2 : règles implicites

# ------------------------------------------------------------------------
# 1.2. charger une bibliothèque par programme (libm.so)
# À COMPLÉTER
dynload_v1 : dynload_v1.o
	$(CC) $(LDFLAGS) -o $@ $< -ldl

dynload_v2 : dynload_v2.o
	$(CC) $(LDFLAGS) -o $@ $< -ldl

# ------------------------------------------------------------------------
# 1.3 fabriquer et utiliser une bibliothèque chargée dynamiquement

# 1.3.1 première version

# À COMPLÉTER
calcul_v1.o : calcul_v1.c
	$(CC) -c $(CFLAGS) $< -o $@ -fpic

# À COMPLÉTER
libcalcul_v1.so : calcul_v1.o
	$(CC) $(LDFLAGS) -o $@ $< -shared

# À COMPLÉTER
main_calcul_v1 : main_calcul_v1.o
	$(CC) $(LDFLAGS) -o $@ $< -ldl

# 1.3.2 deuxième version
# À COMPLÉTER
main_calcul_v2 : main_calcul_v2.o
	$(CC) $(LDFLAGS) -o $@ $< -ldl -export-dynamic

calcul_v2.o : calcul_v2.c
	$(CC) -c $(CFLAGS) $< -o $@ -fpic

# À COMPLÉTER
libcalcul_v2.so : calcul_v2.o
	$(CC) $(LDFLAGS) -o $@ $< -shared


# 1.3.3 troisième version

# divers
.PHONY : clean

clean:
	-@rm *.o *~ $(PROGS)
