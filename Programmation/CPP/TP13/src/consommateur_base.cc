/*
 * consommateur_base.cc
 *
 *  Created on: 27 mars 2017
 *      Author: Solenn
 */

#include"consommateur_base.h"
#include <cassert>

//constructeur
consommateur_base::consommateur_base(unsigned int nbEntrees):m_nbEntrees(nbEntrees),m_lesEntrees(nbEntrees,std::shared_ptr<flot>(new imp_flot())){}

//destructeur
consommateur_base::~consommateur_base(){}

unsigned int consommateur_base::nbEntrees() const{
	return m_nbEntrees;
}

const std::shared_ptr<flot> & consommateur_base::getEntree(unsigned int numentree) const{
	assert(numentree<m_nbEntrees);
	return m_lesEntrees[numentree];
}

void consommateur_base::connecterEntree(const std::shared_ptr<flot> & f, unsigned int numentree){
	assert(numentree<m_nbEntrees);
	m_lesEntrees[numentree]=f;
}

bool consommateur_base::yaDesEchantillons() const{
	bool tmp = true;
	for(unsigned int i = 0; i<m_lesEntrees.size();i++){
		if(m_lesEntrees[i]->vide()){
			tmp = false;
		}
	}

	return tmp;
}
