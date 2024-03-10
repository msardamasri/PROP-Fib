package main.subgrup14_1.mastermind.domini.interficies;

import java.util.List;

public interface Maquina {

	// Given the solution code, the solve operation uses one of the proposed algorithms (either five guess or the genetic one) to create the list of codes that will lead to the solution. If the algorithm is unable to find the solution in less than maxSteps steps, the returned list will contain a list composed of maxSteps codes. The operation will throw an exception in case the secret code solution is not consistent with the parameters of the current game
	
	/**
	 * Desxifra el codi secret
	 * @param solution Solucie al codiSecret
	 * @return Retorna els passos que s'han seguit per a desxifrar el codi
	 * @throws Exception Si el codi es invelid
	 */
	public List<List<Integer>>  solve (List<Integer> solution) throws Exception;
}