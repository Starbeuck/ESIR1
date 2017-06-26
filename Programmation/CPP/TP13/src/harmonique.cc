/*
 * harmonique.cc
 *
 *  Created on: 27 mars 2017
 *      Author: Solenn
 */

#include"harmonique.h"


harmonique::harmonique(double freq, double phi):producteur_base(1),m_echantillon(0),m_freq(freq),m_phi(phi){}

harmonique::~harmonique(){};

void harmonique::calculer(){
	m_lesSorties[0]->inserer(sin((m_echantillon/(double)MixageSonore::frequency)*2*MixageSonore::pi*m_freq+m_phi));
	m_echantillon++;
}
