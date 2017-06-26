/**** on va ici implémenter la transformée de Fourier rapide 1D ****/
//import CpxTab.java;

public class FFT_1D {

	//"combine" c1 et c2 selon la formule vue en TD
	// c1 et c2 sont de même taille
	// la taille du résultat est le double de la taille de c1
	public static CpxTab combine(CpxTab c1, CpxTab c2) {
		assert (c1.taille()==c2.taille()) : "combine: c1 et c2 ne sont pas de même taille, taille c1="+c1.taille()+" taille c2="+c2.taille();
		//A FAIRE
		CpxTab res = new CpxTab(c1.taille()*2);

		for(int i=0;i<c1.taille();i++){
			double a=Math.cos(2*Math.PI*i/res.taille());
			double b= Math.sin(2*Math.PI*i/res.taille());
			
			//on remplit la 1er moitié
			double sommereel=c1.get_p_reel(i)+c2.get_p_reel(i)*a-b*c2.get_p_imag(i);
			res.set_p_reel(i,sommereel );
			double sommeimag=c1.get_p_imag(i)+a*c2.get_p_imag(i)+b*c2.get_p_reel(i);
			res.set_p_imag(i, sommeimag);
			
			//on remplit la seconde
			int k=c1.taille()+i;
			double sommereelfin=c1.get_p_reel(i)-c2.get_p_reel(i)*a+b*c2.get_p_imag(i);
			res.set_p_reel(k,sommereelfin);
			double sommeimagfin=c1.get_p_imag(i)-a*c2.get_p_imag(i)-b*c2.get_p_reel(i);
			res.set_p_imag(k, sommeimagfin);
		}

		return res;
	}

	//renvoie la TFD d'un tableau de complexes
	//la taille de x doit être une puissance de 2
	public static CpxTab FFT(CpxTab x) {
		//A FAIRE : Test d'arrêt
		if (x.taille()==1) return x;

		assert (x.taille()%2==0) : "FFT: la taille de x doit être une puissance de 2";

		//A FAIRE : Décomposition en "pair" et "impair" et appel récursif
		CpxTab cpair = new CpxTab(x.taille()/2); 
		CpxTab cimpair = new CpxTab(x.taille()/2);
		
		int ip =0;
		int ii = 0;
		for (int i = 0; i<x.taille() ; i++){
			if(i%2==0){
				cpair.set_p_reel(ip, x.get_p_reel(i));
				cpair.set_p_imag(ip, x.get_p_imag(i));
				ip++;
			} else {
				cimpair.set_p_reel(ii, x.get_p_reel(i));
				cimpair.set_p_imag(ii, x.get_p_imag(i));
				ii++;
			}
		}
		
		CpxTab cpairFFT = FFT(cpair);
		CpxTab cimpairFFT = FFT(cimpair);
		
		return combine(cpairFFT, cimpairFFT);
	}

	//renvoie la TFD d'un tableau de réels
	//la taille de x doit être une puissance de 2
	public static CpxTab FFT(double[] x) {
		return FFT(new CpxTab(x));
	}

	//renvoie la transformée de Fourier inverse de y
	public static CpxTab FFT_inverse(CpxTab y) {
		//A FAIRE
		CpxTab res = (FFT(y.conjugue()).conjugue());
		double mutl = 1./y.taille();
		for(int i=0; i<res.taille(); i++){
			res.set_p_reel(i, res.get_p_reel(i)*mutl);
			res.set_p_imag(i, res.get_p_imag(i)*mutl);
		}
		
		return res;
	}

	//calcule le produit de deux polynômes en utilisant la FFT
	//tab1 et tab2, sont les coefficients de ces polynômes
	// CpxTab sera le tableau des coefficients du polynôme produit (purement réel)
	public static CpxTab multiplication_polynome_viaFFT(double[] tab1, double[] tab2) {

		//on commence par doubler la taille des vecteurs en rajoutant des zéros à la fin (cf TD)
		double[] t1 = new double[2*tab1.length], t2 = new double[2*tab1.length];
		for (int i = 0; i < tab1.length; i++) {
			t1[i] = tab1[i];
			t2[i] = tab2[i];
		}

		CpxTab t11 = new CpxTab(t1);
		CpxTab t22 = new CpxTab(t2);
		return 	FFT_inverse(CpxTab.multiplie(FFT(t11),FFT(t22)));
	}


	//renvoie un tableau de réels aléatoires
	//utile pour tester la multiplication de polynômes
	public static double[] random(int n) {
		double[] t = new double[n];

		for (int i = 0; i < n; i++)
			t[i] = Math.random();
		return t;
	}

	//effectue la multiplication de polynômes représentés par coefficients
	// p1, p2 les coefficients des deux polynômes P1 et P2
	// renvoie les coefficients du polynôme P1*P2
	private static double [] multiplication_polynome_viaCoeff(double[] p1, double[] p2){

		int n = p1.length + p2.length - 1;
		double a,b;
		double [] out = new double[n];
		for (int k = 0; k < n; k++) {
			for (int i = 0; i <= k; i++) {
				a = (i<p1.length) ? p1[i]:0;
				b = (k-i<p2.length) ? p2[k-i] : 0;
				out[k] += a*b;
			}
		}
		return out;
	}


	//affiche un tableau de réels
	private static void afficher(double [] t){
		System.out.print("[");
		for(int k=0;k<t.length;k++){
			System.out.print(t[k]);
			if (k<(t.length-1))
				System.out.print(" ");
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		/* Pour tester exo 2: calculez et affichez TFD(1,2,3,4) */
		//A FAIRE
		
		double [] test = {1,2,3,4};
		//CpxTab t1 = new CpxTab(test);
		//System.out.println(FFT(t1));
	
		/* Pour tester exo 3: calculez et affichez TFD_inverse(TFD(1,2,3,4)) */
		//A FAIRE	
		
		//System.out.println(FFT_inverse(FFT(t1)));
		/* Pour tester Partie 3 : multiplication polynomiale */
		
		System.out.println("-----------------------------------------------------");
		System.out.println("   Comparaison des 2 méthodes de multiplications polynomiales");
		double[] t5 = {1,2,3,4};
		double[] t6 = {-3,2,0,-5};
		System.out.println("mult via FFT  --> "+multiplication_polynome_viaFFT(t5, t6));
		System.out.print(  "mult via coeff -> ");afficher(multiplication_polynome_viaCoeff(t5, t6));
		 
		
		// Pour étude du temps de calcul 
		int n = 16;  // taille des polynômes à multiplier (testez différentes valeurs en gardant des multiples de 2)

		System.out.println("Temps de calcul pour n="+n);
		//double[] tab1 =random(n),tab2 = random(n);
		double[] tab1=new double[n];
		int k=5;
		for(int i=0;i<n;i++){
			tab1[i]=4+2*Math.sin(2*Math.PI*2*i/n)+Math.cos(2*Math.PI*i*7/n);
		}
		
		CpxTab t1 = new CpxTab(tab1);
		System.out.println(FFT(t1));
		
		/*long date1, date2;
		date1 = System.currentTimeMillis();
		multiplication_polynome_viaCoeff(tab1, tab2);
		date2 = System.currentTimeMillis();
		System.out.println("   via Coeff: " + (date2 - date1));

		date1 = System.currentTimeMillis();
		multiplication_polynome_viaFFT(tab1, tab2);
		date2 = System.currentTimeMillis();
		System.out.println("   via FFT  : " + (date2 - date1));*/
		 

	}

}
