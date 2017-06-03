#ifndef _BUS_H
#define _BUS_H

#include "vehicule.h"

class Bus:public Vehicule{

private :
	static const int m_prixDeBase=200;
public :
	//constructeur
	Bus(unsigned int lg, unsigned int nbp);

	//destructeur
	~Bus();

	//! déterminer le tarif du véhicule
	virtual double calculerTarif() const;

	//! afficher les caractéristiques du véhicule
	virtual void afficher(std::ostream & s = std::cout) const;
	// methode polymorphe de clonage
	virtual void ajouter() const ;
	// constructeur de copie
	Bus(const Bus & a);
};
#endif // _BUS_H
