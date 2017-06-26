package main;

import java.util.Random;

import outilsArbre.OutilsArbre;

import types.ArbreBinaire;
import types.ArbreBinaireSimple;
import types.Direction;
import types.SpecRobot;
import robot.Robot;

public class mainAbin
{
  public static void main (String[] args)
  {
    Random generateurAleatoire = new Random();

    // créer les données de l'arbre
    Direction [ ] tdir    = { Direction.Nord, Direction.Est, Direction.Sud, Direction.Ouest };
    char      [ ] types   = { 'n', 'g', 'd', 'f' };
    SpecRobot [ ] trobots = new SpecRobot[15 + generateurAleatoire.nextInt(15)];
    char      [ ] tnoeud  = new char[trobots.length];
    for (int i = 0; i < trobots.length; ++i) {
      trobots[i] = new Robot(generateurAleatoire.nextInt(100),
			     generateurAleatoire.nextInt(100),
			     tdir[generateurAleatoire.nextInt(tdir.length)]);
      tnoeud[i] = types[generateurAleatoire.nextInt(types.length)];
    }

    // créer l'arbre
    // ArbreBinaire<SpecRobot> arbreRobots = abin.opAbin.faireArbre(trobots, tnoeud);
    ArbreBinaire<SpecRobot> arbreRobots = abin.opAbin.faireABR(trobots);
    OutilsArbre.afficher(arbreRobots, "Arbre de robots");
    
    // arbre de valeur max
    OutilsArbre.afficher(abin.opAbin.arbreMax(arbreRobots), "arbre de valeur max");

    // faire une copie
    ArbreBinaire<SpecRobot> copie = abin.opAbin.copierArbre(arbreRobots);
    OutilsArbre.afficher(copie, "copie \"normale\"");

    // faire une copie (variante APF)
    ArbreBinaire<SpecRobot> copieAPF = abin.opAbin.copierArbreAPF(arbreRobots);
    OutilsArbre.afficher(copieAPF, "copie \"APF\"");
  }
}
