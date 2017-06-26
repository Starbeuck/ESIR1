package util;

public class Couple <t1,t2>{
	private t1 numerateur;
	private t2 denominateur;
	/**
	 * constructeur
	 * @param args
	 */
	public Couple(t1 num, t2 den){
		//assert den != 0 : "le dénominateur doit être different de 0 ";
		this.numerateur=num;
		this.denominateur=den;
	}
	

	public void setFirst( t1 num){
		this.numerateur= num; 
	}
	public void setSecond (t2 den){
		this.denominateur=den;
	}
	public t1 getFirst(){
		return this.numerateur;
	}
	public t2 getSecond(){
		return this.denominateur;
	}
	public boolean equals(Couple b){
		return (this.getFirst()== b.getFirst()&& this.getSecond()==b.getSecond()) ;  
	}
	public static void main(String[] args) {
	
	}

}
