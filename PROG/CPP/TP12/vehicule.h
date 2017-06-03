// -*- c++ -*-
#ifndef _VEHICULE_H
#define _VEHICULE_H

#include <iostream>
/**
 * définition abstraite d'un Vehicule
 */

class Vehicule {
public:
  /** @param lg : longueur du véhicule
      @param nbp : nombre de personnes transportées
  */
  Vehicule(unsigned int lg = 1, unsigned int nbp = 1);

  /// destructeur
  virtual ~Vehicule();

  //! déterminer le tarif du véhicule
  virtual double	calculerTarif() const = 0;

  //! afficher les caractéristiques du véhicule
  virtual void		afficher(std::ostream & s = std::cout) const = 0;

  //! déterminer la longueur d'un véhicule
  unsigned int		getLongueur() const;

  /// déterminer le nombre de personnes dans le véhicule
  unsigned int		getPassagers() const;
  // mthode de copie
 virtual void  ajouter() const;
private:
  unsigned int m_longueur;		///< longueur du véhicule
  unsigned int m_passagers;		///< nombre de personnes dans le véhicule
protected:
  static const double tarifPassager = 15; ///< tarif passager
};

// opérateur << pour la classe Vehicule
std::ostream & operator << (std::ostream & sortie, const Vehicule & v);

#endif // _VEHICULE_H
