#include "afficher.h"
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

// type de la fonction à appeler
typedef
int (* RealFunction)(int, int);


void afficher(int x, int y, int z)
{
  printf("Le résultat de l'opération est %1i et de %2i est %3i \n",x,y,z);
}


int
main(int argc, char * argv[])
{
  // ouverture de la bibliothèque mathématique (man dlopen)
  void * handle = dlopen("libcalcul_v2.so", RTLD_LAZY);
  if (handle == NULL) {
    // erreur : bibliothèque non trouvée
    fprintf(stderr, "%s\n", dlerror());
    exit(EXIT_FAILURE);
  }
    dlerror();    /* Clear any existing error */

    char nomf[1000];
    printf("Saisir le nom d'une fonction : ");
    scanf("%s",nomf);
    // chercher la fonction demandée dans la bibliothèque
    RealFunction root =  dlsym(handle, nomf);

    char * error = dlerror();
    if ((error) != NULL)  {
      // erreur : fonction non trouvée
      fprintf(stderr, "%s\n", error);
      exit(EXIT_FAILURE);
    }
    // tout est correct
    int a, b;
    printf("Donner deux nombres entiers: ");
    scanf("%d%d", &a, &b);

    root(a,b);
    return 0;
  }
