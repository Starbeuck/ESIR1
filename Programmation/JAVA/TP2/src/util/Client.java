package util;
import types.Rationnel;
import java.util.Scanner;
import org.junit.Assert;
import rationnel.RationnelCouple;
import rationnel.RationnelSimple;

public class Client {

	/**
	 * fonction qui permet de créer un rationnel
	 * @param num
	 * @param den
	 * @pre le dénominateur n'est pas nul
	 * @return un rationnel num/den
	 */
	static Rationnel makeRationnel(int num, int den){
		assert den!=0 : "le dénominateur n'est pas nul";
		
		//pour generer de manière "aleatoire" un RationnelCouple ou un RationnelSimple
		if (num%2==0){
			return new RationnelCouple(num,den);
		}else{
			return new RationnelSimple(num,den);
		}
	}

	/**
	 * permet de créer un rationnel à partir des entiers fournis par l'utilisateur
	 * @param input
	 * @return le rationnel crée à partir des entrées de l'utilisateur 
	 */
	public static Rationnel lireRationnel (Scanner input){

		System.out.print("Veuillez saisir un numérateur : ");
		int num = input.nextInt();
		System.out.print("Veuillez saisir un dénominateur non nul : ");
		int dem = input.nextInt();
		assert dem!=0 : "numerateur non valide";

		Rationnel resultat = makeRationnel(num, dem);

		//System.out.print("Le rationnel crée est : " );
		return resultat;
	} 

	/**
	 * permet d'afficher un tableau de rationnel jusqu'à un certain element
	 * @param lesRationnels 		: tableau contenant des rationnels
	 * @param nb					: nombre d'elements que l'on va afficher
	 */
	static void afficher(Rationnel[] lesRationnels, int nb){
		assert nb>=0 : "le nombre saisit est inférieur à 0";
		assert nb<=lesRationnels.length : "le nombre saisit est supérieur à la taille du tableau";

		for (int indice=0; indice<nb; indice++){
			System.out.println(lesRationnels[indice]+" = "+lesRationnels[indice].valeur());
		}
	}

	/**
	 * insérer le rationnel nouveau dans le tableau lesRationnels
	 * @param nouveau		: élément à ajouter au tableau
	 * @param lesRationnels	: tableau trié
	 * @param nb			: nombre d'éléments dans le tableau avant ajout
	 * @pre 				: tableau trié (ordre croissant)
	 * @pre 				: 0<=
	 * @post 				: tableau trié (ordre croissant)
	 */
	static void insererRationnel (Rationnel nouveau, Rationnel[]lesRationnels, int nb){
		assert nb>=0: "oh oh probleme nb arriver"; 
		assert nb< lesRationnels.length: "oh oh nb trop grand";
		
		int indicebis=0;
		Rationnel actuel = nouveau;//pour l'initialiser a quelquechose 
		
		if (nb==0){// si tableau vide on l'insère dans la premiere case 
			lesRationnels[0]=nouveau;
		}else if (lesRationnels[nb-1].compareTo(nouveau)<=0){// si c'est le plus grand du tableau 
			lesRationnels[nb]=nouveau;
		}else{
			for (int indice=0; indice<=nb; indice++){
				if (lesRationnels[indice].compareTo(nouveau)>0 ){//lesRationnelsindice>nouveau ie on insere a cet endroit
					actuel= lesRationnels[indice];
					lesRationnels[indice]=nouveau; 
					indicebis=indice+1;
					indice=nb+3;// pour sortir du for 
				}
			}
			if (indicebis<=nb){
				while(indicebis<=nb){
					Rationnel suivant= lesRationnels[indicebis];
					lesRationnels[indicebis]=actuel;
					actuel=suivant;
					indicebis++;
				}
			}
		}
	}
	
	/**
	 * somme des nb premiers elements du tableau
	 * @param lesRationnels
	 * @param nb
	 * @return
	 */
	static Rationnel sommeRationnels(Rationnel[] lesRationnels, int nb){
		assert nb>= 0: "nb trop petit";
		assert nb<= lesRationnels.length: " nb trop grand par rapport a la taille du tableau";
		
		Rationnel sommeratio=lesRationnels[0];
		
		for (int indice=1; indice<nb; indice++){
			sommeratio=sommeratio.somme(lesRationnels[indice]);
		}
		return sommeratio;
	}

	public static void main(String[] args) {
		

		/*1.3.1
		 * 
		 * Rationnel precedent = new RationnelSimple(0);
			Rationnel courant = new RationnelSimple(0);

			do{
				Scanner scan = new Scanner (System.in);
				courant = lireRationnel(scan);

				if(courant.getDenominateur()==0||courant.getNumerateur()==0){
					break;
				}else{

					System.out.print("courant = "+courant.toString()+"; "+courant.toString()+" + "+precedent.toString()+
							" = "+courant.somme(precedent)+"; inverse = "+courant.inverse()+";  valeur = "+courant.valeur()+"; ");

					if(!courant.equals(precedent)){
						if(courant.compareTo(precedent)<0){
							System.out.println(courant+" < "+precedent+"; "+courant+" != "+precedent);
						}else if(courant.compareTo(precedent)>0){
							System.out.println(courant+" > "+precedent+"; "+courant+" != "+precedent);
						}
					}else{
						System.out.println(courant+" = "+precedent+"; "+courant+" = "+precedent);
					}
				}

				precedent = courant;
			}while(courant.getDenominateur()!=0||courant.getNumerateur()!=0);
		 */

		//1.3.2
		// on créer un tableau vide
		/*System.out.println("Veuillez saisir la taille du tableau : ");
			Scanner scan = new Scanner (System.in);
			int capacity = scan.nextInt();
			Rationnel [] tnb = new Rationnel [capacity];

			// on créer un rationnel "tampon"
			Rationnel tampon = makeRationnel(1,1);
			Rationnel before = makeRationnel(0,1);

			//on a la taille contenu dans le tableau à l'initialisation
			int taille = 0;

			do{
				Scanner temp = new Scanner (System.in);
				tampon = lireRationnel(temp);

				//on insère le rationnel lu
				insererRationnel(tampon, tnb, taille);
				taille ++;

				//on insère la somme de tampon et tampon-1
				if(taille!=1){before = tnb[taille-1];};
				Rationnel somme = before.somme(tampon);
				insererRationnel(somme, tnb, taille);
				taille ++;

				//on insère l'inverse de tampon
				Rationnel inverse = tampon.inverse();
				insererRationnel(inverse, tnb, taille);
				taille++;

				afficher(tnb, taille);

			}while(taille<=capacity-3);
		 */

	}

}
