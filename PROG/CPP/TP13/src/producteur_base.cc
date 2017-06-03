/*
 * producteur_base.cc
 *
 *  Created on: 23 mars 2017
 *      Author: 15001727
 */


#include "producteur_base.h"
#include <cassert>

//constructeur qui prépare un producteur de nb flot
producteur_base::producteur_base(int nbSorties):m_nbSorties(nbSorties),m_lesSorties(nbSorties,std::shared_ptr<flot>(new imp_flot())){}

//destructeur
producteur_base::~producteur_base(){}

//connecte les sorties
void producteur_base::connecterSortie(const std::shared_ptr<flot> & f, unsigned int numsortie){
	assert(numsortie<m_nbSorties);
	m_lesSorties[numsortie]=f;
}

unsigned int producteur_base::nbSorties() const{
	return m_nbSorties;
}

const std::shared_ptr<flot> & producteur_base::getSortie(unsigned int numsortie) const {
	assert(numsortie<m_nbSorties);
	return m_lesSorties[numsortie];
}
