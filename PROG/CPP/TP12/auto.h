#ifndef _AUTO_H
#define _AUTO_H
#include <iostream>
#include "vehicule.h"

class Auto:public Vehicule{

private :
	bool m_type;
	int m_prixDeBase;
public :
	//constructeur
	Auto(unsigned int nbp, bool ttt);

	//destructeur
	~Auto();

	//consultation du type
	bool getType() const ;

	//! déterminer le tarif du véhicule
	virtual double calculerTarif() const;

	//! afficher les caractéristiques du véhicule
	virtual void afficher(std::ostream & s = std::cout) const;
	// methode polymorphe de clonage
	virtual void ajouter() const ;
	// constructeur de copie
	Auto(const Auto & a);
};

#endif // _AUTO_H
