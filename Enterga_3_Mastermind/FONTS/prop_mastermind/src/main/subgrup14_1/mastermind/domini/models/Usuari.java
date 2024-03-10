package main.subgrup14_1.mastermind.domini.models;

import java.io.Serializable;

import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Usuari implements Serializable {

	private static final long serialVersionUID = 1;
	private String uid;
	private String nomUsuari;
	private String hContrasenya;
	private Integer maxPuntuacio;
	private Integer numPartidesGuanyades;
	private Integer numPartidesPerdudes;
	
	/**
	 * Constructora per defecte
	 */
	public Usuari() {	
	}
	
	/**
	 * Constructora de la classe Usuari amb paremetres especefics
	 * @param nomUsuari: Nom de l'usuari
	 * @param hContrasenya: hash de la contrasenya
	 */
	public Usuari(String nomUsuari,
				  String hContrasenya)
	{
		super();
		this.uid = Utils.generateUID();
		this.nomUsuari = nomUsuari;
		this.maxPuntuacio = 0;
		this.numPartidesGuanyades = 0;
		this.numPartidesPerdudes = 0;
		this.hContrasenya = hContrasenya;
	}
	/**
	 * Obte el nombre total de partides que ha jugat l'usuari
	 * @return Retorna el nombre de partides totals que ha jugat l'usuari
	 */
	public Integer numPartidesTotals() {
		return numPartidesGuanyades + numPartidesPerdudes;
	}
	/**
	 * Actualitza els valors de partides guanyades/perdudes i la puntuacio maxima
	 * @param guanyat True si la partida ha resultat en una victoria per part de l'usuari o no
	 * @param puntuacio Puntuacio de la partida
	 */
	public void actualitzaEstadistiques(Boolean guanyat, Integer puntuacio) {
		if (guanyat) this.numPartidesGuanyades++;
		else this.numPartidesPerdudes++;
		if (puntuacio > this.maxPuntuacio) this.maxPuntuacio = puntuacio;
	}/**
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
	public String getNomUsuari() {
	    return nomUsuari;
	}
	/**
	 * Estableix el nom d'usuari.
	 * @param nomUsuari El nom d'usuari.
	 */
	public void setNomUsuari(String nomUsuari) {
	    this.nomUsuari = nomUsuari;
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
	 * Obte el nombre de partides guanyades per l'usuari.
	 * @return El nombre de partides guanyades per l'usuari.
	 */
	public Integer getNumPartidesGuanyades() {
	    return numPartidesGuanyades;
	}
	/**
	 * Estableix el nombre de partides guanyades per l'usuari.
	 * @param numPartidesGuanyades El nombre de partides guanyades per l'usuari.
	 */
	public void setNumPartidesGuanyades(Integer numPartidesGuanyades) {
	    this.numPartidesGuanyades = numPartidesGuanyades;
	}
	/**
	 * Obte el nombre de partides perdudes per l'usuari. 
	 * @return El nombre de partides perdudes per l'usuari.
	 */
	public Integer getNumPartidesPerdudes() {
	    return numPartidesPerdudes;
	}
	/**
	 * Estableix el nombre de partides perdudes per l'usuari. 
	 * @param numPartidesPerdudes El nombre de partides perdudes per l'usuari.
	 */
	public void setNumPartidesPerdudes(Integer numPartidesPerdudes) {
	    this.numPartidesPerdudes = numPartidesPerdudes;
	}
	/**
	 * Obte la contrasenya de l'usuari en format hash. 
	 * @return La contrasenya de l'usuari en format hash.
	 */
	public String gethContrasenya() {
	    return hContrasenya;
	}
	/**
	 * Estableix la contrasenya de l'usuari en format hash.
	 * @param hContrasenya La contrasenya de l'usuari en format hash.
	 */
	public void sethContrasenya(String hContrasenya) {
	    this.hContrasenya = hContrasenya;
	}
}
