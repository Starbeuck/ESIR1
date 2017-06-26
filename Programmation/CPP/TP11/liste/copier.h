#include "Liste.hh"
#include <cassert>
#include <iostream>
#include "cyclicNode.h"
/**
 * recherche une valeur dans une liste triée par valeurs croissantes
 * @param x : valeur à rechercher
 * @param l : liste triée
 * @return it : itérateur valant soit la valeur directement au dessus de celle trouvée
 * 									 soit si l'élément n'existe pas renvoie end()
 */
template<class T>
typename Liste<T>::iterator
rechercheVal(Liste<T> l, const T & x){
	typename Liste<T>::iterator it = l.begin();
	while ( (*it)<x && it!=l.end() ){
		++it;
	}
	return it;
}
template<class T>
Liste<T>  trieList (Liste<T> & l){
	Liste<T>* blop= new Liste <T>();

	typename Liste<T>::iterator itList =l.begin();
	++itList;
	blop->push_front(*(l.begin()));
	typename Liste<T>::iterator insert=l.begin();
	while(itList != l.end()){
		insert= rechercheVal(*(blop),(*itList));// on recherche ou l'on va l'inserer dans blop
			blop->insert(insert,(*itList));

		++itList;

	}
	return *blop;
}

