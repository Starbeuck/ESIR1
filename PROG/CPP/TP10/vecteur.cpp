/** \brief Ce fichier doit contenir l'ensemble des implémentations
relatives à la classe vecteur et aux fonctionnalités la concernant */

#include <cassert>
#include <iostream>
#include "vecteur.h"

/**
 * constructeur avec 2 param�tres
 * @params dim, valInit
 * @return cr�er un tableau de dimensions dim remplit de valInit
 */
Vecteur::Vecteur(int dim, float valInit) :
m_dim(dim),	m_vec(new float[dim])
{assert(dim>0);
	for(int i=0;i<m_dim;i++){
		m_vec[i]=valInit;
	}

	//std::cout<<"Vous avez cr�� un vecteur de dimension"<<m_dim<<"remplit de"<<valInit<<std::endl;
}

/**
 * constructeur de copie
 */
Vecteur::Vecteur(const Vecteur & v){
	//std::cout<<"Constructeur de copie"<<std::endl;

	m_dim = v.dimensions();
	m_vec = new float[m_dim];

	for(int i=0;i<m_dim;i++){
		this->set(i,v.get(i));
	}
}

/**
 * destructeur
 */
Vecteur::~Vecteur(){
	delete[] m_vec;
	//std::cout<<"Appel destructeur"<<std::endl;
}

/**
 * surcharge de l'opérateur =
 */
Vecteur & Vecteur::operator=(const Vecteur & v){
	if(this != &v){
		delete[] m_vec;

		m_vec = new float[v.dimensions()];
		m_dim = v.dimensions();

		for(int i=0;i<m_dim;i++){
			this->set(i,v.get(i));
		}
	}
	return *this;
}

/**
 * surcharge de  l'opérateur +
 */
Vecteur Vecteur::operator+(const Vecteur & v) const{
	assert(this->dimensions()==v.dimensions());

	Vecteur somme(v.dimensions());

	for(int i =0; i<v.dimensions();i++){
		somme.set(i,this->get(i)+v.get(i));
	}

	return somme;
}

/**
 * permet de consulter la valeur d'une coordonn�e
 * @params indice
 * @return retourne la valeur qui se trouve � l'indice indice du vecteur
 */
float Vecteur::get(int indice)const{
	assert(indice>=0 && indice<m_dim && "caca");
	return m_vec[indice];
}

/**
 * permet de set une valeur du vecteur
 * @params indice, valeur
 * @return l'indice set � la valeur
 */
void Vecteur::set(int indice, float valeur){
	assert("Test" && indice>=0 && indice<m_dim);
	m_vec[indice]=valeur;
}

/**
 * permet de connaitre la dimension du vecteur
 * @return m_dim
 */
int Vecteur::dimensions()const {
	return m_dim;
}


float & Vecteur::operator[](int indice){
	assert(indice>=0 && indice<m_dim);
	return m_vec[indice];
}

float Vecteur::operator[](int indice) const{
	assert(indice>=0 && indice<m_dim);
	return m_vec[indice];
}


/**
 * permet d'afficher un vecteur
 * @param vecteur
 */
void afficherVecteur(const Vecteur * v, std::ostream & out){
	//out<<"Le vecteur est le suivant :"<<std::endl;

	for(int i=0; i<v->dimensions(); i++){
		//out<<"Case "<<i<<" : "<<v->get(i)<<std::endl;
		out<<v->get(i)<<" ";
	}
}

/**
 * permet de lire un vecteur
 * @params don't care
 * @return permet de construire le vecteur que l'user nous donne
 */
Vecteur * lireVecteur(std::istream & in){
	int dim;
	float def;

	std::cout<<"Veuillez entrer une dimension : ";
	in>>dim;

	Vecteur * blop = new Vecteur(dim);

	for(int i=0; i<dim;i++){
		std::cout<<"Veuillez entrer une valeur par d�faut : ";
		in>>def;

		blop->set(i,def);
	}
	return blop;

}

/**
 * permet l'addition de deux vecteurs
 * @params pointeur de deux vecteurs dont il faut faire la seomme
 * @return renvoit la somme des deux paramètres
 */
Vecteur add(const Vecteur * v1, const Vecteur * v2) {
	assert(v1->dimensions()==v2->dimensions());

	Vecteur somme(v1->dimensions());
	for(int i=0; i<v1->dimensions();i++){
		somme.set(i,v1->get(i)+v2->get(i));
	}
	return somme;
}

/**
 * surcharge operateur * pour les produire scalaire
 */
float operator*(const Vecteur & v1, const Vecteur & v2) {
	assert(v1.dimensions()==v2.dimensions());

	float result(0);

	for (int i =0; i<v1.dimensions();i++){
		result+=v1.get(i)*v2.get(i);
	}

	return result;
}

std::ostream & operator<<(std::ostream & out, const Vecteur & v){
	out<<"[";

	for(int i=0; i<v.dimensions()-1; i++){
		out<<v.get(i)<<" ; ";
	}

	out<<v.get(v.dimensions()-1);
	out<<"]";

	return out;
}

std::istream & operator>>(std::istream & in, Vecteur & v){

	int dim;
	std::cout<<"Donnez moi la dimension du vecteur que vous voulez creer :"<<std::endl;
	in>>dim;

	Vecteur vec(dim);
	std::cout<<"Veuillez m'indiquer ses coordonnees"<<std::endl;

	for(int i =0; i<vec.dimensions();i++){
		float temp;
		in>>temp;
		vec.set(i, temp);
	}

	v=vec;

	return in;
}
