/*
 * volume_compose.cc
 *
 *  Created on: 28 mars 2017
 *      Author: Solenn
 */

#include"volume_compse.h"
#include<memory>
#include "signal_constant.h"
#include "multiplicateur.h"

volume_compose::volume_compose(double mult):filtre_compose(1,1){

	//cr�ation de l'�quivalent des attributs du volume
	std::shared_ptr<signal_constant> si(new signal_constant(mult));
	std::shared_ptr<Multiplicateur> mul(new Multiplicateur());

	//ajout � la liste des composants du filtre compose
	ajouterCompo(si);
	ajouterCompo(mul);

	//on connecte les composants entre eux
	connecterDeuxComposants(si,0,mul,0);

	//on connecte la sortie du multiplicateur � la sortie du filtre
	connecterSortieInterne(0,mul,0);

	//on connecte l'entr�e du mul � l'entr�e du filtre
	connexionEntreeInterne(0,mul,1);
}

volume_compose::~volume_compose(){}

