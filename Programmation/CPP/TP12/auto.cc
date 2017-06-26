#include "auto.h"
#include <iostream>
//------------------------------------------------------------------------
// classe Auto
//------------------------------------------------------------------------

// constructeur
Auto::Auto(unsigned int nbp, bool ttt): Vehicule(2,nbp), m_type(ttt)
{
	if(ttt==true) m_prixDeBase = 350;
	else m_prixDeBase = 100;
}

// constructeur de copie
Auto::Auto(const Auto & a){

	Auto *temp = new Auto(a.getType(),m_type);
	if(m_type==true) m_prixDeBase = 350;
	else m_prixDeBase = 100;

}

Auto::~Auto(){}

//consultation du type
// 0 si tout terrain
bool Auto::getType() const{
	return m_type;
}

// calculer le tarif
double Auto::calculerTarif() const{
	return m_prixDeBase+(getPassagers()*tarifPassager);
}


//afficher les caractéristiques de la voiture
void Auto::afficher(std::ostream & s) const{
	s<<m_type<<"= longueur : "<<getLongueur()<<", nombre passagers : "<<getPassagers()<<", total TTC : "<<calculerTarif()<<" ";
}


// methode polymorphe de clonage
void Auto::ajouter() const {
	Auto(*this);
}

