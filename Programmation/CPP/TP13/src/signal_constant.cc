/*
 * signal_constant.cc
 *
 *  Created on: 22 mars 2017
 *      Author: 15001727
 */

#include "signal_constant.h"

//initialise un signal constant avec une valeur (et une sortie)
signal_constant::signal_constant(double val):m_val(val){
	m_val_const = std::shared_ptr<flot>(new imp_flot());
}


//nombre de sortie
unsigned int signal_constant::nbSorties() const {
	return 1;
}

//valeur de la sortie
const std::shared_ptr<flot> & signal_constant::getSortie(unsigned int numsortie) const{
	return m_val_const;
}

void signal_constant::calculer(){
	getSortie(nbSorties())->inserer(m_val);
}
