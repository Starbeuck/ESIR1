/*
 * liste.hh
 *
 *  Created on: 2 mars 2017
 *      Author: 15001727
 */

#ifndef LISTE_HH_
#define LISTE_HH_
#include "cyclicNode.h"
#include <cassert>
#include<iostream>


template<class T>

class Liste{

protected :
	typedef DataStructure::cyclicNode<T> Chainon;
	Chainon * m_sentinelle;
	int m_size;

public :
	class const_iterator{

		friend class Liste;
		const Chainon * zero;
		const Chainon * actuel;
		/**
		 * constructeur
		 */
		const_iterator(const Chainon * now, const Chainon *sent){
			zero= sent;
			actuel=now;
		}
	public:


		/**
		 * opérateur ++ préfixé
		 * positionne l'itérateur sur l'élément suivant
		 * @pre l'itérateur désigne une position valide dans la liste (<> end())
		 * @return nouvelle valeur de l'itérateur
		 */
		const_iterator & operator ++(){
			assert (actuel!=zero);
			actuel=actuel->next();
			return (*this);
		}
		/**opérateur -- préfixé
		 * pisotionne l'itérateur sur l'élément précédent
		 * @pre l'itérateur ne désigne pas l'élément de tête
		 * @return nouvelle valeur de l'itérateur
		 */
		const_iterator & operator --(){
			assert(actuel!=zero->next());
			actuel=actuel-> previous();
			return (*this);
		}
		/**operateur d'indirection * (accès non modifiable)
		 * @pre l'itérateur désigne une position valide dans la liste <>end
		 * @return valeur de l'éléement désigné par l'itérateur
		 */
		const T & operator *() const{
			assert(actuel!=zero);
			return actuel->data();
		}
		/**opérateur d'indirection -> (accès non modifiable)
		 * @pre l'itérateur désigne une position valide
		 */
		const T * operator -> () const{
			assert(actuel!=end());
			return &(actuel->data());
		}
		/** surcharge de l'opérateur==
		 *
		 */
		bool  operator ==(const const_iterator & c) {
			return (c.actuel ==actuel && c.zero==zero);
		}
		/**
		 * surcharge de l'opérateur !=
		 */
		bool  operator !=(const const_iterator  & c) {
			return (actuel != c.actuel || zero!=c.zero);
		}
	};

	class iterator{
	protected:
		friend class Liste<T>;
		Chainon * zero;
		Chainon * actuel;

		/**
		 * constructeur
		 */
		iterator(Chainon * now, Chainon * sentinelle){
			zero = sentinelle;
			actuel = now;
		}
	public:

		/**
		 * opérateur ++ préfixé
		 * positionne l'itérateur sur l'élément suivant
		 * @pre l'itérateur désigne une position valide dans la liste (<> end())
		 * @return nouvelle valeur de l'itérateur
		 */
		iterator & operator ++(){
			assert (actuel!=zero);
			actuel=actuel->next();
			return (*this);
		}

		/**opérateur -- préfixé
		 * pisotionne l'itérateur sur l'élément précédent
		 * @pre l'itérateur ne désigne pas l'élément de tête
		 * @return nouvelle valeur de l'itérateur
		 */
		iterator & operator --(){
			assert(actuel!=zero->next());
			actuel=actuel-> previous();
			return (*this);
		}
		/**operateur d'indirection * (accès non modifiable)
		 * @pre l'itérateur désigne une position valide dans la liste <>end
		 * @return valeur de l'éléement désigné par l'itérateur
		 */
		const T & operator *() {
			assert(actuel!=zero);
			return (actuel->data());
		}
		/**opérateur d'indirection -> (accès non modifiable)
		 * @pre l'itérateur désigne une position valide
		 */
		const T * operator -> (){
			assert(actuel!=end());
			return &(actuel->data());
		}
		/** surcharge de l'opérateur==
		 *
		 */
		bool operator ==(const iterator & c){
			return (c.actuel ==actuel && c.zero==zero);
		}
		/**
		 * surcharge de l'opérateur !=
		 */
		bool operator !=(const iterator & c){
			return (this->actuel != c.actuel || this->zero != c.zero);
		}

	};

	/**
	 * constructeur sans paramètres
	 * initialise une liste vide contenant uniquement la sentinelle
	 */
	Liste(){
		m_sentinelle = new Chainon;
		m_size=0;
	}
	/**
	 * constructeur de copie
	 */
	Liste<T>(const Liste<T> & l){
		copierListe(l);
	}

	void copierListe(const Liste<T> & l){
		m_size=0;
		m_sentinelle = new Chainon;
		const_iterator it = l.begin();
		while(it != l.end()){
			this->push_back((*it));
			++it;
		}
	}

	Liste<T> & operator = (const Liste<T> & l){
		if(&l != this){
			if(m_sentinelle != NULL){
				delete m_sentinelle;
			}
			copierListe(l);
		}
		return (*this);
	}

	Liste<T>  operator + (const Liste<T> & l){
		Liste<T> somme;
		iterator it = begin();
		while(it != end()){
			somme.push_back((*it));
			++it;
		}
		const_iterator it2=l.begin();
		while(it2 != l.end()){
			somme.push_back((*it2));
			++it2;
		}
		return somme;
	}




