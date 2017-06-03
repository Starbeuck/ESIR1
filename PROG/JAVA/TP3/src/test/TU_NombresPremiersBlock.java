package test;

import tableau.Block;
import types.Tableau;

public class TU_NombresPremiersBlock extends ATU_NombresPremiers {

  // création de tableau
  public Tableau<Integer> makeTableau(int capinit) {
    return new Block<Integer>(capinit);
  }

  // Fonctions à tester : À changer selon signature

  // Déterminer si n est premier
  boolean estPremier(int n, Tableau<Integer> nombresPremiers) {
    return main.Client.estPremier(n, nombresPremiers);
  }
  // Calculer les nombres premiers dans l'intervalle [2,N]
  int calculerNombresPremiers(int N, Tableau<Integer> nombresPremiers) {
    return main.Client.calculerNombresPremiers(N, nombresPremiers);
  }
  // remplir un tableau avec nb nombres tirés au hasard dans [0..nb[
  Tableau<Integer> remplirHasard(int nb) {
    return main.Client.remplirHasard(nb);
  }
  // éliminer du tableau t1 les éléments du tableau t2 (trié)
  int eliminerPremiers(Tableau<Integer> t, Tableau<Integer> nombresPremiers) {
    return main.Client.eliminerPresent(t, nombresPremiers);
  }
}
