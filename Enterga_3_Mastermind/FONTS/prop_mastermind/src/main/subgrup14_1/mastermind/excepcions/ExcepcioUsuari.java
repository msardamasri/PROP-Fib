package main.subgrup14_1.mastermind.excepcions;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ExcepcioUsuari extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructora buida d'ExepcioUsuari
	 */
	public ExcepcioUsuari() {
		super();
	}
	
	/**
	 * Constructora amb text d'ExecepcioUsuar
	 * @param s Text d'excepcio
	 */
	public ExcepcioUsuari(String s) {
		super(s);
	}
}
