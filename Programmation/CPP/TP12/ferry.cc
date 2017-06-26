//------------------------------------------------------------------------
// classe Ferry
//------------------------------------------------------------------------
#include "ferry.h"
#include <iterator>
// constructeur
Ferry::Ferry (unsigned int longueur, unsigned int personnes)
:m_cap(personnes), m_lgr(longueur)
{
	ferry = * new conteneur();
}

Ferry::~Ferry(){}

/** ajouter un véhicule dans le ferry.
  sans effet s'il n'y a plus de place
  @param pv : désigne le véhicule à ajouter
  @return vrai si l'ajout a eu lieu, faux sinon
 */
bool Ferry::ajouter(const Vehicule * pv){
	if (m_lgr<pv->getLongueur() || m_cap<pv->getPassagers()){
		return false;
	}else {
		ferry.push_front(pv);
		m_cap-=pv->getPassagers();
		m_lgr-=pv->getLongueur();
		return true;
	}
}

/**calculer le tarif total du ferry
 * @return le prix total
 */
double Ferry::calculerTarif(void)const{
	double a;
	conteneur::const_iterator it=this->ferry.begin();
	while (it!= this->ferry.end()){
		a+=(*it)->calculerTarif();
	}
	return a;
}

/**
 * afficher les informations sur le contenu du ferry
 * @return le contenu d'un ferry
 */
void Ferry::afficher(std::ostream & s) const{
	conteneur ::const_iterator it=this->ferry.begin();
	while (it!= this->ferry.end()){
		s<<*it;
		++it;
	}
}

/**
 * surchage de l'opérateur d'affichage
 */
std::ostream & operator << (std::ostream & sortie, const Ferry & ferry){
	ferry.afficher();
	return sortie;
}

