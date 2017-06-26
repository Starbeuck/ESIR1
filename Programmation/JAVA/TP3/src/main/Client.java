package main;

import java.util.Scanner;
import tableau.Block;
import types.Array;
import types.Tableau;
import java.util.Random;

public class Client {

	/**
	 * permet de déterminer si n est premier, à partir du tableau de nombre
	 * premier
	 * 
	 * @param n
	 *            le nombre dont on doit dire s'il est premier
	 * @param t
	 *            le tableau des entiers premier en [2,n-1]
	 * @return vrai si n est premier
	 */
	public static boolean estPremier(int n, Tableau<Integer> t) {
		assert n >= 2 : "n doit etre superieur ou egal a 2";

		int indice = 0;

		while (indice < t.size()) {
			if (n % t.get(indice) == 0) {
				return false;
			}
			indice++;
		}

		return true;
	}

	/**
	 * permet de calculer les nombres premiers en [2 , n-1]
	 * 
	 * @param n
	 *            la borne superieure de l'intervalle
	 * @param t
	 *            le tableau a remplir par les nombres premiers
	 * @return le dernier element ajoute du tableau
	 */
	public static int calculerNombresPremiers(int n, Tableau<Integer> t) {
		assert n >= 2 : "n doit etre plus grand ou egal a 2";

		int indice = 1;
		t.push_back(2);
		int i = 2;

		while (!t.full() && i <= n) {

			if (estPremier(i, t)) {
				t.push_back(i);
				indice++;
			}
			i++;
		}

		return i;
	}

	/**
	 * prend en parametres un tableau et l'affiche
	 * 
	 * @param t
	 *            tableau a afficher
	 * @return string contenant les elements du tableau
	 */
	public static String afficher(Tableau<Integer> t) {

		String res = "";
		for (int i = 0; i < t.size(); i++) {
			res += t.get(i) + " ";
		}

		return res;
	}

	/**
	 * remplit un tableau contenant nb element de manière aléatoire
	 * 
	 * @param nb
	 *            nombre d'elements dans le tableau
	 * @return le tableau remplit d'element au hasard entre [0, nb]
	 */
	public static Tableau remplirHasard(int nb) {
		Tableau<Integer> t = new Block(nb);

		while (!t.full()) {
			int random_integer = (int) (Math.random() * nb);
			t.push_back(random_integer);
		}

		return t;
	}

	/**
	 * fonction auxiliaire qui permet d'effectuer une recherche dichotomique 
	 * @param cherche		entier cherché
	 * @param tnb			tableau dans lequel on cherche l'entier
	 * @param borneg		borne inferieur du tableau
	 * @param borned		borne superieur du tableau
	 * @return				true si l'entier est present dans le tableau
	 */
	public static boolean rechercheDichotomique(Integer cherche, Tableau<Integer> tnb, int borneg, int borned) {
		int milieu = (borneg + borned) / 2;
		if (borneg == borned)
			return cherche.equals(tnb.get(borneg));

		if (tnb.get(milieu) >= cherche)
			return rechercheDichotomique(cherche, tnb, borneg, milieu);

		return rechercheDichotomique(cherche, tnb, milieu+1, borned);

	}

	/**
	 * fonction "principale" de recherche dichotomique avec moins de param pour un usage plus facile
	 * @param cherche		entier cherché
	 * @param tnb			tableau où l'on cherche
	 * @return				true si l'entier est present dans le tableau
	 */
	public static boolean rechercheDichotomique(Integer cherche, Tableau<Integer> tnb) {
		int borneg = 0;
		int borned = tnb.size() - 1;
		return rechercheDichotomique(cherche, tnb, borneg, borned);
	}

	/**
	 * supprime dans t1 les elements presents dans t2
	 * @param t1		tableau d'entiers
	 * @param t2		tableau trié en ordre croissant
	 * @return			le nombre d'elements supprimés
	 */
	public static int eliminerPresent(Tableau<Integer> t1, Tableau<Integer> t2) {
		int i = 0;
		int nbElementSupprimes = 0;
		
		while (i < t1.size()) {
			if (rechercheDichotomique(t1.get(i), t2) == false) {
				i++;
			} else {
				t1.set(i, t1.get(t1.size() - 1));
				t1.pop_back();
				nbElementSupprimes += 1;
			}
		}
		return nbElementSupprimes;
	}

	public static void main(String[] args) {

		// 3.3.1 & 3.3.2
		Tableau<Integer> t = new Block(100);
		System.out.println("Veuillez entrer un entier");
		Scanner scan = new Scanner(System.in);
		int entier = scan.nextInt();
		Tableau<Integer> res = remplirHasard(calculerNombresPremiers(entier, t) - 1);

		// System.out.println(afficher(t));
		System.out.println(afficher(res));

		Tableau<Integer> prems = new Block(200);
		calculerNombresPremiers(1000, prems);
		System.out.println(eliminerPresent(res, prems));
		System.out.println(afficher(res));

	}

}
