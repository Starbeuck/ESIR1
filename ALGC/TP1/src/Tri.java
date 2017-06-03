public class Tri {

	/*Tri le tableau t via la m�thode "Tri par insertion"
	 * cf TD1 exo 2
	 */
	public static void triInsertion(int[] t){
		int size=t.length;

		for(int j=1;j<size;j++){//parcours du tableau et tri un par un des elements
			int cle=t[j]; // sauvegarde de l'element a trier
			int i=j-1;
			while (i>=0 && t[i]>cle){ //parcours inverse du tableau pour inserer notre element a sa bonne place dans le tableau 
				t[i+1]=t[i];// on décale les elelemnts deja tries mais superieurs a l'element a inserer
				i--;
			}
			t[i+1]=cle;//on inserer l'element trie
		}
	}



	/*Tri le tableau t via la m�thode "Tri fusion"
	 * cf TD2 exo 5
	 */
	public static void triFusion(int[] t){
		if (t.length>0)
			triFusion(t, 0, t.length-1);
	}

	/* Sous-fonction (r�cursive) pour le tri fusion
	 * Trie le sous-tableau t[debut]..t[fin]
	 */
	private static void triFusion(int[] t, int debut, int fin){
		if(debut<fin){
			int milieu= (debut+fin)/2;
			triFusion(t,debut,milieu);
			triFusion(t,milieu+1,fin);
			fusionner(t,debut,milieu,fin);
		}
	}

	/* Sous-fonction pour le tri fusion
	 * Suppose que, dans le tableau t, 
	 *       les 2 sous-tableaux t[deb1]..t[fin1] et t[fin1+1]..[t[fin2] sont d�j� tri�s
	 * Fusionne ces 2 sous-tableaux pour que le sous-tableau t[deb1]..t[fin2] soit tri�
	 */
	private static void fusionner(int[] t, int deb1, int fin1, int fin2){
		int deb2 = fin1+1;
		//on recopie les �l�ments du d�but du tableau
		int[] t1 = new int[fin1-deb1+1];
		for(int i=deb1;i<=fin1;i++){
			t1[i-deb1]=t[i];
		}
		int compt1=deb1;
		int compt2=deb2;
		for(int i=deb1;i<=fin2;i++){        
			if (compt1==deb2) //c'est que tous les �l�ments du premier tableau ont �t� utilis�s
				break; //tous les �l�ments ont donc �t� class�s
			else if (compt2==(fin2+1)){ //c'est que tous les �l�ments du second tableau ont �t� utilis�s
				t[i]=t1[compt1-deb1]; //on ajoute les �l�ments restants du premier tableau
				compt1++;
			}
			else if (t1[compt1-deb1]<t[compt2]){
				t[i]=t1[compt1-deb1]; //on ajoute un �l�ment du premier tableau
				compt1++;
			}
			else{
				t[i]=t[compt2]; //on ajoute un �l�ment du second tableau
				compt2++;
			}
		}
	}
}