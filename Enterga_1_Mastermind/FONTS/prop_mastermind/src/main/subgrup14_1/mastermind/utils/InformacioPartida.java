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
	public InformacioPartida(String uidPartida, LocalDateTime data, Integer puntuacio, Dificultat dificultat) {
		super();
		this.uidPartida = uidPartida;
		this.data = data;
		this.puntuacio = puntuacio;
		this.dificultat = dificultat;
	}
	public String getUidPartida() {
		return uidPartida;
	}
	public void setUidPartida(String uidPartida) {
		this.uidPartida = uidPartida;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Integer getPuntuacio() {
		return puntuacio;
	}
	public void setPuntuacio(Integer puntuacio) {
		this.puntuacio = puntuacio;
	}
	public Dificultat getDificultat() {
		return dificultat;
	}
	public void setDificultat(Dificultat dificultat) {
		this.dificultat = dificultat;
	}
}
