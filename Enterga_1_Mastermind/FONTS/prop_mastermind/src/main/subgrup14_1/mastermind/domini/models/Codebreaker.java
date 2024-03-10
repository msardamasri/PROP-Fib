package main.subgrup14_1.mastermind.domini.models;

import java.util.List;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Codebreaker {

	public Codebreaker(InfoPartida infoPartida) {
		super();
	}

	/**
	 * Obte les combinacions usades per trobar el codiSecret per part d'un dels algoritmes
	 * @param codiSecret Codi a desxifrar
	 * @param algoritme Algoritme escollit
	 * @return Retorna una llista amb tots els passos fins a trobar el codiSecret
	 */
	public List<List<Integer>> solve(List<Integer> solution) throws Exception {
		// Usat per a fer un @Override en cas de que la instancia sigui "CodebreakerMaquina", si no ho es no es crida aquest metode
		return null;
	}
}
