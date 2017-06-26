/*
 * volume.h
 *
 *  Created on: 28 mars 2017
 *      Author: kerou
 */

#ifndef INCLUDE_VOLUME_H_
#define INCLUDE_VOLUME_H_

#include"signal_constant.h"
#include"multiplicateur.h"
#include"filtre_base.h"
#include<memory>

class volume : public filtre_base{
private:
	signal_constant m_si;
	Multiplicateur m_mult;
public:
	volume(double volmult);
	virtual ~volume();

	virtual void connecterEntree(const std::shared_ptr<flot> & f, unsigned int numentree);
	virtual void calculer();
};




#endif /* INCLUDE_VOLUME_H_ */
