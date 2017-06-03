/*
 * filtre_base.cc
 *
 *  Created on: 27 mars 2017
 *      Author: Solenn
 */

#include"filtre_base.h"

filtre_base::filtre_base(unsigned int nbEntreesF, unsigned int nbSortiesF):producteur_base(nbSortiesF), consommateur_base(nbEntreesF){}

filtre_base::~filtre_base() {}
