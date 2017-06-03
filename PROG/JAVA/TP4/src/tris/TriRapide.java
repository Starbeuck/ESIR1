package tris;

public class TriRapide {
	/**
	 * partager les �l�ments d'un sous-tableau en 2 parties 
	 * @param T: tableau � partager 
	 * @param binf,bsup: indices du premeir et du dernier �l�ment du sous-tableau � partager 
	 * @pre 0<= binf, bsup<t.length
	 * @post partage les �l�ments de T compris entre les indices binf et bsup selon le principe d�crit au paragraphe 2.2 et met le pivot � sa place 
	 * @return indice auquel � �t� plac� le pivot 
	 */
	static int partager (int[] T, int binf, int bsup){
		assert binf>=0 : "trop petit";
		assert bsup<T.length: "trop grand";

		int valPivot = T[binf]; 				//on affecte une valeur au pivot
		int deb = binf+1; 						//on fait un sous tableau prive du pivot
		int fin = bsup; 			

		while(deb<fin){

			while(deb<fin && T[fin]>valPivot){fin--;}		//si le dernier element est bon par rapport au pivot on ne le bouge pas
			while(deb<fin && T[deb]<=valPivot){deb++;}		//si le premier element est bon par rapport au pivot on le bouge pas

			//deux valeurs sont mal placees, on les �change
			if(deb<fin){
				int temp = T[deb];
				T[deb]=T[fin];
				T[fin]=temp;
			}
		}

		//on a fini le partage, on insere le pivot
		if (T[deb] > valPivot) deb--;
	    T[binf] = T[deb];
	    T[deb] = valPivot;

		return deb;
	}

	/** triRapide : trier r�cursivement un sous-tableau (algorithme du tri rapide)
	 * @param T: tableau � trier	
	 * @param binf, bsup : indices du premier et du dernier �l�ment du sous tableau � trier 
	 * @pre 0<=binf, bsup<T.length
	 */
	static void triRapide (int[]T, int binf, int bsup){
		assert binf>=0 && bsup<T.length : "les bornes ne sont pas valides ";

		if(binf<bsup){
			int indicePivot = partager(T,binf,bsup);
			triRapide(T, binf, indicePivot-1);
			triRapide(T, indicePivot+1, bsup);
		}
	}

	/** trier : trier un tableau par ordre croissant avec l'algorithme de tri rapide 
	 * @param T: tableau � trier 
	 * @param nb : nb nombre d'�l�ments � trier dans le tableau 
	 * @pre 1<nb<=T.length
	 */
	public static void trier (int[] T, int nb){
		assert nb>1 && nb<=T.length : "nb est en dehors des bornes";
		triRapide (T, 0, nb-1);
	}
}
