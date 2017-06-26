/*
 * signal_constant.h
 *
 *  Created on: 22 mars 2017
 *      Author: 15001727
 */

#ifndef INCLUDE_SIGNAL_CONSTANT_H_
#define INCLUDE_SIGNAL_CONSTANT_H_

#include <iostream>
#include <memory>
#include "producteur.h"
#include "flot.h"
#include "imp_flot.h"

class signal_constant : public producteur{
private :

	//valeur de la sortie
	std::shared_ptr<flot> m_val_const;
	double m_val;

public:
	 //constructeur
	  signal_constant(double val);

	  //méthode de l'interface producteur
	   unsigned int nbSorties() const;
	   const std::shared_ptr<flot> & getSortie(unsigned int numsortie) const ;
	   virtual void calculer();
};



#endif /* INCLUDE_SIGNAL_CONSTANT_H_ */
