package main.subgrup14_1.mastermind.excepcions;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ExcepcioPersistencia extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructora buida d'ExcepcioPersistencia
	 */
	public ExcepcioPersistencia() {
		super();
	}
	
	/**
	 * Constructora amb text d'ExcepcioPersistencia
	 * @param s Text d'excepcio
	 */
	public ExcepcioPersistencia(String s) {
		super(s);
	}
}
