package main.subgrup14_1.mastermind.utils;

import java.util.List;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class PartidaEnJoc {
    private Integer puntuacio;
    private List<List<Integer>> intents;
    private List<Pair<Integer, Integer>> correccions;
    private Boolean acabada;
    private Dificultat dificultat;
    public PartidaEnJoc(Integer puntuacio, List<List<Integer>> intents, List<Pair<Integer, Integer>> correccions,
            Boolean acabada, Dificultat dificultat) {
        super();
        this.puntuacio = puntuacio;
        this.intents = intents;
        this.correccions = correccions;
        this.acabada = acabada;
        this.dificultat = dificultat;
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
    public Boolean getAcabada() {
        return acabada;
    }
    public void setAcabada(Boolean acabada) {
        this.acabada = acabada;
    }
	public Dificultat getDificultat() {
		return dificultat;
	}
	public void setDificultat(Dificultat dificultat) {
		this.dificultat = dificultat;
	}
}