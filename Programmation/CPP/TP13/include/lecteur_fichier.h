/*
 * lecteur_fichier.h
 *
 *  Created on: 28 mars 2017
 *      Author: Solenn
 */

#ifndef INCLUDE_LECTEUR_FICHIER_H_
#define INCLUDE_LECTEUR_FICHIER_H_

#include <iostream>
#include <fstream>
#include "producteur_base.h"

class lecteur_fichier: public producteur_base{
public:
	lecteur_fichier(std::string filename, unsigned int nbCanaux = 1);
   virtual ~lecteur_fichier ();

   virtual void calculer();

 protected:
   std::ifstream m_file;
   unsigned int m_nbCanaux;
};


#endif /* INCLUDE_LECTEUR_FICHIER_H_ */
