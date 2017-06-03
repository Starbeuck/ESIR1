// Charger la bibliothèque mathématique (libm.so)
// et calculer la racine carrée d'un réel

#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>


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

  // chercher la fonction sqrt dans la bibliothèque
  RealFunction root =  dlsym(handle, "sqrt");

  char * error = dlerror();
  if ((error) != NULL)  {
    // erreur : fonction non trouvée
    fprintf(stderr, "%s\n", error);
    exit(EXIT_FAILURE);
  }
  // tout est correct
  double x;
  do {
    printf("Saisir un nombre réel >= 0 : ");
    scanf("%lf", &x);
  } while (x < 0);
  printf("Racine carrée de %lf = %lf\n", x, root(x));
  dlclose(handle);
  exit(EXIT_SUCCESS);
}
