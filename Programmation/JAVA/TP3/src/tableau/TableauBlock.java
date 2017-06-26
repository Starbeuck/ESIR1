package tableau;

import types.Tableau;

public class TableauBlock<T> implements Tableau<T> {
	private static final int tailleDefaut = 128 ;

	private Tableau2x<Block<T>> tab;
	private int m_blockcapacity;
	private int nbelmt;

	//constructeur
	public TableauBlock (int tabcapacity, int blockcapacity ){
		assert tabcapacity>0: "la capacite du tableau doit etre positive";
		assert blockcapacity>0: "la capacite du block doit etre positive";
		this.tab= new Tableau2x <Block<T>>(tabcapacity);
		tab.push_back(new Block <T>(blockcapacity));
		m_blockcapacity=blockcapacity;
		this.nbelmt=0;
	}
	public TableauBlock ( int tabcapacite){
		assert tabcapacite>0: "la capacite du tableau doit etre positive";
		this.tab=new Tableau2x<Block<T>>(tabcapacite);
		tab.push_back(new Block <T> (tailleDefaut));
		m_blockcapacity=tailleDefaut;

		this.nbelmt=0;
	} 
	/**
	 * Determiner la taille du tableau
	 * @return nombre d'elements presents dans le tableau
	 */
	@Override
	public int size() {
		return this.nbelmt;
	}

	/**
	 * Déterminer si le tableau est vide
	 * @return vrai si le tableau est vide
	 */
	@Override
	public boolean empty() {
		return this.nbelmt==0;
	}

	/**
	 * Determiner si le tableau est plein
	 * @return vrai s'il n'est plus possible d'ajouter d'élément dans le tableau
	 */
	@Override
	public boolean full() {
		return false;
	}
public  Block<T> getblock(int i){
	return this.tab.get(i/m_blockcapacity);
}

public  T getElement(Block <T> a, int i){
	return a.get(i%m_blockcapacity);
}
	/**
	 * Renvoyer l'element d'indice i
	 * 
	 * @param i : indice de l'élément à consulter
	 * @pre 0 <= i < this.size()
	 * @return valeur de l'élément d'indice i
	 */
	@Override
	public T get(int i) {
		assert i>=0 : " l'indice doit �tre superieur ou egal a 0";
		assert i<this.size() : "l'indice doit etre inferieur au nombre d'�l�ments total  ";
		return getElement(getblock(i),i);
	}

	/**
	 * Modifier l'élément d'indice i
	 * 
	 * @param i : indice de l'élément à modifier
	 * @pre 0 <= i < this.size()
	 * @param v : nouvelle valeur de l'élément d'indice i
	 */
	@Override
	public void set(int i, T v) {
		assert i>=0 : " l'indice doit �tre superieur ou egal a 0";
		assert i<this.size(): "l'indice doit etre inferieur au nombre d'�l�ments total  ";
		this.tab.get( i/m_blockcapacity).set(i%m_blockcapacity,v);
	}
	/**
	 * Ajouter un élément en fin de tableau
	 * 
	 * @param x : élément à ajouter en fin de tableau
	 * @pre : ! this.full()
	 */
	@Override
	public void push_back(T x) {
		if( this.tab.get(this.tab.size()-1).full()){
			this.tab.push_back(new Block (this.m_blockcapacity));


		}
		this.tab.get(this.tab.size()-1).push_back(x);
		this.nbelmt+=1;

	}

	/**
	 * Supprimer le dernier élément du tableau
	 * @pre : ! this.empty()
	 */
	@Override
	public void pop_back() {
		assert !this.empty(): "le tableau est d�j� vide ";
		if (this.tab.get(this.tab.size()-1).empty()){
			this.tab.pop_back();
		}
		this.nbelmt--;
	}

}

