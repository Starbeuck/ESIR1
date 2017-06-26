/*
 * mixeur.h
 *
 *  Created on: 28 mars 2017
 *      Author: Solenn
 */

#ifndef INCLUDE_MIXEUR_H_
#define INCLUDE_MIXEUR_H_

#include"filtre_compose.h"
#include<cassert>
#include"operation_binaire.h"

class mixeur : public filtre_compose{
private:
public:
	mixeur(unsigned int nbEntrees, double val[]);
	virtual ~mixeur();

	class add_mutl : public filtre_compose{
	private:
	public:
		add_mutl(unsigned int nbEntrees):filtre_compose(nbEntrees,1){
			assert(nbEntrees>=2);

			if(nbEntrees==2){
				std::shared_ptr<filtre_base> add(new operation_binaire<std::plus<double>>());

				//on ajoute le composant au filtre compose
				ajouterCompo(add);

				// on connecte les entres des composants à celles du filtre compose
				connexionEntreeInterne(0,add,0);
				connexionEntreeInterne(1,add,1);

				connecterSortieInterne(0,add,0);
				}
				else{
					std::shared_ptr<filtre_base> add(new operation_binaire<std::plus<double>>());
					std::shared_ptr<filtre_base> admulti(new add_mutl(nbEntrees -1));

					ajouterCompo(add);
					ajouterCompo(admulti);

					connexionEntreeInterne(0,add,0);
					connecterDeuxComposants(admulti,0, add, 1);

					connecterSortieInterne(0,add,0);

					 for(unsigned int i = 1; i < nbEntrees; i++)
						 connexionEntreeInterne(i, admulti, i-1);
					  }
				}

		~add_mutl(){}
	};

};



#endif /* INCLUDE_MIXEUR_H_ */
