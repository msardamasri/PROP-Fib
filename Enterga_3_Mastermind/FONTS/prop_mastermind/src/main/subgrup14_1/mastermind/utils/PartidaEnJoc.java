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
    /**
     * Crea una nova instencia de la classe PartidaEnJoc amb els valors especificats.
     *
     * @param puntuacio     La puntuacie de la partida en joc.
     * @param intents       La llista d'intents realitzats durant la partida en joc.
     * @param correccions   La llista de correccions associades als intents de la partida en joc.
     * @param acabada       Indica si la partida en joc ha finalitzat.
     * @param dificultat    La dificultat de la partida en joc.
     */
    public PartidaEnJoc(Integer puntuacio, List<List<Integer>> intents, List<Pair<Integer, Integer>> correccions,
            Boolean acabada, Dificultat dificultat) {
        super();
        this.puntuacio = puntuacio;
        this.intents = intents;
        this.correccions = correccions;
        this.acabada = acabada;
        this.dificultat = dificultat;
    }
    /**
     * Obte la puntuacie de la partida en joc.
     * @return La puntuacie de la partida en joc.
     */
    public Integer getPuntuacio() {
        return puntuacio;
    }
    /**
     * Estableix la puntuacie de la partida en joc.
     * @param puntuacio La puntuacie de la partida en joc.
     */
    public void setPuntuacio(Integer puntuacio) {
        this.puntuacio = puntuacio;
    }
    /**
     * Obte la llista d'intents realitzats durant la partida en joc.
     * @return La llista d'intents de la partida en joc.
     */
    public List<List<Integer>> getIntents() {
        return intents;
    }
    /**
     * Estableix la llista d'intents de la partida en joc.
     * @param intents La llista d'intents de la partida en joc.
     */
    public void setIntents(List<List<Integer>> intents) {
        this.intents = intents;
    }
    /**
     * Obte la llista de correccions associades als intents de la partida en joc.
     * @return La llista de correccions de la partida en joc.
     */
    public List<Pair<Integer, Integer>> getCorreccions() {
        return correccions;
    }
    /**
     * Estableix la llista de correccions de la partida en joc.
     * @param correccions La llista de correccions de la partida en joc.
     */
    public void setCorreccions(List<Pair<Integer, Integer>> correccions) {
        this.correccions = correccions;
    }
    /**
     * Comprova si la partida en joc ha finalitzat.
     * @return Cert si la partida en joc ha finalitzat, fals altrament.
     */
    public Boolean getAcabada() {
        return acabada;
    }
    /**
     * Estableix l'estat de finalitzacie de la partida en joc.
     * @param acabada Cert per indicar que la partida en joc ha finalitzat, fals altrament.
     */
    public void setAcabada(Boolean acabada) {
        this.acabada = acabada;
    }
    /**
     * Obte la dificultat de la partida en joc.
     * @return La dificultat de la partida en joc.
     */
    public Dificultat getDificultat() {
        return dificultat;
    }
    /**
     * Estableix la dificultat de la partida en joc.
     * @param dificultat La dificultat de la partida en joc.
     */
    public void setDificultat(Dificultat dificultat) {
        this.dificultat = dificultat;
    }

}