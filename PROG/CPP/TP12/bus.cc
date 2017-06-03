#include "bus.h"

//------------------------------------------------------------------------
// classe bus
//------------------------------------------------------------------------

// constructeur
Bus::Bus(unsigned int lg, unsigned int nbp):Vehicule(lg, nbp){}

//constructeur de copie
Bus::Bus(const Bus & a){
	Bus *p=new Bus(a.getLongueur(),a.getPassagers());
}

Bus::~Bus(){}
//calcul du tarif
double Bus::calculerTarif() const{
	return m_prixDeBase+(getPassagers()*tarifPassager)+(50*getLongueur());
}

//! afficher les caractéristiques du véhicule
void Bus::afficher(std::ostream & s) const{
	s<<"Bus = longueur : "<<getLongueur()<<", nombre passagers : "<<getPassagers()<<", total TTC : "<<calculerTarif();
}


void Bus::ajouter() const{
	Bus(*this);
}
