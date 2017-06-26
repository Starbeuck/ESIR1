package tableau;


import types.Array;

import types.Tableau;

public class Block <T>implements Tableau<T> {
	
	private int m_taille=0;
	private Array<T>  block ;
	
	//Constructeurs 
	public Block (int cap){
		assert cap>0: "la capacite du tableau doit etre positive";
		this.block= new Array <T>(cap);
	}
	 /**
	   * Determiner la taille du tableau
	   * @return nombre d'elements presents dans le tableau
	   */
	  public int size(){
		  return this.m_taille;
	  }

	  /**
	   * Déterminer si le tableau est vide
	   * @return vrai si le tableau est vide
	   */
	  public boolean empty(){
		  return this.m_taille==0;
	  }

	  /**
	   * Determiner si le tableau est plein
	   * @return vrai s'il n'est plus possible d'ajouter d'élément dans le tableau
	   */
	  public boolean full(){
		  return this.m_taille==this.block.length();
	  }

	  /**
	   * Renvoyer l'element d'indice i
	   * 
	   * @param i : indice de l'élément à consulter
	   * @pre 0 <= i < this.size()
	   * @return valeur de l'élément d'indice i
	   */
	  public T get(int i){
		  assert i>=0 : " l'indice doit �tre superieur ou egal a 0";
		  assert i<this.m_taille : "l'indice doit etre inferieur a la taille du tableau ";
		  return this.block.get(i);
	  }

	  /**
	   * Modifier l'élément d'indice i
	   * 
	   * @param i : indice de l'élément à modifier
	   * @pre 0 <= i < this.size()
	   * @param v : nouvelle valeur de l'élément d'indice i
	   */
	  public void set(int i, T v){
		  assert i>=0 : " l'indice doit �tre superieur ou egal a 0";
		  assert i<this.m_taille : "l'indice doit etre inferieur a la taille du tableau ";
		  this.block.set(i, v);
	  }

	  /**
	   * Ajouter un élément en fin de tableau
	   * 
	   * @param x : élément à ajouter en fin de tableau
	   * @pre : ! this.full()
	   */
	  public void push_back(T x){
		  assert !this.full() : "oh oh plus de place dans le tablau ";
		  this.block.set(this.m_taille, x);
		  this.m_taille +=1;
	  }

	  /**
	   * Supprimer le dernier élément du tableau
	   * @pre : ! this.empty()
	   */
	  public void pop_back(){
		  assert !this.empty() : "le tableau est vide vous ne pouvez rien supprimer"; 
		  this.m_taille-=1; 
	  }


}
