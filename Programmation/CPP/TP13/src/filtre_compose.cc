/*
 * filtre_compose.cc
 *
 *  Created on: 28 mars 2017
 *      Author: 15001727
 */

#include"filtre_compose.h"

filtre_compose::filtre_compose(unsigned int nbEntrees, unsigned int nbSorties):
filtre_base(nbEntrees, nbSorties),m_compo(),m_asso(nbEntrees){}

filtre_compose::~filtre_compose(){}

void filtre_compose::ajouterCompo(std::shared_ptr<composant> compo){
	m_compo.push_back(compo);
}

void filtre_compose::connecterEntree(const std::shared_ptr<flot> &f, unsigned int numentree){

	if(m_asso.size()>0){
		m_asso[numentree].first->connecterEntree(f,m_asso[numentree].second);
	}
	consommateur_base::connecterEntree(f,numentree);
}

void filtre_compose::connexionEntreeInterne(unsigned int numEntree, std::shared_ptr<consommateur> compo,
		unsigned int numEntreeCompo){

	m_asso[numEntree] = std::pair<std::shared_ptr<consommateur>, unsigned int>(compo,numEntreeCompo);
}

void filtre_compose::connecterSortieInterne(unsigned int numSortie,
		std::shared_ptr<producteur_base> composant, unsigned int numSortieComposant){

	m_lesSorties[numSortie] = composant->getSortie(numSortieComposant);
}

void filtre_compose::connecterDeuxComposants(std::shared_ptr<producteur>  composantSortie, unsigned int numSortieComposant,
		std::shared_ptr<consommateur> composantEntree, unsigned int numEntreeComposant){
	composantEntree->connecterEntree(composantSortie->getSortie(numSortieComposant),numEntreeComposant);
}

void filtre_compose::calculer(){

	if(yaDesEchantillons()){
		for(std::vector<std::shared_ptr<composant>>::iterator it = m_compo.begin(); it != m_compo.end() ; it++){
			(*it)->calculer();
		}

	}
}
