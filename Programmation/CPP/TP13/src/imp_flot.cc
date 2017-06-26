/*
 * imp_flot.cc
 *
 *  Created on: 22 mars 2017
 *      Author: 15001727
 */

#include "cassert"
#include "imp_flot.h"


//constructeur
//imp_flot::imp_flot(){}

//implementation du flot.h
//destructeur

//insertion d'un echantillon dans le flot
void imp_flot::inserer(double echantillon){
	bebe.push_back(echantillon);
}

// extrait l'echantillon en tête du flot
double imp_flot::extraire(){
	assert(!vide());
	double tmp = *bebe.begin();
	bebe.pop_front();
	return tmp;
}

//permet de savoir si le flot est vide ou non
bool imp_flot::vide() const{
	return bebe.size()==0;
}
