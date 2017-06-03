package tris;

public class TriTas {
	/**
	 * ajouter tnb[p] au tas formé par les p premiers éléments du tableau tnb 
	 * @ param tnb 		tableau dont les p premiers éléments forment un tas
	 * @param p			indice de l'element a ajouter au tas 
	 * @pre 			1<p<=tnb.length
	 * @post			les p+1 premiers elements du tableau forment un tas
	 */
	public static void ajouterTas(int[] tnb, int p){
		
		assert p>=1 : "valeur minimale = 1";
		assert p<tnb.length : "ce n'est pas dans les bornes du tableau";
		
		int i=p;
		
		// on fait "glisser" l'element jusqu'à sa place dans le tas
		while(tnb[i]>tnb[(i-1)/2]){
			
			int tmp= tnb[(i-1)/2];
			tnb[(i-1)/2]=tnb[i];
			tnb[i]=tmp;
			i=(i-1)/2;
		}
		
		// on augmente la taille du tas
		p=p+1;
	}
	
	
	/**
	 * supprimer l'élément maximum du tas et réorganiser le reste du tas 
	 * @param tnb		tableau dont les p premiers éléments forment un tas
	 * @param p			nombre d'éléments dans le tas 
	 * @pre 			1<p<=tnb.length
	 * @post			place l'élément maximum en tnb[p-1]: les p-1 premiers éléments de tnd forment un tas
	 */
	public static void supprimerMax(int[] tnb, int p){
		assert p>=1 : "valeur minimale = 1 ";
		assert p<=tnb.length : "hors borne";
		
		//on diminue le tas en supprimant le max que l'on inverse avec le premier element
		int tmp= tnb[0];
		tnb[0] = tnb[p-1];
		tnb[p-1]=tmp;
		p--;
		
		//on range le tas ampute d'un element
		int i = 0;
		while(((2*i+1<p)&&(tnb[i]<tnb[2*i+1])) || ((2*i+2<p)&&(tnb[i]<tnb[2*i+2]))){
			
			if(2*i+2>=p){
				if(tnb[i]<tnb[2*i+1]){
					tmp= tnb[2*i+1];
					tnb[2*i+1]=tnb[i];
					tnb[i]=tmp;
					i=2*i+1;
				}
			}
			else if (tnb[2*i+1] > tnb[2*i+2]){
				tmp= tnb[2*i+1];
				tnb[2*i+1]=tnb[i];
				tnb[i]=tmp;
				i=2*i+1;
			}
			else{
				tmp= tnb[2*i+2];
				tnb[2*i+2]=tnb[i];
				tnb[i]=tmp;
				i=2*i+2;
			}			
		}
		
	}

	/**
	 * trier un tableau d'entiers en ordre croissant avec l'algorithme du tri par tas 
	 * @param tnb : tableau à trier 
	 * @param nb : nombre d'éléments dans le tableau 
	 * @pre 1<=nb<=tnb.length
	 */
	public static void trier (int [] tnb, int nb){
		assert nb>=1 && nb<=tnb.length: "nb en dehors des bornes, caca";

		int i=1;
		while(i<nb){
			ajouterTas(tnb,i);
			i++;
		}

		while(nb>1){
			supprimerMax(tnb,nb);
			nb--;
		}

	}
}
