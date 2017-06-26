import java.util.Scanner;
import java.lang.Object;
import java.util.*;
import java.*;

public class TriDicho {

	/**
	 * initialiser un tableau avec les valeurs d'une suite de nombres entiers lus au clavier
	 * la suite est termin�e par valfin
	 * 
	 * @param tnb		: tableau de nombres (d�j� cr�e) � initialiser
	 * @param valfin	: valeur qui met fin � la saisie : ne doit pas �tre plac�e dans le tableau
	 * @param entree	: scanner d'entr�e o� se fait la lecture
	 * @return nbEntree	: nombre de nombres plac�s dans le tableau (N)
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
	 * afficher les nbs premiers �l�ments d'un tableau
	 * @param tnb 	: tableau initialis�
	 * @param nb  	: nombre d'�l�ments en t�te du tableau
	 * @pre 		: 0<=nbEntree<=tnb.length
	 * @post 		: le tableau n'est pas modifi�
	 */
	public static void afficherTableau (int [] tnb, int nb){

		String contenuTab = "Les "+nb+" premiers �l�ments du tableau sont : ";
		int indice = 0;

		while(indice<nb){
			contenuTab+=tnb[indice]+"; ";
			indice++;
		}

		System.out.println(contenuTab);
	}

	/**
	 * Effectue le tri des N premiers �l�ments d'un tableau de nombres d'entier
	 * @param tnb	: tableau � trier jusqu'� nb
	 * @param nb	: nombre d'�l�ments � trier
	 */
	public static int[] TrierInsertion(int [] tnb, int nb){
		assert nb<tnb.length : "le nombre d'elements � trier n'est pas valide";
		int element = 0;


		for (int indice=1; indice<nb; indice++){// on compare a partir de l'�l�ment 2 par rapport au premier 
			element = tnb[indice];
			int compt=indice;
			while(compt>0){
				if (tnb[compt-1]>element){// c'est quon est pas arriv� la ou on doit le placer donc on d�cale
					tnb[compt] = tnb[compt-1];
					compt--;
				}else {
					tnb[compt]= element;// bim on doit le placer la 
					compt=-12;// et on sort du while parce qu'on a pas besoin de d�caler les �lement avant dans le tableau

				}
				if (compt ==0){// ca veut dire qu'on l'a pas cal� avant notre �l�ment (ie c'est le plsu petit
					tnb[0]=element;
				}
			}
		}
		return tnb;
	}

	public static int rechercheDichotomique(int cherche, int[] tnb, int nb){
		boolean trouve = false; //faux tant qu'on a pas trouv� cherche
		int debut = 0; //indice d�but tableau
		int fin = nb;//indice fin de recherche dans le tableau
		int milieu;//indice du milieu de tableau
		int indice = -1; //valeur retourn� si cherche n'est pas dans tnb

		if(tnb.length!=0){
			while(!trouve && ((fin-debut)>1)){

				milieu = (debut+fin)/2; // on d�termine le milieu

				trouve = (tnb[milieu]==cherche); //on regarde si le milieu correspond � notre valeur

				//on choisit la moiti� du tableau que l'on garde
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
