package robotSimple.v2;
/* But : regrouper en une classe : 
     - les valeurs caractéristiques (attributs) d'un robot indépendamment de
       toute application en les rendant invisibles de l'extérieur
     - les opérations qu'on peut réaliser sur un robot, indépendamment de
       toute application
 */
public class Robot {

  // attributs cachés
  private int X, Y;
  private Direction direction;

  // initialiser un robot
  public Robot(int x, int y, Direction dir) { X = x; Y = y; direction = dir; }

  /** Faire avancer le robot (this) d'une unité dans sa direction actuelle
   *  @remarks : sa direction ne change pas
   */
  public void avancer() {
    switch (this.direction) {
    case Nord  : --this.Y; break;
    case Est   : ++this.X; break;
    case Sud   : ++this.Y; break;
    case Ouest : --this.X; break;
    }
  }

  /** Faire tourner le robot (this) d'un 1/4 de tour "à droite"
   *  @remarks : sa position ne change pas
   */
  public void tourner() {
    switch (this.direction) {
    case Nord  : this.direction = Direction.Est;   break;
    case Est   : this.direction = Direction.Sud;   break;
    case Sud   : this.direction = Direction.Ouest; break;
    case Ouest : this.direction = Direction.Nord;  break;
    }
  }

  // Déterminer si le robot this et le robot autre sont sur la même case
  public boolean memePosition(Robot autre) {
    return this.getX() == autre.getX() && this.getY() == autre.getY();
  }

  // @return l'abscisse du robot (this)
  public int getX() { return this.X; }

  // @return l'ordonnée du robot (this)
  public int getY() { return this.Y; }

  // @return la direction du robot (this)
  public Direction getDirection() { return this.direction; }
} // Robot
