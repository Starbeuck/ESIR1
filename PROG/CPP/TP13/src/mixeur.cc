/*
 * mixeur.cc
 *
 *  Created on: 28 mars 2017
 *      Author: Solenn
 */

#include"mixeur.h"
#include<memory>
#include "volume_compse.h"


mixeur::mixeur(unsigned int nbEntrees, double val[]):filtre_compose(nbEntrees,1){

	std::shared_ptr<filtre_base> add(new add_mutl(nbEntrees));

	for(unsigned int i = 0; i < nbEntrees; i++)
	{
		//	on prendre le ieme composant qu'on ajouter au filtre compose
		std::shared_ptr<filtre_base> vol(new volume_compose(val[i]));
		ajouterCompo(vol);

		// on connecte le composant avec le filtre compose
		connexionEntreeInterne(i, vol, 0);
		connecterDeuxComposants(vol, 0, add, i);
	}

	ajouterCompo(add);
	connecterSortieInterne(0,add,0);

}

mixeur::~mixeur(){}
