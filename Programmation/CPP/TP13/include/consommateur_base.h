/*
 * consommateur_base.h
 *
 *  Created on: 27 mars 2017
 *      Author: Solenn
 */

#ifndef INCLUDE_CONSOMMATEUR_BASE_H_
#define INCLUDE_CONSOMMATEUR_BASE_H_

#include"consommateur.h"
#include "vector"
#include "imp_flot.h"
#include <memory>

class consommateur_base : virtual consommateur{
private:
	unsigned int m_nbEntrees;
	std::vector<std::shared_ptr<flot>> m_lesEntrees;

public:
	consommateur_base(unsigned int nbEntrees);
	~consommateur_base();

	//héritée de consommateur
	virtual unsigned int nbEntrees() const;
	virtual const std::shared_ptr<flot> & getEntree(unsigned int numentree) const;
	virtual void connecterEntree(const std::shared_ptr<flot> & f, unsigned int numentree);
	virtual bool yaDesEchantillons() const;
};


#endif /* INCLUDE_CONSOMMATEUR_BASE_H_ */
