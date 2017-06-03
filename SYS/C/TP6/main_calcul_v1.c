#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

// type de la fonction à appeler
typedef
int (* RealFunction)(int, int);


void afficher(char txt[],
	      RealFunction calculer,
	      int x, int y)
{
  printf("%-10s de %2d 	et %2d : %4d\n", txt, x, y, calculer(x, y));
}



int
main(int argc, char * argv[])
{
  // ouverture de la bibliothèque mathématique (man dlopen)
  void * handle = dlopen("libcalcul_v1.so", RTLD_LAZY);
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

    afficher("Le résultat de la fonction : ",root, a, b);

    return 0;
  }
