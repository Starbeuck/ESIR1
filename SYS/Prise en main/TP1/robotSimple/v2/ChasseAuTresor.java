package robotSimple.v2;
import java.util.Scanner;
import java.util.Random;

public class ChasseAuTresor
{
  //Le Grand Jeu de la Chasse Au Trésor

  public static void main(String[] args)
  {
    Scanner entree = new Scanner(System.in);
    Random generateurAleatoire= new Random();

    // choisir au hasard la position du trésor
    int xTresor = generateurAleatoire.nextInt(10);
    int yTresor = generateurAleatoire.nextInt(10);

    // choisir au hasard la position du robot et fixer sa direction
    int xr  = generateurAleatoire.nextInt(10);
    int yr  = generateurAleatoire.nextInt(10);

    // créer et initialiser un robot
    Robot astro = new Robot(xr, yr, Direction.Nord);

    // déterminer (le carré de) la distance du robot au trésor
    int distance = carreDistance(astro.getX(), astro.getY(), xTresor, yTresor);
    int nbCoups = 0;

    // répéter tant que le robot n'est pas sur le trésor
    while (distance != 0) {
      //indiquer au joueur la distance au trésor
      System.out.println("direction : " + astro.getDirection() + " distance : " + distance);

      // obtenir un ordre
      System.out.print("[a]vancer, [t]ourner, [s]top ? ");
      String ordre = entree.nextLine();

      //exécuter l'ordre
      if      (ordre.equals("a")) { astro.avancer(); }
      else if (ordre.equals("t")) { astro.tourner(); }
      else if (ordre.equals("s")) { break; }

      // recalculer la distance du robot au trésor
      distance = carreDistance(astro.getX(), astro.getY(), xTresor, yTresor);
      ++nbCoups;
    } // fin while

    // fin itération ; 2 sorties possibles : distance == 0 ou abandon
    if (distance == 0) { System.out.println("Gagné en " + nbCoups + " coups"); }
    else               { System.out.println("Abandon au bout de " + nbCoups + " coups"); }
  } // fin main

  // déterminer lequel des 2 robots est le plus proche du trésor
  static Robot lePlusProche(Robot premier, Robot deuxieme, int xt, int yt)
  {
    if (carreDistance(premier.getX(), premier.getY(), xt, yt)
	<
	carreDistance(deuxieme.getX(), deuxieme.getY(), xt, yt))
      {
	return premier;
      }
    else {
      return deuxieme;
    }
  }

  // distance entre deux points
  static int carreDistance(int xr, int yr, int xt, int yt)
  {
    return (xr - xt) * (xr - xt) + (yr - yt) * (yr - yt);
  }
} // fin ChasseAuTresor
