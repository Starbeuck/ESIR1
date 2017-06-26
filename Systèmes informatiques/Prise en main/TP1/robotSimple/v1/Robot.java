package robotSimple.v1;

/**
   But : regrouper en une classe les valeurs caractéristiques (attributs) d'un
         robot indépendamment de toute application
 */
public class Robot {

  // attributs
  public int X, Y;
  public Direction direction;

  // initialiser un robot
  public Robot(int x, int y, Direction dir) {
    X = x; Y = y; direction = dir;
  }
} // Robot
