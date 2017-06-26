/*
 * multiplicateur.h
 *
 *  Created on: 27 mars 2017
 *      Author: Solenn
 */

#ifndef INCLUDE_MULTIPLICATEUR_H_
#define INCLUDE_MULTIPLICATEUR_H_

#include"filtre_base.h"

class Multiplicateur : public filtre_base{
private:
public:
	Multiplicateur();
	virtual ~Multiplicateur();
	virtual void calculer();
};



#endif /* INCLUDE_MULTIPLICATEUR_H_ */
