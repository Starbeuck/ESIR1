package rationnel;

import types.Rationnel;
import util.Outils; 
public class RationnelSimple implements Rationnel {
	//attributs
	private int m_numerateur; 
	private int m_denominateur; 

	/**
	 * constructeur 
	 * @param num : le numerateur de notre rationnel
	 * @param den : le denominateur 
	 * @pre den!=0
	 * @return le rationnel simple num/den
	 * @post  den>0 && fraction irreductible 
	 */

	public RationnelSimple (int num, int den){
		assert den!=0 : "veuillez inserer un d�nominateur non nul ";

		// on rend la fraction irreductible
		if(den<0){
			num=num*(-1);
			den=den*(-1);
		}

		if (num!=0){
			int pgcd =Outils.pgcd(Math.abs(num), den);
			den= den/pgcd;
			num = num/pgcd;
		}

		this.m_denominateur = den;
		this.m_numerateur = num;

		//System.out.println( " rationnelSimple cr�e");

		assert den >0 : "le denominateur doit etre positif"; 
		assert (num!=0 &&Outils.pgcd(Math.abs(num), den)==1)|| (num==0): "la fraction est reductible";
	}

	/**
	 * initialiser un rationnel à partir d'un entier : nb/1
	 * @param num : valeur du numérateur
	 */
	public RationnelSimple (int num) {
		this.m_denominateur = 1; 
		this.m_numerateur =num;
	}

	/**
	 * initialiser un rationnel à partir d'un autre
	 * @param r : rationnel à dupliquer
	 */
	public RationnelSimple (Rationnel r) {
		this.m_numerateur = r.getNumerateur();
		this.m_denominateur =r.getDenominateur();
	}

	@Override
	public boolean equals(Rationnel r) {
		return (this.m_numerateur==r.getNumerateur() && this.m_denominateur == r.getDenominateur() );
	}

	@Override
	public Rationnel somme(Rationnel r) {
		int wait =r.getDenominateur()* this.m_numerateur+ r.getNumerateur()*this.m_denominateur;
		return new RationnelSimple (wait , r.getDenominateur()*m_denominateur);
	}

	@Override
	public Rationnel inverse() {
		assert this.m_numerateur !=0: "impossible d'inverser, le numerateur vaut 0 ";
		return new RationnelSimple (this.m_denominateur, this.m_numerateur);
	}

	/**
	 * calculer la valeur réelle du rationnel this
	 * @return valeur réelle du rationnel this
	 */
	@Override
	public double valeur() {
		return (double)m_numerateur/m_denominateur;
	}

	/**
	 *  @return représentation affichable d'un rationnel
	 * numerateur / denominateur
	 * ou numerateur (si denominateur = 1)
	 */
	@Override
	public String toString(){
		String tostring = ""+m_numerateur;
		if (m_denominateur!=1){
			tostring=tostring+"/"+ m_denominateur; 
		}
		return tostring;
	}

	//accesseurs 
	@Override
	public int getNumerateur() {
		return m_numerateur ; 
	}

	@Override
	public int getDenominateur() {
		return m_denominateur; 
	}

	@Override
	public int compareTo(Rationnel autre) {
		return autre.getDenominateur()* m_numerateur- autre.getNumerateur()*m_denominateur;
	}
}
