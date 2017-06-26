package main;
import java.util.Scanner;
import outilsTris.*;
import tris.*;

public class client {

	public static boolean Trie(int [] tab1, int[] tab2){
		boolean trie = true ; 
		for(int i=0; i<tab1.length ; i++){
			trie = (tab1[i]==tab2[i]) && trie;
		}
		return trie;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		//l'user choisit un fichier
		System.out.println("Choisisez vous le fichier 50000, 00097 ou 01290 ?");
		String fichier = scan.nextLine();

		// on ouvre et trie le tableau correspondant
		String aOuvrir = fichier+".txt";
		int [] tabATrier = OutilsTris.lireTableau(aOuvrir);
		double alea = Math.random()*10;
		alea = (int)alea;

		if (alea%2==0){
			TriTas.trier(tabATrier,tabATrier.length);
			System.out.println("TriTas utilise");	
		}else{
			TriRapide.trier(tabATrier,tabATrier.length);
			System.out.println("TriRapide utilise");
		}
		//on le compare au tableau trie correspondant
		String fichierTrie = fichier+"t.txt";
		int [] TabTrie = OutilsTris.lireTableau(fichierTrie);
		System.out.println(Trie(tabATrier,TabTrie));

		//on enregistre le tableau trier dans un fichier
		OutilsTris.enregistrerTableau(tabATrier, tabATrier.length, "Fichier Trie", "txt");
	}
}
