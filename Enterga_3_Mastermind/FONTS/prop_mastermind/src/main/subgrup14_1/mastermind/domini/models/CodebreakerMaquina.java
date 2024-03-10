package main.subgrup14_1.mastermind.domini.models;

import java.util.List;

import main.subgrup14_1.mastermind.domini.interficies.Maquina;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class CodebreakerMaquina extends Codebreaker {
	
	private Maquina algoritme;
	
	/**
	 * Constructora de CodebreakerMaquina
	 * @param infoPartida Informacio de la partida
	 * @param algoritme Algorisme a usar
	 */
	public CodebreakerMaquina(InfoPartida infoPartida, Integer algoritme) {
		if (algoritme == 0) this.algoritme = new AlgoritmeFiveGuess(infoPartida);
		else if (algoritme == 1) this.algoritme = new Genetic(infoPartida);
	}
	
	@Override
	public List<List<Integer>>  solve (List<Integer> solution) throws Exception {
		return this.algoritme.solve(solution);
	}
}
