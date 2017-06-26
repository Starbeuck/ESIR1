package Sac_A_Dos;

import Algo_Genetiques.Individu;

public class Individu_SAD implements Individu {
	int t[]; 
	double x[];
	int n;
	
	public Individu_SAD(double b[], int c){
		this.t= new int [b.length];
		for(int i=0; i<t.length; i++){
			if(Math.random()<0.5){
				t[i]=0;
			}else{
				t[i]=1;
			}
		}
		x=b;
		n=c;
	}
	
	public Individu_SAD(int c){
		n=c;		
	}

	@Override
	public double adaptation() {
		double fiogj=0;
		for(int i=0; i<this.t.length;i++){
			fiogj+= t[i]*x[i];
		}
		if(fiogj>n){
			return 0;
		}else{
			return fiogj;
		}
	}

	@Override
	public Individu[] croisement(Individu conjoint) {
		// TODO Auto-generated method stub
		assert(conjoint instanceof Individu_SAD);
		Individu_SAD conjointagain = (Individu_SAD)conjoint;
		
		assert(conjointagain.n==n && conjointagain.x==x);
		
		int g=(int)Math.random()*this.t.length; 
		Individu_SAD unicorn = new Individu_SAD(n);
		Individu_SAD poneyyy = new Individu_SAD(n);
		
		for(int i =0; i<this.t.length;i++){
			if(i<g){
				unicorn.t[i]=t[i];
				poneyyy.t[i]=conjointagain.t[i];
			} else {
				unicorn.t[i]=conjointagain.t[i];
				poneyyy.t[i]=t[i];
			}
		}
		
		Individu sortie []= {unicorn,poneyyy};
		return sortie;
	}

	@Override
	public void mutation(double prob) {
		// TODO Auto-generated method stub
		
		for(int i =0;i < t.length ; i++){
			if(Math.random()<prob){
				t[i]=Math.abs(t[i]-1);
			}
		}
	}

}
