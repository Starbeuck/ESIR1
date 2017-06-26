import java.util.Scanner;
import java.lang.Object;
import java.util.*;
import java.*;

public class TriDicho {

	/**
	 * initialiser un tableau avec les valeurs d'une suite de nombres entiers lus au clavier
	 * la suite est terminée par valfin
	 * 
	 * @param tnb		: tableau de nombres (déjà crée) à initialiser
	 * @param valfin	: valeur qui met fin à la saisie : ne doit pas être placée dans le tableau
	 * @param entree	: scanner d'entrée où se fait la lecture
	 * @return nbEntree	: nombre de nombres placés dans le tableau (N)
	 * @post 			: le tableau contient N nombres entiers (0<=nbEntree<=tnb.length)
	 */
	public static int lireTableau(int [] tnb, int valfin, Scanner entree){

		int nbEntree = 0;
		int nouvEntree = 0;
		int [] tab = tnb;
		int indice = 0;

		while((nbEntree<tab.length)&&(nouvEntree!=valfin)){
			System.out.println("Entree un nombre : ");
			nouvEntree = entree.nextInt();

			if(nouvEntree!=valfin){
				tab[indice] = nouvEntree;
				indice++;
				nbEntree++;
			}
		}

		assert 0<=nbEntree && nbEntree<=tab.length : "Nombre de nombres incorrects"; 
		return nbEntree;
	}

	/**
	 * afficher les nbs premiers éléments d'un tableau
	 * @param tnb 	: tableau initialisé
	 * @param nb  	: nombre d'éléments en tête du tableau
	 * @pre 		: 0<=nbEntree<=tnb.length
	 * @post 		: le tableau n'est pas modifié
	 */
	public static void afficherTableau (int [] tnb, int nb){

		String contenuTab = "Les "+nb+" premiers éléments du tableau sont : ";
		int indice = 0;

		while(indice<nb){
			contenuTab+=tnb[indice]+"; ";
			indice++;
		}

		System.out.println(contenuTab);
	}

	/**
	 * Effectue le tri des N premiers éléments d'un tableau de nombres d'entier
	 * @param tnb	: tableau à trier jusqu'à nb
	 * @param nb	: nombre d'éléments à trier
	 */
	public static int[] TrierInsertion(int [] tnb, int nb){
		assert nb<tnb.length : "le nombre d'elements à trier n'est pas valide";
		int element = 0;


		for (int indice=1; indice<nb; indice++){// on compare a partir de l'élément 2 par rapport au premier 
			element = tnb[indice];
			int compt=indice;
			while(compt>0){
				if (tnb[compt-1]>element){// c'est quon est pas arrivé la ou on doit le placer donc on décale
					tnb[compt] = tnb[compt-1];
					compt--;
				}else {
					tnb[compt]= element;// bim on doit le placer la 
					compt=-12;// et on sort du while parce qu'on a pas besoin de décaler les élement avant dans le tableau

				}
				if (compt ==0){// ca veut dire qu'on l'a pas calé avant notre élément (ie c'est le plsu petit
					tnb[0]=element;
				}
			}
		}
		return tnb;
	}

	public static int rechercheDichotomique(int cherche, int[] tnb, int nb){
		boolean trouve = false; //faux tant qu'on a pas trouvé cherche
		int debut = 0; //indice début tableau
		int fin = nb;//indice fin de recherche dans le tableau
		int milieu;//indice du milieu de tableau
		int indice = -1; //valeur retourné si cherche n'est pas dans tnb

		if(tnb.length!=0){
			while(!trouve && ((fin-debut)>1)){

				milieu = (debut+fin)/2; // on détermine le milieu

				trouve = (tnb[milieu]==cherche); //on regarde si le milieu correspond à notre valeur

				//on choisit la moitié du tableau que l'on garde
				if(tnb[milieu]>cherche){
					fin = milieu;
				}else{
					debut = milieu;
				}

				if(tnb[debut]==cherche){
					indice = debut;
				}

			}

			if (tnb[0]==cherche){
				return 0;
			}
		}

		return indice;
	}

	public static void main(String[] args) {

		//Test lireTableau
		//Scanner scan = new Scanner (System.in);
		//int [] tab = new int [3];
		//System.out.println(lireTableau(tab, 2,scan));

		//Test afficherTableau
		//int tab[] = {1,2,3,4};
		//afficherTableau(tab, 0);

		//TestTrierInsertion
		int tab[] = {};
		System.out.println(rechercheDichotomique(-7,tab,1));
	}

}
