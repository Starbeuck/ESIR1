/*
 * volume_compse.h
 *
 *  Created on: 28 mars 2017
 *      Author: Solenn
 */

#ifndef INCLUDE_VOLUME_COMPSE_H_
#define INCLUDE_VOLUME_COMPSE_H_

#include"filtre_compose.h"

class volume_compose : public filtre_compose{
private:
public:
	volume_compose(double mult);
	~volume_compose();
};




#endif /* INCLUDE_VOLUME_COMPSE_H_ */
