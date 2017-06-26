package test;

import org.junit.Assert;
import org.junit.Test;

import tris.*;

public class TU_triTas {

	
	public static String afficherTab(int[] tnb){
		String result="| ";
		for(int i=0; i<tnb.length-1; i++){
			result = result + tnb[i] + " | ";
		}
		result = result + tnb[tnb.length-1];
		return result;
	}
	
	
	@Test
	public void testAjouterTas() {
		int[] t1 = {12,6,20,8,14,10,10,3,18,5,9,11,15};
		tris.TriTas.ajouterTas(t1,1);
		int[] t2 = {12,6,20,8,14,10,10,3,18,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t2)+ " mais obtenu : " +afficherTab(t1), t1, t2);
		
		
		tris.TriTas.ajouterTas(t1,2);
		int[] t3 = {20,6,12,8,14,10,10,3,18,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t3)+ " mais obtenu : " +afficherTab(t1), t1, t3);
		
		tris.TriTas.ajouterTas(t1,3);
		int[] t4 = {20,8,12,6,14,10,10,3,18,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t4)+ " mais obtenu : " +afficherTab(t1), t1, t4);
		
		tris.TriTas.ajouterTas(t1,4);
		int[] t5 = {20,14,12,6,8,10,10,3,18,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t5)+ " mais obtenu : " +afficherTab(t1), t1, t5);
		
		tris.TriTas.ajouterTas(t1,5);
		int[] t6 = {20,14,12,6,8,10,10,3,18,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t6)+ " mais obtenu : " +afficherTab(t1), t1, t6);
		
		tris.TriTas.ajouterTas(t1,6);
		int[] t7 = {20,14,12,6,8,10,10,3,18,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t7)+ " mais obtenu : " +afficherTab(t1), t1, t7);
		
		
		tris.TriTas.ajouterTas(t1,7);
		int[] t8 = {20,14,12,6,8,10,10,3,18,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t8)+ " mais obtenu : " +afficherTab(t1), t1, t8);
		
		tris.TriTas.ajouterTas(t1,8);
		int[] t9 = {20,18,12,14,8,10,10,3,6,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t9)+ " mais obtenu : " +afficherTab(t1), t1, t9);
	
		tris.TriTas.ajouterTas(t1,9);
		int[] t10 = {20,18,12,14,8,10,10,3,6,5,9,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t10)+ " mais obtenu : " +afficherTab(t1), t1, t10);
		
		tris.TriTas.ajouterTas(t1,10);
		int[] t11 = {20,18,12,14,9,10,10,3,6,5,8,11,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t11)+ " mais obtenu : " +afficherTab(t1), t1, t11);
		
		tris.TriTas.ajouterTas(t1,11);
		int[] t12 = {20,18,12,14,9,11,10,3,6,5,8,10,15};
		Assert.assertArrayEquals("attendu " + afficherTab(t12)+ " mais obtenu : " +afficherTab(t1), t1, t12);
	
		tris.TriTas.ajouterTas(t1,12);
		int[] t13 = {20,18,15,14,9,12,10,3,6,5,8,10,11};
		Assert.assertArrayEquals("attendu " + afficherTab(t13)+ " mais obtenu : " +afficherTab(t1), t1, t13);
	}

	@Test
	public void testSupprimerMax() {
		int[] t1 = {20,18,15,14,9,12,10,3,6,5,8,10,11};
		
		//Suppression de la racine 20
		tris.TriTas.supprimerMax(t1,13);
		int[] t2 = {18,14,15,11,9,12,10,3,6,5,8,10,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t2)+ " mais obtenu : " +afficherTab(t1), t1, t2);
		
		//Suppresion de la racine 18
		tris.TriTas.supprimerMax(t1,12);
		int[] t3 = {15,14,12,11,9,10,10,3,6,5,8,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t3)+ " mais obtenu : " +afficherTab(t1), t1, t3);
	
		tris.TriTas.supprimerMax(t1,11);
		int[] t4 = {14,11,12,8,9,10,10,3,6,5,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t4)+ " mais obtenu : " +afficherTab(t1), t1, t4);
	
		tris.TriTas.supprimerMax(t1,10);
		int[] t5 = {12,11,10,8,9,10,5,3,6,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t5)+ " mais obtenu : " +afficherTab(t1), t1, t5);
		
		tris.TriTas.supprimerMax(t1,9);
		int[] t6 = {11,9,10,8,6,10,5,3,12,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t6)+ " mais obtenu : " +afficherTab(t1), t1, t6);
		
		tris.TriTas.supprimerMax(t1,8);
		int[] t7 = {10,9,10,8,6,3,5,11,12,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t7)+ " mais obtenu : " +afficherTab(t1), t1, t7);
		
		tris.TriTas.supprimerMax(t1,7);
		int[] t8 = {10,9,5,8,6,3,10,11,12,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t8)+ " mais obtenu : " +afficherTab(t1), t1, t8);
		
		tris.TriTas.supprimerMax(t1,6);
		int[] t9 = {9,8,5,3,6,10,10,11,12,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t9)+ " mais obtenu : " +afficherTab(t1), t1, t9);
		
		tris.TriTas.supprimerMax(t1,5);
		int[] t10 = {8,6,5,3,9,10,10,11,12,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t10)+ " mais obtenu : " +afficherTab(t1), t1, t10);
		
		tris.TriTas.supprimerMax(t1,4);
		int[] t11 = {6,3,5,8,9,10,10,11,12,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t11)+ " mais obtenu : " +afficherTab(t1), t1, t11);
		
		tris.TriTas.supprimerMax(t1,3);
		int[] t12 = {5,3,6,8,9,10,10,11,12,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t12)+ " mais obtenu : " +afficherTab(t1), t1, t12);
		
		tris.TriTas.supprimerMax(t1,2);
		int[] t13 = {3,5,6,8,9,10,10,11,12,14,15,18,20};
		Assert.assertArrayEquals("attendu " + afficherTab(t13)+ " mais obtenu : " +afficherTab(t1), t1, t13);
		
	}

	@Test
	public void testTrier() {
		int[] t1 = {12,6,0,1};
		int[] t2 = {0,1,6,12};
		tris.TriTas.trier(t1, 4);
		Assert.assertArrayEquals("attendu " + afficherTab(t2)+ " mais obtenu : " +afficherTab(t1), t1, t2);
	}

}
