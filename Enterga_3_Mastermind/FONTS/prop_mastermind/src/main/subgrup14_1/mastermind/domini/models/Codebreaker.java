package main.subgrup14_1.mastermind.domini.models;

import java.util.List;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Codebreaker {

	public Codebreaker() {
		super();
	}

	/**
	 * Obte les combinacions usades per trobar el codiSecret per part d'un dels algoritmes
	 * @param solution Codi a desxifrar
	 * @return Retorna una llista amb tots els passos fins a trobar el codiSecret
	 * @throws Exception Si el codi es invalid
	 */
	public List<List<Integer>> solve(List<Integer> solution) throws Exception {
		// Usat per a fer un @Override en cas de que la instancia sigui "CodebreakerMaquina", si no ho es no es crida aquest metode
		return null;
	}
}
