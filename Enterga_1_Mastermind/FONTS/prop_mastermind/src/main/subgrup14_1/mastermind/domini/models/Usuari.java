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
	 * Constructor per defecte
	 */
	public Usuari() {	
	}
	
	/**
	 * Constructor de la classe Usuari
	 * @param nomUsuari : Nom de l'usuari
	 * @param rutaImgPerfil : Path de la imatge de perfil
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
	}
	
	// Getters + Setters
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNomUsuari() {
		return nomUsuari;
	}
	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}
	public Integer getMaxPuntuacio() {
		return maxPuntuacio;
	}
	public void setMaxPuntuacio(Integer maxPuntuacio) {
		this.maxPuntuacio = maxPuntuacio;
	}
	public Integer getNumPartidesGuanyades() {
		return numPartidesGuanyades;
	}
	public void setNumPartidesGuanyades(Integer numPartidesGuanyades) {
		this.numPartidesGuanyades = numPartidesGuanyades;
	}
	public Integer getNumPartidesPerdudes() {
		return numPartidesPerdudes;
	}
	public void setNumPartidesPerdudes(Integer numPartidesPerdudes) {
		this.numPartidesPerdudes = numPartidesPerdudes;
	}
	public String gethContrasenya() {
		return hContrasenya;
	}
	public void sethContrasenya(String hContrasenya) {
		this.hContrasenya = hContrasenya;
	}
}
