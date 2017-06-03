package robotSimple.v1;

/**
   But : regrouper en une classe les opérations qu'on peut réaliser sur un
         robot, indépendamment de toute application
 */
public class OperationsRobot {

  // Faire avancer le robot paramètre d'une unité dans sa direction actuelle
  public static void avancer(Robot ceRobot)
  {
    switch (ceRobot.direction) {
    case Nord  : --ceRobot.Y; break;
    case Est   : ++ceRobot.X; break;
    case Sud   : ++ceRobot.Y; break;
    case Ouest : --ceRobot.X; break;
    }
  }

  // Faire tourner le robot paramètre d'un 1/4 de tour "à droite"
  public static void tourner(Robot ceRobot)
  {
    switch (ceRobot.direction) {
    case Nord  : ceRobot.direction = Direction.Est;   break;
    case Est   : ceRobot.direction = Direction.Sud;   break;
    case Sud   : ceRobot.direction = Direction.Ouest; break;
    case Ouest : ceRobot.direction = Direction.Nord;  break;
    }
  }
} // fin OperationsRobot
