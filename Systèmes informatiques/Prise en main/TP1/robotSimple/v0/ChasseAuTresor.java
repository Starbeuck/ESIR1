package robotSimple.v0;
import java.util.Scanner;
import java.util.Random;

enum Direction { Nord, Est, Sud, Ouest }

public class ChasseAuTresor {
  public static void main(String[] args) {
    Scanner entree = new Scanner(System.in);
    Random generateurAleatoire = new Random();

    // choisir au hasard la position du trésor
    int xTresor = generateurAleatoire.nextInt(10);
    int yTresor = generateurAleatoire.nextInt(10);

    // choisir au hasard la position du robot et fixer sa direction
    int xRobot  = generateurAleatoire.nextInt(10);
    int yRobot  = generateurAleatoire.nextInt(10);
    Direction dirRobot = Direction.Nord;

    // déterminer (le carré de) la distance du robot au trésor
    int distance = carreDistance(xRobot, yRobot, xTresor, yTresor);

    int nbCoups  = 0;
    // répéter tant que le robot n'est pas sur le trésor
    while (distance != 0) {
      //indiquer au joueur la distance au trésor
      System.out.println("direction : " + dirRobot + " distance : " + distance);

      // obtenir un ordre et l'exécuter
      System.out.print("[a]vancer, [t]ourner, [s]top ? ");
      String ordre = entree.nextLine();
      if (ordre.equals("a")) {
        // Faire avancer le robot d'une unité dans sa direction actuelle
        switch (dirRobot) {
        case Nord  : --yRobot; break;
        case Est   : ++xRobot; break;
        case Sud   : ++yRobot; break;
        case Ouest : --xRobot; break;
        }
      }
      else if (ordre.equals("t")) {
        // Faire tourner le robot d'un 1/4 de tour "à droite"
        switch (dirRobot) {
        case Nord  : dirRobot = Direction.Est;   break;
        case Est   : dirRobot = Direction.Sud;   break;
        case Sud   : dirRobot = Direction.Ouest; break;
        case Ouest : dirRobot = Direction.Nord;  break;
        }
      } else if (ordre.equals("s")) { break; }

      // recalculer la distance du robot au trésor
      distance = carreDistance(xRobot, yRobot, xTresor, yTresor);
      ++nbCoups;
    } // fin while

    // fin itération ; 2 sorties possibles : distance == 0 ou abandon
    if (distance == 0) { System.out.println("Gagné en " + nbCoups + " coups"); }
    else               { System.out.println("Abandon au bout de " + nbCoups + " coups"); }
  } // fin main

  // distance entre deux points
  static int carreDistance(int xr, int yr, int xt, int yt) {
    return (xr - xt) * (xr - xt) + (yr - yt) * (yr - yt);
  }
} // fin ChasseAuTresor
