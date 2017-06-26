/*
 * filtre_base.h
 *
 *  Created on: 27 mars 2017
 *      Author: Solenn
 */

#ifndef INCLUDE_FILTRE_BASE_H_
#define INCLUDE_FILTRE_BASE_H_

#include"filtre.h"
#include"producteur_base.h"
#include"consommateur_base.h"

class filtre_base : public filtre, public producteur_base, public consommateur_base{
private:
public:
	filtre_base(unsigned int nbEntreesF, unsigned int nbSortiesF);
	virtual ~filtre_base();
};



#endif /* INCLUDE_FILTRE_BASE_H_ */
