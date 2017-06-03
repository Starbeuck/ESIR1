#include <stdio.h>

/** ---------------------------------------------------------
 * lit sur l'entrée standard une suite de caractères terminée
 * par % et la recopie sur la sortie standard en doublant chaque
 * caractère
 * ---------------------------------------------------------*/

int main()
{
  int c;
  do {
    c = getchar();
    putchar(c);
    putchar(c);
  } while (c!='%');
  return 0;
}

