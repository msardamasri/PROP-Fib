package main.subgrup14_1.mastermind.excepcions;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ExcepcioPartida extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructora buida d'ExcepcioPartida
	 */
	public ExcepcioPartida() {
		super();
	}
	
	/**
	 * Constructora amb text d'ExcepcioPartida
	 * @param s Text d'excepcio
	 */
	public ExcepcioPartida(String s) {
		super(s);
	}
}
