package main.subgrup14_1.mastermind.domini.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.utils.Pair;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Partida implements Serializable {
	
	private static final long serialVersionUID = 1 ;
	private Boolean acabada;
	private Integer numTorn;
	private InfoPartida infoPartida;
	private Long tempsPartida;
	private Integer puntuacio;
	private List<List<Integer>> intents = new ArrayList<>();
	private List<Pair<Integer, Integer>> correccions = new ArrayList<Pair<Integer, Integer>>();
	
	/**
	 * Crea una nova instencia de la classe Partida amb els paremetres especificats.
	 * @param uidPartida L'identificador de la partida.
	 * @param uidUsuari  L'identificador de l'usuari associat a la partida.
	 * @param dificultat La dificultat de la partida.
	 * @param rol        El rol de l'usuari en la partida.
	 */
	public Partida(String uidPartida, String uidUsuari, Dificultat dificultat, Rol rol){
		this.acabada = false;
		this.numTorn = 0;
		this.infoPartida = new InfoPartida(uidUsuari, uidPartida, dificultat, rol);
		this.tempsPartida = (long) 0;
		if (rol == Rol.CODEMAKER) this.puntuacio = 0;
		else {
			if (dificultat == Dificultat.FACIL) this.puntuacio = 1200;
			else if (dificultat == Dificultat.INTERMIG) this.puntuacio = 2000;
			else this.puntuacio = 2400;
		}
	}
	/**
	 * Inicialitza el codi secret de la partida amb la llista de nombres enters especificada.
	 * @param codiSecret La llista de nombres enters que representa el codi secret.
	 */
	public void inicialitzarCodiSecret(List<Integer> codiSecret) {
	    this.infoPartida.setCodiSecret(codiSecret);
	}
	/**
	 * Incrementa el nombre de torn actual en 1.
	 */
	public void incrementaTorn() {
	    this.numTorn += 1;
	}
	/**
	 * Resta la puntuacie especificada a la puntuacie actual de la partida.
	 * Si la puntuacie resulta ser negativa, es fixa a 0.
	 * @param i La puntuacie a restar.
	 */
	public void restaPuntuacio(Integer i) {
	    this.puntuacio -= i;
	    if (this.puntuacio < 0) {
	        this.puntuacio = 0;
	    }
	}
	/**
	 * Estableix l'estat de la partida com a "acabada".
	 */
	public void fi() {
	    this.acabada = true;
	}
	/**
	 * Actualitza el temps de la partida afegint el valor especificat.
	 * @param l El valor de temps a afegir.
	 */
	public void actualitzaTemps(Long l) {
	    this.tempsPartida += l;
	}
	/**
	 * Afegeix un intent de codi a la llista d'intents de la partida.
	 * @param codi L'intent de codi a afegir.
	 */
	public void intentarCodi(List<Integer> codi) {
	    this.intents.add(codi);
	}
	/**
	 * Comprova si el rol de la partida es CODEBREAKER (trencaclosques) per a l'usuari actual.
	 * @return Cert si l'usuari actual te el rol de CODEBREAKER, fals altrament.
	 */
	public Boolean esMaquinaCodemaker() {
	    return this.infoPartida.getRol() == Rol.CODEBREAKER;
	}
	/**
	 * Afegeix una correccie de codi a la llista de correccions de la partida.
	 * @param correccio La correccie de codi a afegir.
	 */
	public void corretgirCodi(Pair<Integer, Integer> correccio) {
	    this.correccions.add(correccio);
	}
	/**
	 * Obte l'estat de la partida (acabada o no).
	 * @return Cert si la partida este acabada, fals altrament.
	 */
	public Boolean getAcabada() {
	    return acabada;
	}
	/**
	 * Estableix l'estat de la partida (acabada o no).
	 * @param acabada L'estat de la partida (cert si este acabada, fals altrament).
	 */
	public void setAcabada(Boolean acabada) {
	    this.acabada = acabada;
	}
	/**
	 * Obte el nombre de torn actual de la partida. 
	 * @return El nombre de torn actual de la partida.
	 */
	public Integer getNumTorn() {
	    return numTorn;
	}
	/**
	 * Estableix el nombre de torn actual de la partida. 
	 * @param numTorn El nombre de torn actual de la partida.
	 */
	public void setNumTorn(Integer numTorn) {
	    this.numTorn = numTorn;
	}
	/**
	 * Obte la informacie de la partida.
	 * @return La informacie de la partida.
	 */
	public InfoPartida getInfoPartida() {
	    return infoPartida;
	}
	/**
	 * Estableix la informacie de la partida.
	 * @param infoPartida La informacie de la partida.
	 */
	public void setInfoPartida(InfoPartida infoPartida) {
	    this.infoPartida = infoPartida;
	}
	/**
	 * Obte el temps total de la partida en milisegons.
	 * @return El temps total de la partida en milisegons.
	 */
	public Long getTempsPartida() {
	    return tempsPartida;
	}
	/**
	 * Estableix el temps total de la partida en milisegons.
	 * @param tempsPartida El temps total de la partida en milisegons.
	 */
	public void setTempsPartida(Long tempsPartida) {
	    this.tempsPartida = tempsPartida;
	}
	/**
	 * Obte la puntuacie actual de la partida. 
	 * @return La puntuacie actual de la partida.
	 */
	public Integer getPuntuacio() {
	    return puntuacio;
	}
	/**
	 * Estableix la puntuacie actual de la partida. 
	 * @param puntuacio La puntuacie actual de la partida.
	 */
	public void setPuntuacio(Integer puntuacio) {
	    this.puntuacio = puntuacio;
	}
	/**
	 * Obte la llista d'intents de codi de la partida. 
	 * @return La llista d'intents de codi de la partida.
	 */
	public List<List<Integer>> getIntents() {
	    return intents;
	}
	/**
	 * Estableix la llista d'intents de codi de la partida. 
	 * @param intents La llista d'intents de codi de la partida.
	 */
	public void setIntents(List<List<Integer>> intents) {
	    this.intents = intents;
	}
	/**
	 * Obte la llista de correccions de codi de la partida. 
	 * @return La llista de correccions de codi de la partida.
	 */
	public List<Pair<Integer, Integer>> getCorreccions() {
	    return correccions;
	}
	/**
	 * Estableix la llista de correccions de codi de la partida.
	 * @param correccions La llista de correccions de codi de la partida.
	 */
	public void setCorreccions(List<Pair<Integer, Integer>> correccions) {
	    this.correccions = correccions;
	}

}
