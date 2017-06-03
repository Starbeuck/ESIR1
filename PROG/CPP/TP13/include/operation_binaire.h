/*
 * operation_binaire.h
 *
 *  Created on: 28 mars 2017
 *      Author: kerou
 */

#ifndef INCLUDE_OPERATION_BINAIRE_H_
#define INCLUDE_OPERATION_BINAIRE_H_

#include"filtre_base.h"

template <class op>
class operation_binaire : public filtre_base{
private:
	op m_op;
public:
	operation_binaire();
	~operation_binaire();

	//operation héritée
	virtual void calculer();
};

//GENERICITE

//filtre op avec deux entrees et une sortie
template <class op>
operation_binaire<op>::operation_binaire () :
  filtre_base(2,1),
  m_op(op())
{}

template <class op>
operation_binaire<op>::~operation_binaire ()
{}

template <class op>
void operation_binaire<op>::calculer()
{
  if(yaDesEchantillons())
    this->getSortie(0)->inserer(
      m_op(this->getEntree(0)->extraire(), this->getEntree(1)->extraire())
    );
}

#endif /* INCLUDE_OPERATION_BINAIRE_H_ */
