import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mesure_Tri {

	/* Affiche un tableau d'entiers
	 */
	public static void afficher(int [] t){
		System.out.print("[");
		for(int k=0;k<t.length;k++)
			System.out.print(" "+t[k]+" ");
		System.out.println("]");
	}
	
	/* Cr�e un fichier nomFichier.m contenant du code matlab initialisant un tableau T_nomFichier qui contient les �l�ments de tab
	*/
	public static void tab_to_matlab(String nomFichier, List<Integer> tab) {
		Iterator<Integer> i = tab.iterator();
		String s="T_"+nomFichier+"=[";
		while(i.hasNext())
				s += i.next()+" ";
		s += "];";
		PrintWriter fich;
		try {
			fich = new PrintWriter(new BufferedWriter (new FileWriter(nomFichier+".m")));
			fich.println(s);
		    fich.close();
		} catch (IOException e) {
			System.err.println("Probl�me: " + e.getMessage()) ;
		    System.exit(2) ;
		}
	}
	
	public static void main(String[] args) {
		//V�rification de l'impl�mentation des tris
		int [] t = new int[10];
		int [] t2 = new int[10];
		for(int k=0;k<t.length;k++){ //initialisation al�atoire
			t[k]=(int) (Math.random()*100);
			t2[k]=t[k];
		}
		System.out.print("Avant le tri : "); afficher(t);
	//	Tri.triInsertion(t);
	//	System.out.print("Apr�s le tri par insertion: "); afficher(t);
		Tri.triFusion(t2);
		System.out.print("Apr�s le tri fusion:        "); afficher(t2);
		
		//Analyse du temps d'ex�cution
		
		//A FAIRE : modifier les 3 valeurs suivantes
		int taille_init = 1000000;
		int taille_incr = 100000;
		int taille_fin  = 5000
				000;
		
		List<Integer> tab_tailles = new ArrayList<Integer>();
		List<Integer> tab_temps = new ArrayList<Integer>();
		for(int n = taille_init; n<taille_fin; n=n+taille_incr){			  
			tab_tailles.add(n); //on sauvegarde la taille
			t = new int[n];
			//A FAIRE : initialisation de t
			int i=0;
			while( i<n){
				//t[i]=i;
				//t [i]=t.length-i;
				t[i]=(int)(Math.random()*100);
				i++;
			}
			long date1 = System.currentTimeMillis(); //on lance le chrono
			Tri.triFusion(t); //on trie le tableau 
			long date2 = System.currentTimeMillis(); //on arr�te le chrono
			tab_temps.add((int)(date2 - date1)); //on sauvegarde le temps
			System.out.println("Temps de calcul pour n="+n+" : "+ tab_temps.get(tab_temps.size()-1)+" millisecondes.");
		}
		
		//conversion des tableaux au format matlab
		tab_to_matlab("tailles", tab_tailles); 
		tab_to_matlab("temps", tab_temps);
	}

}
