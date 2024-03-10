package main.subgrup14_1.mastermind.utils;

import java.io.Serializable;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Pair<L,R> implements Serializable{
	private static final long serialVersionUID = 1L;
	private L l;
    private R r;
    /**
     * Crea una nova instencia de la classe Pair amb els valors especificats.
     * 
     * @param l El valor de l'esquerra.
     * @param r El valor de la dreta.
     */
    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    /**
     * Obte el valor de l'esquerra.
     * 
     * @return El valor de l'esquerra.
     */
    public L getL() {
        return this.l;
    }

    /**
     * Obte el valor de la dreta.
     * 
     * @return El valor de la dreta.
     */
    public R getR() {
        return this.r;
    }

    /**
     * Estableix el valor de l'esquerra.
     * 
     * @param l El valor de l'esquerra.
     */
    public void setL(L l) {
        this.l = l;
    }

    /**
     * Estableix el valor de la dreta.
     * 
     * @param r El valor de la dreta.
     */
    public void setR(R r) {
        this.r = r;
    }

}