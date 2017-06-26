/*
 * harmonique.h
 *
 *  Created on: 27 mars 2017
 *      Author: Solenn
 */

#ifndef INCLUDE_HARMONIQUE_H_
#define INCLUDE_HARMONIQUE_H_


#include"constantes.h"
#include"producteur_base.h"
#include <cmath>

class harmonique : public producteur_base{
private:
	int m_echantillon;
	double m_freq;
	double m_phi;

public:
	//constructeur et destructeur
	harmonique(double freq, double phi = 0);
	virtual ~harmonique ();

	//fonction héritée
	virtual void calculer();

};



#endif /* INCLUDE_HARMONIQUE_H_ */
