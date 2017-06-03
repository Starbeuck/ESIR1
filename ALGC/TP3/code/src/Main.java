import java.util.LinkedList;
import java.util.List;

import robdd.ROBDD;
import expression.*;

public class Main {

	public static void main(String[] args) {
		/*//EXEMPLE 
		Expression exp = new Et(new Atome("x"),new Atome("y")); // représente (x ^ y)
		System.out.println(exp.atomes()); // affiche la liste des atomes (=variables booléennes) présents dans exp
		exp = exp.remplace("x",true); // exp vaut maintenant (true ^ y)
		//System.out.println(exp.evalue()); // <- erreur car (true ^ y) ne peut pas être évalué
		exp = exp.remplace("y",false); // exp vaut maintenant (true ^ false)
		System.out.println(exp.evalue());
		
		//Affichage de l'arbre associé à l'expression exp pour l'ordre x > y 
		List<String> ordre_atomes = new LinkedList<String>();
		ordre_atomes.add("x");
		ordre_atomes.add("y");
		System.out.println("\n Arbre de exp : \n" + exp.arbre(ordre_atomes)); // <- que se passe-t-il ? -> evalue le résultat de l'expression
		Expression exp2 = new Et(new Atome("x"),new Atome("y")); // représente (x ^ y)
		System.out.println("\n Arbre de exp2 : \n" + exp2.arbre(ordre_atomes));		*/
		
		//Exercice 2
		/*Expression equiv1 = new Equiv(new Atome("x1"),new Atome("y1")); // représente (x1 <=> y1)
		Expression equiv2 = new Equiv(new Atome("x2"),new Atome("y2")); // représente (x2 <=> y2)
		Expression f = new Et(equiv1, equiv2); //représente (x1 <=> y1) ^ (x2 <=> y2)
		
		//affichage de l'arbre non évalué dans l'ordre 1
		List<String> ordre_atomesf = new LinkedList<String>();
		ordre_atomesf.add("x1");
		ordre_atomesf.add("x2");
		ordre_atomesf.add("y1");
		ordre_atomesf.add("y2");
		System.out.println("\n Arbre de exp : \n" + f.arbre(ordre_atomesf)); 
		
		//affichage de l'arbre non évalué dans l'ordre 2
		for(int i=0;i<ordre_atomesf.size();i++)	ordre_atomesf.remove(i);
		ordre_atomesf.add("x1");
		ordre_atomesf.add("y1");
		ordre_atomesf.add("x2");
		ordre_atomesf.add("y2");
		System.out.println("\n Arbre de exp : \n" + f.arbre(ordre_atomesf)); 
		
		// test possible
		f = f.remplace("x1", true);
		f = f.remplace("x2", true);
		f = f.remplace("y1", false);
		f = f.remplace("y2", true);
		System.out.println(f.evalue()); //rend false car x2 != y2
		
		//ordre 1 x1 > x2 > y1 > y2	
		System.out.println("\n Arbre de exp : \n" + f.arbre(ordre_atomesf)); //nous permet de vérifier le résultat
		
		//ordre 2 x1 > y1 > x2 > y2
		for(int i=0;i<ordre_atomesf.size();i++)	ordre_atomesf.remove(i);
		ordre_atomesf.add("x1");
		ordre_atomesf.add("y1");
		ordre_atomesf.add("x2");
		ordre_atomesf.add("y2");
		System.out.println("\n Arbre de exp : \n" + f.arbre(ordre_atomesf)); */
		
		//CONSTRUCTION DU ROBDD
		//Exercice 3
		// test de estVrai et estFaux
		/*Expression exp4= new Constante (true);
		System.out.println(exp4.estVrai());// vrai car exp vaut vrai 
		exp4=new Non (new Constante (false));
		System.out.println(exp4.estVrai());// faux car exp4 n'est pas une constante et ne vaut pas vrai
		System.out.println(exp4.estFaux());// faux car exp4 n'est pas du type "constante"*/
		
		//Exercice 5
		Expression equiv1 = new Equiv(new Atome("x1"),new Atome("y1")); // représente (x1 <=> y1)
		Expression equiv2 = new Equiv(new Atome("x2"),new Atome("y2")); // représente (x2 <=> y2)
		Expression f = new Et(equiv1, equiv2); //représente (x1 <=> y1) ^ (x2 <=> y2)
		
		System.out.println(f.robdd().toString());
	}
}
