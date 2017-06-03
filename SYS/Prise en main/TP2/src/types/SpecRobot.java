package types;

/**
 * Modéliser le comportement d'un Robot
 * un Robot est défini par une position sur une grille infinie
 * et une direction parmi 4 possibles
 */

public interface SpecRobot
  extends Comparable<SpecRobot>
{

  // initialiser un robot avec deux coordonnées et une direction
  // public Robot(int x, int y, Direction dir);

  /**
   * Faire avancer le robot (this) d'une unité dans sa direction actuelle
   * @remarks : sa direction ne change pas
   */
  public void avancer();

  /**
   * Faire tourner le robot (this) d'un 1/4 de tour "à droite"
   * @remarks : sa position ne change pas
   */
  public void tourner();

  /**
   * Déterminer si le robot this et le robot autre sont sur la même case
   */
  public boolean memePosition(SpecRobot autre);

  /**
   * @return l'abscisse du robot (this)
   */
  public int getX();

  /**
   * @return l'ordonnée du robot (this)
   */
  public int getY();

  /**
   * @return la direction du robot (this)
   */
  public Direction getDirection();

  /**
   * Déterminer si le robot this et le robot autre ont même valeur
   */
  public boolean equals(SpecRobot autre);

  /**
   * fournir une représentation affichable d'un robot
   */
  public String toString();

  /**
   * @return copie du robot this
   */
  public SpecRobot clone();

  // comparaison ordonnée de 2 robots
  public int compareTo(SpecRobot autre);

} // SpecRobot
