/*
 * lecteur_fichier.cc
 *
 *  Created on: 28 mars 2017
 *      Author: Solenn
 */

#include "lecteur_fichier.h"
#include "composant_exception.h"
#include <climits>

lecteur_fichier::lecteur_fichier(std::string filename, unsigned int nbCanaux):producteur_base(nbCanaux),
m_file(filename.c_str(), std::ifstream::in | std::ifstream::binary),
m_nbCanaux(nbCanaux)
{
	if(!m_file.good()){
		throw composant_exception("Cannot open file");
	}
}

lecteur_fichier::~lecteur_fichier(){
	m_file.close();
}

void lecteur_fichier::calculer(){
	if(m_file.good() && !m_file.eof()){
		for(unsigned int i = 0; i < this->m_nbCanaux; i++)
		{
			short int tmp = 0 ;
			//on lit les echantillons du fichier

			m_file.read((char*)&tmp, sizeof(short int));
			double res = double(tmp)/(double)SHRT_MAX;

			std::cout<<res<<std::endl;
			m_lesSorties[i]->inserer(res);

		}
	}

	else if(m_file.eof()) {
		throw composant_exception("End of the file");
	}
	else
	{
		throw composant_exception("Cannot read file");
	}

}
