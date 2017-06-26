/** \brief Ce fichier doit contenir la déclaration de la classe vecteur
    Attention, elle ne doit contenir aucune implémentation de méthode / fonction
 */

#ifndef _VECTEUR_H
#define _VECTEUR_H
#include <iostream>

// Déclaration de la classe vecteur
class Vecteur {
private :
	// attributs
	int m_dim;
	float * m_vec;

public :
	// prototypes des constructeurs et autres méthodes publiques
	Vecteur(int dim=3, float valInit=0);
	Vecteur(const Vecteur & v);
	float get(int indice) const;
	void set(int indice, float valeur);
	int dimensions() const;
	~Vecteur();
	Vecteur & operator=(const Vecteur & v);
	Vecteur operator+(const Vecteur &v1) const ;
	float & operator[](int indice);
	float operator[](int indice) const;

private :
	// méthodes privées d'implémentation (si besoin)
};

// Prototypes des fonctions
void afficherVecteur(const Vecteur * v, std::ostream & out=std::cout);
Vecteur * lireVecteur(std::istream & in =std::cin);
Vecteur add(const Vecteur * v1, const Vecteur * v2);
float operator*(const Vecteur & v1, const Vecteur & v2);
std::ostream & operator<<(std::ostream & out, const Vecteur & v);
std::istream & operator>>(std::istream & in, Vecteur & v);
#endif
