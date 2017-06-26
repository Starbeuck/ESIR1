/*
 * main.cpp
 *
 *  Created on: 8 mars 2013
 *      Author: engel
 */

#include "constantes.h"
#include "enregistreur_fichier.h"
#include "enregistreur_fichier_texte.h"

#include "harmonique.h"
#include "signal_constant.h"
#include "multiplicateur.h"
#include "operation_binaire.h"
#include "volume.h"
#include "volume_compse.h"
#include "mixeur.h"
#include "lecteur_fichier.h"
#include "composant_exception.h"

void
q2_signal_constant()
{
	signal_constant constant(0.5);
	enregistreur_fichier_texte enregistreur("02_constant.txt", 1);
	enregistreur.connecterEntree(constant.getSortie(0), 0);
	// générer des valeurs
	for (unsigned int i = 0; i < 50; ++i) {
		constant.calculer();
		enregistreur.calculer();
	}
}

void
q4_harmonique()
{
	harmonique la440(440); // la 440Hz (voir fr.wikipedia.org/wiki/Note_de_musique)
	enregistreur_fichier enregistreur("04_harmonique.raw", 1);	// fichier mono
	enregistreur.connecterEntree(la440.getSortie(0), 0);
	// produire 2 secondes de son
	for (unsigned long int i = 0; i < 2 * MixageSonore::frequency; ++i) {
		la440.calculer();
		enregistreur.calculer();
	}
}

void
q8_multiplicateur(){
	harmonique h440(440);
	harmonique h880(880);
	Multiplicateur test;
	test.connecterEntree(h440.getSortie(0),0);
	test.connecterEntree(h880.getSortie(0),1);

	enregistreur_fichier q8 ("08_mult.raw", 1);
	q8.connecterEntree(test.getSortie(0), 0);

	for(unsigned long int i = 0; i < 2 * MixageSonore::frequency; ++i){
		h440.calculer();
		h880.calculer();
		test.calculer();
		q8.calculer();
	}
}

void
q10_opBin()	{
	harmonique h440(440);
	harmonique h880(880);
	operation_binaire<std::multiplies<double>> test;

	test.connecterEntree(h440.getSortie(0),0);
	test.connecterEntree(h880.getSortie(0),1);

	enregistreur_fichier q10 ("11_opBin.raw", 1);
	q10.connecterEntree(test.getSortie(0), 0);

	for(unsigned long int i = 0; i < 2 * MixageSonore::frequency; ++i){
		h440.calculer();
		h880.calculer();
		test.calculer();
		q10.calculer();
	}
}

void
q12_Volume()
{
	harmonique h440(440);
	volume test(0.5);

	test.connecterEntree(h440.getSortie(0), 0);

	enregistreur_fichier enr("12_volume.raw", 1);
	enr.connecterEntree(test.getSortie(0), 0);

	for (unsigned long int i = 0; i < 2 * MixageSonore::frequency; ++i) {
		h440.calculer();
		test.calculer();
		enr.calculer();
	}
}

void
q14_volume_compose(){
	harmonique h440(440);
	volume_compose test (0.5);

	test.connecterEntree(h440.getSortie(0),0);

	enregistreur_fichier enr("14_volume_comp.raw", 1);
	enr.connecterEntree(test.getSortie(0), 0);

	for (unsigned long int i = 0; i < 2 * MixageSonore::frequency; ++i) {
		h440.calculer();
		test.calculer();
		enr.calculer();
	}
}

void
q15_mixeur(){
	harmonique h440(440);
	harmonique h880(880);

	double tab[]={0.25,4};

	mixeur robot(2,tab);
	robot.connecterEntree(h440.getSortie(0),0);
	robot.connecterEntree(h880.getSortie(0),1);

	enregistreur_fichier enr("15_mixeur.raw", 1);
	enr.connecterEntree(robot.getSortie(0), 0);

	for (unsigned long int i = 0; i < 2 * MixageSonore::frequency; ++i) {
		h440.calculer();
		h880.calculer();
		robot.calculer();
		enr.calculer();
	}
}

void
q16_lecteur_fichier(){
	lecteur_fichier mono("raw/mono.raw", 1);
	lecteur_fichier stereo("raw/stereo.raw", 2);

	double tab[] =  {0.3, 0.3, 0.3};

	mixeur robot(2, tab);
	robot.connecterEntree(mono.getSortie(0), 0);
	robot.connecterEntree(stereo.getSortie(0), 1);
	//robot.connecterEntree(stereo.getSortie(1), 2);

	enregistreur_fichier enr("16_lecteur_fichier.raw", 1);

	enr.connecterEntree(robot.getSortie(0), 0);

	try
	{
		while(true)
		{
			mono.calculer();
			stereo.calculer();
			robot.calculer();
			enr.calculer();
		}
	}
	catch(composant_exception & e){
		std::cout<<e.what()<<std::endl;
	}
}


int
main()
{
	q2_signal_constant();
	q4_harmonique();
	q8_multiplicateur();
	q10_opBin();
	q12_Volume();
	q14_volume_compose();
	q15_mixeur();
	q16_lecteur_fichier();
	return 0;
}
