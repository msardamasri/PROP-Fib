package main.subgrup14_1.mastermind.domini.models;

import java.util.ArrayList;
import java.util.List;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.utils.Pair;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Codemaker {
	
	public Codemaker() {
		super();
	}
	
	/**
	 * Genera un codi aleatori acorde a la dificulat
	 * @param dificultat Dificultat de la partida
	 * @return Retorna una llista d'Integers amb 4 nombres aleatoris acorde a la dificultat
	 */
	public List<Integer> generarCodi(Dificultat dificultat) {
        List<Integer> codiSecret = new ArrayList<Integer>();
        for (int i = 0; i < 4; ++i) {
            if (dificultat == Dificultat.FACIL) {
                Integer aux = Utils.numeroAleatori(0, 3);
                while (codiSecret.contains(aux)) aux = Utils.numeroAleatori(0, 3);
                codiSecret.add(aux);
            }
            else if (dificultat == Dificultat.INTERMIG) {
                Integer aux = Utils.numeroAleatori(0, 4);
                codiSecret.add(aux);
            }
            else {
                Integer aux = Utils.numeroAleatori(0, 5);
                codiSecret.add(aux);
            }
        }
        return codiSecret;
	}
	
	/**
	 * Corretgeix el codi introduit
	 * @param codi Codi introduit pel codebreaker
	 * @param solucio Codi secret del codemaker
	 * @return Retorna un parell d'enters amb els punts blanc i negres com a component L i R respectivament
	 * @throws ExcepcioPartida Si el codi no te el tamany que hauria
	 */
	public Pair<Integer, Integer> validarCodi(List<Integer> codi, List<Integer> solucio) throws ExcepcioPartida {
		Integer blanc, negre;
		Integer[] corr = Utils.compararCombinacions(codi, solucio);
		blanc = corr[1];
		negre = corr[0];
		return new Pair<Integer, Integer>(blanc, negre);
	}
}
