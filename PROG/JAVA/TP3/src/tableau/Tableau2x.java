package tableau;

import types.Tableau;
import types.Array;

public class Tableau2x <T>implements Tableau<T> {

	private Block <T> bloc; 	private int blockcapacity;

	public Tableau2x (int capacite){
		assert capacite>0: "la capacite du tableau doit etre positive";
		this.bloc=new Block<T> (capacite);
	
	}
	/**
	 * Déterminer la taille du tableau
	 * @return nombre d'éléments présents dans le tableau
	 */
	public int size(){
		return this.bloc.size();
	}

	/**
	 * Déterminer si le tableau est vide
	 * @return vrai si le tableau est vide
	 */
	public boolean empty(){
		return this.bloc.empty();
	}

	/**
	 * Determiner si le tableau est plein
	 * @return vrai s'il n'est plus possible d'ajouter d'élément dans le tableau
	 */
	public boolean full(){
		return false ; 
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
		assert i<this.size() : "l'indice doit etre inferieur a la taille du tableau ";
		return this.bloc.get(i);
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
		assert i<this.size() : "l'indice doit etre inferieur a la taille du tableau ";
		this.bloc.set(i, v);
	}

	/**
	 * Ajouter un élément en fin de tableau
	 * 
	 * @param x : élément à ajouter en fin de tableau
	 * @pre : ! this.full()
	 */
	public void push_back(T x){
		if (this.bloc.full()){
			Block <T>tnb= new Block <T>(this.size()*2);
			for (int i=0;i<this.size();i++){
				tnb.push_back(this.bloc.get(i));
			}	
			tnb.push_back(x);
			this.bloc=tnb;
		}else{

		this.bloc.push_back(x);
		}

	}

	/**
	 * Supprimer le dernier élément du tableau
	 * @pre : ! this.empty()
	 */
	public void pop_back(){
		assert !this.empty() : "le tableau est vide vous ne pouvez rien supprimer"; 
		this.bloc.pop_back(); 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
