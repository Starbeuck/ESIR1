package robot;

import types.Direction;
import types.SpecRobot;

// Implémentation simple d'un robot

public class Robot implements SpecRobot {

  // attributs cachés
  private int X, Y;
  private Direction direction;

  // initialiser un robot
  public Robot(int x, int y, Direction dir) {
    X = x; Y = y; direction = dir;
  }

  // Faire avancer le robot (this) d'une unité dans sa direction actuelle
  public void avancer() {
    switch (this.getDirection()) {
    case Nord  : --this.Y; break;
    case Est   : ++this.X; break;
    case Sud   : ++this.Y; break;
    case Ouest : --this.X; break;
    }
  }

  // Faire tourner le robot (this) d'un 1/4 de tour "à droite"
  public void tourner() {
    switch (this.getDirection()) {
    case Nord  : this.direction = Direction.Est;   break;
    case Est   : this.direction = Direction.Sud;   break;
    case Sud   : this.direction = Direction.Ouest; break;
    case Ouest : this.direction = Direction.Nord;  break;
    }
  }

  // Déterminer si le robot this et le robot autre sont sur la même case
  public final boolean memePosition(SpecRobot autre) {
    return this.getX() == autre.getX() && this.getY() == autre.getY();
  }

  // @return l'abscisse du robot (this)
  public int getX() { return this.X; }

  // @return l'ordonnée du robot (this)
  public int getY() { return this.Y; }

  // @return la direction du robot (this)
  public Direction getDirection() { return this.direction; }

  // Déterminer si le robot this et le robot autre ont même valeur
  public boolean equals(SpecRobot autre) {
    return
      this.getX()         ==  autre.getX() &&
      this.getY()         ==  autre.getY() &&
      this.getDirection() ==  autre.getDirection();
  }

  // fournir une représentation affichable d'un robot
  public String toString() {
    return "(" + this.getX() + "," + this.getY() + "," + this.getDirection() + ")";
  }

  // renvoyer une copie du robot this
  public SpecRobot clone() {
    return new Robot(this.getX(), this.getY(), this.getDirection());
  }

  // comparaison ordonnée de 2 robots
  public int compareTo(SpecRobot autre)
  {
    if (this.getX() == autre.getX()) {
      return (int) (this.getY() - autre.getY());
    }
    else {
      return (int) (this.getX() - autre.getX());
    }
  }

} // Robot