	/**
	 * destructeur
	 * permet de détruire une liste
	 */
	virtual ~Liste(){
		while(!empty())
		{
			this->pop_front();
		}
		delete m_sentinelle;
	}






	/**
	 * détermine si la liste est vide
	 * @return true si c'est le cas, false sinon
	 */
	const bool empty() const{
		return this->m_size == 0;
	}

	/**
	 * retourne le nombre d'elements dans la liste
	 * @return le nombre d'element dans la liste
	 */
	const int size() const{
		return m_size;
	}


	/**
	 * permet d'acceder au premier element
	 * @return le premier element qui sera non modifiable
	 */
	const T & front() const{
		assert(!this->empty());
		return m_sentinelle->next()->data();
	}

	/**
	 * permet d'acceder au premier element
	 * @return le premier element que l'on pourra modifier
	 */
	T & front(){
		assert(!this->empty());
		return m_sentinelle->next()->data();
	}

	/**
	 * retourne le dernier element
	 * @return le dernier element qui sera non modifiable
	 */
	const T & back() const {
		assert(!this->empty());
		return m_sentinelle->previous()->data();
	}

	/**
	 * retourne le dernier element
	 * @return le dernier element qui sera modifiable
	 */
	T & back(){
		assert(!this->empty());
		return m_sentinelle->previous()->data();
	}

	/**
	 * insert un element en premier element
	 */
	void push_front(const T & c){
		Chainon *temp = new Chainon(c);
		m_sentinelle->insertAfter(temp);
		m_size++;
	}

	/**
	 * insert un element en fin de liste
	 */
	void push_back(const T & c){
		Chainon *temp = new Chainon(c);
		m_sentinelle->insertBefore(temp);
		m_size++;
	}

	/**
	 * supprime le premier element de la liste
	 */
	void pop_front(){
		assert(!empty());
		delete m_sentinelle->next();
		m_size--;
	}

	/**
	 * supprime le dernier element de la liste
	 */
	void pop_back(){
		assert(!empty());
		delete m_sentinelle->previous();
		m_size--;
	}
	/**
	 *  renvoie un iterateur sur le début de liste
	 * cet iterateur désigne le premier élément de la liste si elle n'est pas vide
	 * sinon il désigne la même position que l'itérateur renvoyé par end()
	 */
	const_iterator begin () const{
		return  const_iterator(m_sentinelle->next(), m_sentinelle);
	}

	/**
	 * renvoie un itérateur qui désigne une position située après le dernier élément
	 */
	iterator end(){
		return iterator (m_sentinelle, m_sentinelle);
	}


	/**
	 *  renvoie un iterateur sur le début de liste
	 * cet iterateur désigne le premier élément de la liste si elle n'est pas vide
	 * sinon il désigne la même position que l'itérateur renvoyé par end()
	 */
	iterator begin() {
		return  iterator(m_sentinelle->next(), m_sentinelle);
	}
	/**
	 * renvoie un itérateur qui désigne une position située après le dernier élément
	 */
	const_iterator end() const{
		return const_iterator (m_sentinelle, m_sentinelle);
	}


	/**
	 * insère un nouvel élément dont la valeur est donnée en paramètre avant l'élément désigné par l'itérateur
	 * @return itérateur qui désigne l'élément inséré
	 * @param iterator désigne l'endroit où placer l'élément
	 * @param x: élément à insérer
	 */
	iterator insert (iterator position, const T & x){

		if(position==this->end()){
			push_back(x);
		}else{
			Chainon * temp=new Chainon(x);
			position.actuel->insertBefore(temp);
			m_size++;
		}
		return --position;
	}
	/**
	 * methode qui supprime l'élément désigné par l'iterator
	 * @param iterator désignant l'élément à supprimer
	 * @return iterator designant lélément qui suit celui supprimé
	 */
	iterator erase(iterator asupprimer){
		iterator retour(asupprimer.actuel->next(),asupprimer.zero);
		delete asupprimer.actuel;
		this->m_size--;
		return retour;
	}


};
template<class T>
std::ostream & operator << (std::ostream & out, const Liste<T> & l){
	typename Liste<T>::const_iterator it = l.begin();
	out << "Liste : " << std::endl;
	typename Liste<T>::const_iterator itfin = l.end();
	--itfin;
	while(it != itfin){
		out<<(*it)<<",";
		++it;
	}
	out << (*it) << std::endl;
	return out;
}
/**
 * chercher un élément dans la séquence[ premier, dernier[
 * @param premier : début de la séquence
 * @param dernier : fin de séquence
 * @param x : valeur cherchée
 * @return iérateur qui désigne x s'il est trouvé:
 * 			cet itérateur est égal a dernier si x est absent
 */
template <class T>
typename Liste<T>::iterator
find(typename Liste<T>::iterator premier,
		typename Liste<T>::iterator dernier,
		const T & x){
	typename Liste<T>::iterator it ( dernier.actuel,dernier.zero);

	while(premier!=dernier){
		if((*premier)==x||(* dernier)==x){
			if((*premier)== x){
				return premier;
			}else{
				return dernier;
			}
		}
		++premier;
		--dernier;
	}
	return it;
}

#endif /* LISTE_HH_ */
