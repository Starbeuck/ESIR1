#include "afficher.h"
/*------------------------------------------------------------
  Les trois fonctions ...
  -----------------------------------------------------------*/
int plus (int x, int y) {
  afficher(x,y,x+y);
  return x + y;
}

int moins(int x, int y) {
  afficher(x,y,x-y);
  return x - y;
}

int mult (int x, int y) {
  afficher(x,y,x*y);
  return x * y;
}
