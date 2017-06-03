#include "vecteur.h"
#include <iostream>
#include <stdlib.h>

/** \brief Programme principal */
int main()
{
	//message de bienvenue
	std::cout<<"Bonjour, bienvenue dans l'assitant de creation de vos vecteurs !"<<std::endl;

	//creation des vecteurs
	std::cout<<"CREATION DU PREMIER VECTEUR"<<std::endl;
	Vecteur *temp1 = new Vecteur;
	std::cin>>*temp1;
	//= lireVecteur();
	//afficherVecteur(temp1);
	std::cout<<*temp1<<std::endl;

	std::cout<<"CREATION DU SECOND VECTEUR"<<std::endl;
	Vecteur * temp2 = new Vecteur;
	//= lireVecteur();
	//afficherVecteur(temp2);
	std::cin>>*temp2;
	std::cout<<*temp2<<std::endl;

	//menu
	int num;
	std::cout<<"Que voulez-vous faire ?"<<std::endl;
	std::cout<<"1) Modifier l'un des vecteurs "<<std::endl;
	std::cout<<"2) Faire une affectation"<<std::endl;
	std::cout<<"3) Faire une somme"<<std::endl;
	std::cout<<"4) Faire un produit scalaire"<<std::endl;
	std::cout<<"5) Quitter"<<std::endl;
	std::cin>>num;

	switch(num){
	case 1 :
		int vec;
		std::cout<<"Je vois, vous vous etes trompee. Quel(s) vecteur(s) voulez-vous modifier ?"<<std::endl;
		std::cin>>vec;

		if(vec==1){
			int indice;
			float val;

			std::cout<<"Veuillez me dire quelle coordonnee vous voulez modifier ?"<<std::endl;
			std::cin>>indice;

			while(indice<0 || indice>temp1->dimensions()){
				std::cout<<"La coordonnee demandee n'existe pas"<<std::endl;
				std::cout<<"Veuillez me dire quelle coordonnee vous voulez modifier ?"<<std::endl;
				std::cin>>indice;
			}

			std::cout<<"Quelle valeur voulez-vous entrer ?"<<std::endl;
			std::cin>>val;

			//temp1->set(indice, val);
			//afficherVecteur(temp1);
			(*temp1)[indice]=val;
			std::cout<<*temp1<<std::endl;
		}

		if(vec==2){
			int indice;
			float val;

			std::cout<<"Veuillez me dire quelle coordonnee vous voulez modifier ?"<<std::endl;
			std::cin>>indice;

			while(indice<0 || indice>temp2->dimensions()){
				std::cout<<"La coordonnee demandee n'existe pas"<<std::endl;
				std::cout<<"Veuillez me dire quelle coordonnee vous voulez modifier ?"<<std::endl;
				std::cin>>indice;
			}

			std::cout<<"Quelle valeur voulez-vous entrer ?"<<std::endl;
			std::cin>>val;

			temp1->set(indice, val);
			afficherVecteur(temp2);
		}
			break;

	case 2 :
		std::cout<<"Le premier vecteur va prendre la valeur du second !"<<std::endl;
		*temp1 = *temp2;
		std::cout<<"Le premier vecteur vaut désormais :"<<std::endl;
		afficherVecteur(temp1);
		break;

	case 3 :
		if(temp1->dimensions()==temp2->dimensions()){
			std::cout<<"Avec la méthode add : "<<std::endl;
			Vecteur somme(add(temp1,temp2));
			afficherVecteur(&somme);

			std::cout<<"Avec l'opérateur + :"<<std::endl;
			Vecteur sommeBis(*temp1 + *temp2);
			afficherVecteur(&sommeBis);

			std::string conf5;
			std::cout<<"Voulez-vous faire une somme de 3 vecteurs ? (y/n)"<<std::endl;
			std::cin>>conf5;

			if (conf5=="y"){
				std::cout<<"Et une somme de trois vecteurs !"<<std::endl;

				std::cout<<"CREATION DU TROISIEME VECTEUR"<<std::endl;
				Vecteur * temp = lireVecteur();
				afficherVecteur(temp);

				Vecteur s((*temp+*temp1)+*temp2);
				afficherVecteur(&(s));

				delete temp;
			}
		}else{
			std::cout<<"Vos vecteurs ne sont pas de la meme taille, ce n'est pas possible"<<std::endl;
		}
		break;

	case 4 :{
		std::cout<<"Nous allons faire le produit scalaire de vos deux vecteurs"<<std::endl;
		float r = (*temp1)*(*temp2);
		std::cout<<r<<std::endl;
	}
	break;

	case 5 :
		break;

	}
	delete temp2;
	delete temp1;
	return 0 ;

}
