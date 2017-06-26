/*
 * imp_flot.h
 *
 *  Created on: 22 mars 2017
 *      Author: 15001727
 */

#ifndef INCLUDE_IMP_FLOT_H_
#define INCLUDE_IMP_FLOT_H_

#include "flot.h"
#include "deque"
#include <memory>

class imp_flot : public flot {
private:
	std::deque<double> bebe;

public:
	//constructeur
	//imp_flot();

	//destructeur
	//~imp_flot();

	//methode de flot
	virtual void inserer(double echantillon);
	virtual double extraire();
	virtual bool vide() const;
};

#endif /* INCLUDE_IMP_FLOT_H_ */
