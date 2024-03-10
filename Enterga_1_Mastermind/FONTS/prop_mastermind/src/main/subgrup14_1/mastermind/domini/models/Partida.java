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
	
	public Partida(String uidPartida, String uidUsuari, Dificultat dificultat, Rol rol){
		this.acabada = false;
		this.numTorn = 0;
		this.infoPartida = new InfoPartida(uidUsuari, uidPartida, dificultat, rol);
		this.tempsPartida = (long) 0;
		if (rol == Rol.CODEBREAKER) this.puntuacio = 0;
		else {
			if (dificultat == Dificultat.FACIL) this.puntuacio = 700;
			else if (dificultat == Dificultat.INTERMIG) this.puntuacio = 1000;
			else this.puntuacio = 1300;
		}
	}
	public void inicialitzarCodiSecret(List<Integer> codiSecret) {
		this.infoPartida.setCodiSecret(codiSecret);
	}
	public void incrementaTorn() {
		this.numTorn += 1;
	}
	public void restaPuntuacio(Integer i) {
		this.puntuacio -= i;
	}
	public void fi() {
		this.acabada = true;
	}
	public void actualitzaTemps(Long l) {
		this.tempsPartida += l;
	}
	public void intentarCodi(List<Integer> codi) {
		this.intents.add(codi);
	}
	public Boolean esMaquinaCodemaker() {
		return this.infoPartida.getRol() == Rol.CODEBREAKER;
	}
	public void corretgirCodi(Pair<Integer, Integer> correccio) {
		this.correccions.add(correccio);
	}
	
	
	public Boolean getAcabada() {
		return acabada;
	}
	public void setAcabada(Boolean acabada) {
		this.acabada = acabada;
	}
	public Integer getNumTorn() {
		return numTorn;
	}
	public void setNumTorn(Integer numTorn) {
		this.numTorn = numTorn;
	}
	public InfoPartida getInfoPartida() {
		return infoPartida;
	}
	public void setInfoPartida(InfoPartida infoPartida) {
		this.infoPartida = infoPartida;
	}
	public Long getTempsPartida() {
		return tempsPartida;
	}
	public void setTempsPartida(Long tempsPartida) {
		this.tempsPartida = tempsPartida;
	}
	public Integer getPuntuacio() {
		return puntuacio;
	}
	public void setPuntuacio(Integer puntuacio) {
		this.puntuacio = puntuacio;
	}
	public List<List<Integer>> getIntents() {
		return intents;
	}
	public void setIntents(List<List<Integer>> intents) {
		this.intents = intents;
	}
	public List<Pair<Integer, Integer>> getCorreccions() {
		return correccions;
	}
	public void setCorreccions(List<Pair<Integer, Integer>> correccions) {
		this.correccions = correccions;
	}
}
