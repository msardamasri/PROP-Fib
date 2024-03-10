package main.subgrup14_1.mastermind.utils;

import java.time.LocalDateTime;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class InformacioPartida {
	private String uidPartida;
	private LocalDateTime data;
	private Integer puntuacio;
	private Dificultat dificultat;
	/**
	 * Crea una nova instencia de la classe InformacioPartida amb els paremetres especificats. 
	 * @param uidPartida L'identificador de la partida.
	 * @param data       La data de la partida.
	 * @param puntuacio  La puntuacie de la partida.
	 * @param dificultat La dificultat de la partida.
	 */
	public InformacioPartida(String uidPartida, LocalDateTime data, Integer puntuacio, Dificultat dificultat) {
	    super();
	    this.uidPartida = uidPartida;
	    this.data = data;
	    this.puntuacio = puntuacio;
	    this.dificultat = dificultat;
	}
	/**
	 * Obte l'identificador de la partida. 
	 * @return L'identificador de la partida.
	 */
	public String getUidPartida() {
	    return uidPartida;
	}
	/**
	 * Estableix l'identificador de la partida. 
	 * @param uidPartida L'identificador de la partida.
	 */
	public void setUidPartida(String uidPartida) {
	    this.uidPartida = uidPartida;
	}
	/**
	 * Obte la data de la partida. 
	 * @return La data de la partida.
	 */
	public LocalDateTime getData() {
	    return data;
	}
	/**
	 * Estableix la data de la partida. 
	 * @param data La data de la partida.
	 */
	public void setData(LocalDateTime data) {
	    this.data = data;
	}
	/**
	 * Obte la puntuacie de la partida. 
	 * @return La puntuacie de la partida.
	 */
	public Integer getPuntuacio() {
	    return puntuacio;
	}
	/**
	 * Estableix la puntuacie de la partida. 
	 * @param puntuacio La puntuacie de la partida.
	 */
	public void setPuntuacio(Integer puntuacio) {
	    this.puntuacio = puntuacio;
	}
	/**
	 * Obte la dificultat de la partida. 
	 * @return La dificultat de la partida.
	 */
	public Dificultat getDificultat() {
	    return dificultat;
	}
	/**
	 * Estableix la dificultat de la partida. 
	 * @param dificultat La dificultat de la partida.
	 */
	public void setDificultat(Dificultat dificultat) {
	    this.dificultat = dificultat;
	}

}
