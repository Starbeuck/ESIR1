package rationnel;

import types.Rationnel;
import util.Couple;
import util.Outils;

public class RationnelCouple implements Rationnel {
	private Couple <Integer, Integer> c;

	/**
	 * constructeur 
	 * @param num : le numerateur de notre rationnel
	 * @param den : le denominateur 
	 * @pre den!=0
	 * @return le rationnel simple num/den
	 * @post  den>0 && fraction irreductible 
	 */
	public RationnelCouple (int num, int den){
		assert den!=0 : "veuillez inserer un dÈnominateur non nul ";

		if( den<0){
			num=num*(-1);
			den=den*(-1);
		}

		if (num!=0){

			// on rend la fraction irreductible 
			int pgcd =Outils.pgcd(Math.abs(num), den);

			den= den/pgcd;
			num = num/pgcd;
		}

		Couple<Integer, Integer> result = new Couple <Integer,Integer>(num,den);
		this.c=result;

		//System.out.println ( "Rationnelcouble crÈe");

		assert den >0 : "le denominateur doit etre positif"; 
		assert (num!=0 &&Outils.pgcd(Math.abs(num), den)==1)|| (num==0) : "la fraction est reductible";
	}

	/**
	 * constructeur
	 * @param a
	 */
	public RationnelCouple (int a){
		this.c=new Couple <Integer, Integer> (a,1);
	}
	
	/**
	 * constructeur RationnelCouple ‡ partir d'un RationnelSimple
	 * @param r		: RationnelSimple
	 */
	public RationnelCouple(Rationnel r){
		int num = r.getNumerateur();
		int den= r.getDenominateur();

		this.c=new Couple <Integer,Integer>(num,den);
		//System.out.println ( "RationnelCouble crÈe");
	}

	/**
	 * on verifie que deux rationnels sont egaux
	 * @param r		: RationnelSimple
	 */
	@Override
	public boolean equals(Rationnel r) {
		return (this.c.getFirst()==r.getNumerateur()&& this.c.getSecond()==r.getDenominateur());
	}

	/**
	 * on calcule la somme entre deux rationnel
	 * @param r		: RationnelSimple
	 */
	@Override
	public Rationnel somme(Rationnel r) {
		int wait =r.getDenominateur()* this.c.getFirst()+ r.getNumerateur()*this.c.getSecond();
		return new RationnelSimple (wait , r.getDenominateur()*this.c.getSecond());
	}

	/**
	 * on calcule l'inverse
	 */
	@Override
	public Rationnel inverse() {
		assert this.c.getFirst() !=0: "impossible d'inverser, le numerateur vaut 0 ";
		return new RationnelCouple (this.c.getSecond(), this.c.getFirst());
	}

	/**
	 * calculer la valeur r√©elle du rationnel this
	 * @return valeur r√©elle du rationnel this
	 */
	@Override
	public double valeur() {
		return (double) this.c.getFirst()/this.c.getSecond();
	}

	/**
	 *  @return repr√©sentation affichable d'un rationnel
	 * numerateur / denominateur
	 * ou numerateur (si denominateur = 1)
	 */
	@Override
	public String toString(){
		String tostring=""+this.c.getFirst();
		if (this.c.getSecond()!=1){
			tostring=tostring+ "/" + this.c.getSecond(); 
		}
		return tostring;
	}

	//accesseurs 
	@Override
	public int getNumerateur() {
		return this.c.getFirst(); 

	}

	@Override
	public int getDenominateur() {
		return this.c.getSecond();
	}

	@Override
	public int compareTo(Rationnel autre) {
		return autre.getDenominateur()* this.c.getFirst()- autre.getNumerateur()*this.c.getSecond();
	}

	public static void main(String[] args) {
		// Rationnel r1 = new RationnelCouple(3);
		//System.out.println(r1.getNumerateur());
		Rationnel r1 = new RationnelCouple(-21, -7);
		Rationnel r2 = new RationnelCouple(r1);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r1==r2);
	}
}
