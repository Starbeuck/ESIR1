// Charger la bibliothèque mathématique (libm.so)
// et calculer la racine carrée d'un réel

#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
#include <math.h>


// type de la fonction à appeler
typedef
double (* RealFunction)(double);

int
main(int argc, char * argv[])
{
  // ouverture de la bibliothèque mathématique (man dlopen)
  void * handle = dlopen("libm.so", RTLD_LAZY);
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
  double x;
  printf("Entrer une valeur : ");
  scanf("%lf", &x);
  printf("Le résultat de la fonction est = %lf\n", root(x));
  dlclose(handle);
  exit(EXIT_SUCCESS);
}
