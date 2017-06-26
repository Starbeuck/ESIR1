/*
 * multiplicateur.cc
 *
 *  Created on: 27 mars 2017
 *      Author: Solenn
 */

#include"multiplicateur.h"

Multiplicateur::Multiplicateur():filtre_base(2,1){}

Multiplicateur::~Multiplicateur(){}

void Multiplicateur::calculer(){
	if(yaDesEchantillons()){
		getSortie(0)->inserer((getEntree(0)->extraire())*(getEntree(1)->extraire()));
	}
}


