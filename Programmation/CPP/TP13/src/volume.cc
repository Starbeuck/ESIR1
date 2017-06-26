/*
 * volume.cc
 *
 *  Created on: 28 mars 2017
 *      Author: kerou
 */

#include"volume.h"

volume::volume(double volmult):filtre_base(1,1),m_si(volmult),m_mult(){
	m_mult.connecterEntree(m_si.getSortie(0),0);
	m_mult.connecterSortie(getSortie(0),0);
}

volume::~volume(){}

void volume::connecterEntree(const std::shared_ptr<flot> & f, unsigned int numentree){
	//connexion multiplicateur
	m_mult.connecterEntree(f,1);

	//connexion à notre entrée
	consommateur_base::connecterEntree(f,numentree);
}

void volume::calculer(){
	if(yaDesEchantillons()){
		m_si.calculer();
		m_mult.calculer();
	}
}
