/*
 * filtre_compose.h
 *
 *  Created on: 28 mars 2017
 *      Author: kerou
 */

#ifndef INCLUDE_FILTRE_COMPOSE_H_
#define INCLUDE_FILTRE_COMPOSE_H_

#include<memory>
#include"composant.h"
#include"consommateur.h"
#include"filtre_base.h"
#include<vector>
#include<map>

class filtre_compose : public filtre_base{
private:

	//liste des composants contenus dans le filtre_compose
   std::vector<std::shared_ptr<composant>> m_compo;

   //numero d'entree externe -> [composant, numero d'entree du composant]
   std::vector<std::pair<std::shared_ptr<consommateur>, unsigned int > >m_asso;

   //faire la meme pour les sorties ? pas nécessaire car fait dans producteur

protected:
	/**
	 * ajout d'un composant dans le filtre
	 * @param compo : le compo a ajouter
	 */
	void ajouterCompo(std::shared_ptr<composant> compo);

	/**
	 * connecte this avec l'entree du compo
	 * @param	numEntree 		: numero d'entree externe du filtre_compose
	 * @param	compo			: le composant interne
	 * @parem	numEntreeCompo	: numero Entree du composant compo
	 */
	void connexionEntreeInterne(unsigned int numEntree, std::shared_ptr<consommateur> compo, unsigned int numEntreeCompo);

	/**
	 *  connecte this avec une sortie d'un compo
	 *  @param numSortie          : le numero de sortie externe du filtre_compose
	 *  @param numComposant       : le composant interne
	 *  @param numSortieComposant : le numero de la sortie du composant interne `numComposant`
	 */
	void connecterSortieInterne(unsigned int numSortie, std::shared_ptr<producteur_base> composant, unsigned int numSortieComposant);

	/**
	 *  connecte deux composant internes
	 *  @param composantSortie    : le numero du composant interne dont on veut connecter la sortie
	 *  @param numSortieComposant : le numero de sortie du composant `numComposantInterneEnSortie`
	 *  @param composantEntree    : le numero du composant interne dont on veut connecter l'entree
	 *  @param numEntreeComposant : le numero d'entree du composant `numComposantInterneEnEntree`
	 */
	void connecterDeuxComposants(std::shared_ptr<producteur>  composantSortie, unsigned int numSortieComposant,
			std::shared_ptr<consommateur> composantEntree, unsigned int numEntreeComposant);

public:
	filtre_compose(unsigned int nbEntrees, unsigned int nbSorties);
	virtual ~filtre_compose();

	virtual void connecterEntree(const std::shared_ptr<flot> &f, unsigned int numentree);
	virtual void calculer();

};

#endif /* INCLUDE_FILTRE_COMPOSE_H_ */
