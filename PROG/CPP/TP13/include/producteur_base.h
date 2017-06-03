/*
 * producteur_base.h
 *
 *  Created on: 23 mars 2017
 *      Author: 15001727
 */

#ifndef INCLUDE_PRODUCTEUR_BASE_H_
#define INCLUDE_PRODUCTEUR_BASE_H_

#include <iostream>
#include <memory>
#include "producteur.h"
#include "vector"
#include "imp_flot.h"

class producteur_base : virtual public producteur{
protected:
	// nombre de composant du vecteur
	unsigned int m_nbSorties;
	// vecteur contenant des shared_ptr sur des flots
	std::vector<std::shared_ptr<flot>> m_lesSorties;

public:

	 //constructeur & destructeur
	 producteur_base(int nbSorties);
	 ~producteur_base();

	//méthode de l'interface producteur
	unsigned int nbSorties() const;
	const std::shared_ptr<flot> & getSortie(unsigned int numsortie) const ;

	// permet de conencter une sortie sur ce composant
	void connecterSortie(const std::shared_ptr<flot> & f, unsigned int numsortie);

};


#endif /* INCLUDE_PRODUCTEUR_BASE_H_ */
