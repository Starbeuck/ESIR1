#include <iostream>
#include "vehicule.h"

//------------------------------------------------------------------------
// classe Vehicule
//------------------------------------------------------------------------

// constructeur
Vehicule::Vehicule(unsigned int lg, unsigned int nbp)
		:m_longueur(lg), m_passagers(nbp){}

// destructeur
Vehicule::~Vehicule(){}

// longueur d'un véhicule
unsigned int Vehicule::getLongueur() const{
	return m_longueur;
}
// nombre de personnes dans le véhicule
unsigned int Vehicule::getPassagers() const{
	return m_passagers;
}

// opérateur d'affichage
std::ostream & operator << (std::ostream & sortie, const Vehicule & v){
	v.afficher();
	return sortie;
}


