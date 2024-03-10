package main.subgrup14_1.mastermind.utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class InformacioUsuari implements Comparable<InformacioUsuari> {
	private String uid;
	private String username;
	private Integer maxPuntuacio;
	private Integer numPartidesJugades;
	/**
	 * Crea una nova instencia de la classe InformacioUsuari amb els paremetres especificats.
	 * 
	 * @param uid               L'identificador de l'usuari.
	 * @param username          El nom d'usuari.
	 * @param maxPuntuacio      La puntuacie mexima de l'usuari.
	 * @param numPartidesJugades El nombre de partides jugades per l'usuari.
	 */
	public InformacioUsuari(String uid, String username, Integer maxPuntuacio, Integer numPartidesJugades) {
	    super();
	    this.uid = uid;
	    this.username = username;
	    this.maxPuntuacio = maxPuntuacio;
	    this.numPartidesJugades = numPartidesJugades;
	}
	/**
	 * Obte l'identificador de l'usuari. 
	 * @return L'identificador de l'usuari.
	 */
	public String getUid() {
	    return uid;
	}
	/**
	 * Estableix l'identificador de l'usuari. 
	 * @param uid L'identificador de l'usuari.
	 */
	public void setUid(String uid) {
	    this.uid = uid;
	}
	/**
	 * Obte el nom d'usuari. 
	 * @return El nom d'usuari.
	 */
	public String getUsername() {
	    return username;
	}
	/**
	 * Estableix el nom d'usuari. 
	 * @param username El nom d'usuari.
	 */
	public void setUsername(String username) {
	    this.username = username;
	}
	/**
	 * Obte la puntuacie mexima de l'usuari. 
	 * @return La puntuacie mexima de l'usuari.
	 */
	public Integer getMaxPuntuacio() {
	    return maxPuntuacio;
	}
	/**
	 * Estableix la puntuacie mexima de l'usuari. 
	 * @param maxPuntuacio La puntuacie mexima de l'usuari.
	 */
	public void setMaxPuntuacio(Integer maxPuntuacio) {
	    this.maxPuntuacio = maxPuntuacio;
	}
	/**
	 * Obte el nombre de partides jugades per l'usuari. 
	 * @return El nombre de partides jugades per l'usuari.
	 */
	public Integer getNumPartidesJugades() {
	    return numPartidesJugades;
	}
	/**
	 * Estableix el nombre de partides jugades per l'usuari. 
	 * @param numPartidesJugades El nombre de partides jugades per l'usuari.
	 */
	public void setNumPartidesJugades(Integer numPartidesJugades) {
	    this.numPartidesJugades = numPartidesJugades;
	}
	/**
	 * Compara aquesta instencia d'InformacioUsuari amb una altra per ordre de puntuacie mexima descendent.
	 * @param i L'objecte InformacioUsuari a comparar.
	 * @return Un valor negatiu si aquesta instencia te una puntuacie mexima mes gran que la instencia proporcionada,
	 *         un valor positiu si te una puntuacie mexima mes petita, o zero si les puntuacions meximes sen iguals.
	 */
	@Override
	public int compareTo(InformacioUsuari i) {
	    return i.getMaxPuntuacio().compareTo(this.getMaxPuntuacio());
	}

}
