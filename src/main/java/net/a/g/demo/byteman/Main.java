package net.a.g.demo.byteman;

public class Main {

	public static void main(String[] args) {
		ObjectToIntercept oti = new ObjectToIntercept();
		
		String retour = oti.call();
		
		System.out.println("Retour : "+retour);
		
	}

}
