import java.io.IOException;

public class FFT_2D {

	//renvoie la TFD d'une image de complexes
	public static CpxImg FFT(CpxImg I) {

		CpxImg out = new CpxImg(I.taille());

		// FFT 1D sur les lignes
		for (int k = 0; k < I.taille(); k++)
			out.set_line(k,FFT_1D.FFT(I.get_line(k)));
		  
		// transposition
		out.transpose();

		// FFT 1D sur les "nouvelles" lignes de out (anciennes colonnes)
		for (int k = 0; k < I.taille(); k++)
			out.set_line(k,FFT_1D.FFT(out.get_line(k)));

		//on re transpose pour revenir dans le sens de d�part
		out.transpose();
		
		//on divise par la taille de I
		out.multiply(1./I.taille());
		return out.recentrage();
	}
	
	//renvoie la TFD inverse d'une images de complexes
	public static CpxImg FFT_inverse(CpxImg I) {
		I = I.recentrage();
		CpxImg out = new CpxImg(I.taille());
		for (int k = 0; k < I.taille(); k++)
			out.set_line(k, I.get_line(k).conjugue());

		out = FFT(out).recentrage();
		for (int k = 0; k < I.taille(); k++)
			out.set_line(k, out.get_line(k).conjugue());
		return out;
	}

	// compression par mise � z�ro des coefficients de fr�quence 
	// FI contient la TDF de I 
	// Dans FI on met � z�ros tous les coefficients correspondant � des fr�quences inf�rieures � k
	public static void compression(CpxImg FI, int k) {
		// A COMPLETER
		assert (2*k<FI.taille()) : "compression impossible, k trop grand";
		
		int centre = FI.taille()/2;
		
		for(int hauteur=0; hauteur<FI.taille();hauteur++){
			for(int largeur = 0; largeur<FI.taille(); largeur++){
				if(centre-k>hauteur || hauteur>centre+k || centre-k>largeur || largeur>centre+k){
					FI.set_p_imag(hauteur, largeur, 0);
					FI.set_p_reel(hauteur, largeur, 0);
				}
			}
		}
	}

	// compression par seuillage des coefficients faibles
	// FI contient la TDF de I 
	// Dans FI on met � z�ros tous les coefficients dont le module est inf�rieur � seuil 
	// on renvoie le nombre de coefficients conserv�s 
	public static int compression_seuil(CpxImg FI, double seuil){
		//A COMPLETER
		
		int total=0;
		
		for(int hauteur=0; hauteur<FI.taille();hauteur++){
			for(int largeur = 0; largeur<FI.taille(); largeur++){
				
				double module = Math.sqrt(Math.pow(FI.get_p_imag(hauteur, largeur),2)+(Math.pow(FI.get_p_reel(hauteur, largeur),2)));
				
				if(module<seuil){
					FI.set_p_imag(hauteur, largeur, 0);
					FI.set_p_reel(hauteur, largeur, 0);
				} else total++;
			}
		}
		
		return total;
	}

	
	public static void main(String[] args) {
		
		try {			
			//PLACEZ ICI VOS TESTS en 2D
			//Exemple, lecture
			BytePixmap BP = new BytePixmap("barbara_512.pgm");
			CpxImg I = new CpxImg(BP);
			
			CpxImg res = FFT(I);
			CpxImg res2 = FFT(I);
			
			//on supprime le coefficient central
			//res.set_p_imag(res.taille()/2, res.taille()/2, 0);
			//res.set_p_reel(res.taille()/2, res.taille()/2, 0);

			//seuil	
			double supp = compression_seuil(res,30);
			System.out.println(supp);
			CpxImg outseuil = FFT_inverse(res);	
			
			//compression
			compression(res2,(int)Math.sqrt(supp)/2	);
			CpxImg outcompress = FFT_inverse(res2);
			
			//Exemple, �criture
			BP = outseuil.convert_to_BytePixmap();
			BP.write("compressionseuil.pgm");
			
			BP = outcompress.convert_to_BytePixmap();
			BP.write("compression.pgm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
