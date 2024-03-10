package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.models.AlgoritmeFiveGuess;
import main.subgrup14_1.mastermind.domini.models.InfoPartida;

public class AlgoritmeFiveGuessTest {
	
	private static void backtrack(List<List<Integer>> combinaciones, List<Integer> combinacionActual, int[] numeros, int tamano) {
        if (combinacionActual.size() == tamano) {
            combinaciones.add(new ArrayList<>(combinacionActual));
        } else {
            for (int i = 0; i < numeros.length; i++) {
                if (combinacionActual.contains(numeros[i])) {
                    continue;
                }
                combinacionActual.add(numeros[i]);
                backtrack(combinaciones, combinacionActual, numeros, tamano);
                combinacionActual.remove(combinacionActual.size() - 1);
            }
        }
    }

	@Test
    public void testAlgoritmeFiveGuess() {
		
        InfoPartida ip = new InfoPartida(null, null, Dificultat.FACIL, null);
        AlgoritmeFiveGuess afg = new AlgoritmeFiveGuess(ip);
        assertNotNull(afg);
        
    }

	@Test
	public void testCombinacioInicial() {
		
		//dificultat facil
		InfoPartida ip1 = new InfoPartida(null, null, Dificultat.FACIL, null);
		AlgoritmeFiveGuess afg1 = new AlgoritmeFiveGuess(ip1);
		List<Integer> solve1  = Arrays.asList(0, 1, 2, 3);
		
		List<Integer> solucioProposada1  = new ArrayList<>();
		afg1.combinacioInicial(solucioProposada1);
		assertEquals(solve1, solucioProposada1);
		
		/*------------------------------------------*/
		
		//dificultat mitja
		InfoPartida ip2 = new InfoPartida(null, null, Dificultat.INTERMIG, null);
		AlgoritmeFiveGuess afg2 = new AlgoritmeFiveGuess(ip2);
		List<Integer> solve2  = Arrays.asList(0, 0, 1, 1);
		
		List<Integer> solucioProposada2  = new ArrayList<>();
		afg2.combinacioInicial(solucioProposada2);
		assertEquals(solve2, solucioProposada2);
		
		/*------------------------------------------*/
		
		//dificultat dificil
		InfoPartida ip3 = new InfoPartida(null, null, Dificultat.DIFICIL, null);
		AlgoritmeFiveGuess afg3 = new AlgoritmeFiveGuess(ip3);
		List<Integer> solve3  = Arrays.asList(0, 0, 1, 1);
		
		List<Integer> solucioProposada3  = new ArrayList<>();
		afg3.combinacioInicial(solucioProposada3);
		assertEquals(solve3, solucioProposada3);
		
	}

	@Test
	public void testGenerarCombinacionsRepetides() {
		
		List<List<Integer>> combinacions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        List<Integer> combinacio = new ArrayList<>();
                        combinacio.add(i);
                        combinacio.add(j);
                        combinacio.add(k);
                        combinacio.add(l);
                        combinacions.add(combinacio);
                    }
                }
            }
        }
        
        InfoPartida ip1 = new InfoPartida(null, null, Dificultat.FACIL, null);
		AlgoritmeFiveGuess afg1 = new AlgoritmeFiveGuess(ip1);
		afg1.generarCombinacionsRepetides();
		List<List<Integer>> solve = afg1.getPossiblesCombinacions();
		assertEquals(solve, combinacions);
		
	}
	
	@Test
	public void testgetPossiblesCombinacions() {
		
		InfoPartida ip = new InfoPartida(null, null, Dificultat.FACIL, null);
        AlgoritmeFiveGuess afg = new AlgoritmeFiveGuess(ip);
        assertNotNull(afg.getPossiblesCombinacions());
        
    }

	@Test
	public void testGenerarCombinacionsNoRepetides() {
		
		List<List<Integer>> combinacions = new ArrayList<>();
        backtrack(combinacions, new ArrayList<Integer>(), new int[]{0, 1, 2, 3}, 4);
        
        InfoPartida ip1 = new InfoPartida(null, null, Dificultat.FACIL, null);
		AlgoritmeFiveGuess afg1 = new AlgoritmeFiveGuess(ip1);
		afg1.generarCombinacionsNoRepetides();
		List<List<Integer>> solve = afg1.getPossiblesCombinacions();
		assertEquals(solve, combinacions);
		
	}
	

	@Test
	public void testMinmax() {
		
		List<Integer> proximIntent = new ArrayList<>();
		List<Integer> sol  = Arrays.asList(0, 1, 2, 3);
        
        InfoPartida ip = new InfoPartida(null, null, Dificultat.FACIL, null);
        AlgoritmeFiveGuess afg = new AlgoritmeFiveGuess(ip);
        afg.generarCombinacionsNoRepetides();
        afg.minmax(proximIntent);
        
        assertEquals(proximIntent, sol);
        
	}

	@Test
	public void testCompararCombinacions() {
		
		Integer[] resultatComparacio = new Integer[] {1, 3};
		List<Integer> sol  = Arrays.asList(2, 3, 1, 0);
		List<Integer> comp  = Arrays.asList(1, 3, 0, 2);
		
		InfoPartida ip1 = new InfoPartida(null, null, Dificultat.FACIL, null);
		AlgoritmeFiveGuess afg1 = new AlgoritmeFiveGuess(ip1);
		Integer[] solve = afg1.compararCombinacions(sol, comp);
		
		assertArrayEquals(resultatComparacio, solve);
		
	}
	
	@Test
	public void testSolve() {
		
		InfoPartida ip = new InfoPartida(null, null, Dificultat.FACIL, null);
		AlgoritmeFiveGuess afg = new AlgoritmeFiveGuess(ip);
		
		List<Integer> sol  = Arrays.asList(2, 3, 1, 0);
		List<List<Integer>> totalCombinacions = new ArrayList<>();
		totalCombinacions = afg.solve(sol);
		
		List<Integer> s1 = Arrays.asList(0, 1, 2, 3);
		List<Integer> s2 = Arrays.asList(1, 0, 3, 2);
		List<Integer> s3 = Arrays.asList(2, 3, 0, 1);
		List<Integer> s4 = Arrays.asList(2, 3, 1, 0);
		List<List<Integer>> resultatEsperat = new ArrayList<>();
		resultatEsperat.add(s1);
		resultatEsperat.add(s2);
		resultatEsperat.add(s3);
		resultatEsperat.add(s4);
		
		assertEquals(resultatEsperat, totalCombinacions);
		
		/*------------------------------------------*/
		
		InfoPartida ip2 = new InfoPartida(null, null, Dificultat.INTERMIG, null);
		AlgoritmeFiveGuess afg2 = new AlgoritmeFiveGuess(ip2);
		
		List<Integer> sol2  = Arrays.asList(4, 4, 2, 1);
		List<List<Integer>> totalCombinacions2 = new ArrayList<>();
		totalCombinacions2 = afg2.solve(sol2);
		
		List<Integer> s12 = Arrays.asList(0, 0, 1, 1);
		List<Integer> s22 = Arrays.asList(0, 2, 3, 4);
		List<Integer> s32 = Arrays.asList(2, 3, 2, 1);
		List<Integer> s42 = Arrays.asList(2, 1, 4, 1);
		List<Integer> s52 = Arrays.asList(1, 4, 2, 1);
		List<Integer> s62 = Arrays.asList(4, 4, 2, 1);
		List<List<Integer>> resultatEsperat2 = new ArrayList<>();
		resultatEsperat2.add(s12);
		resultatEsperat2.add(s22);
		resultatEsperat2.add(s32);
		resultatEsperat2.add(s42);
		resultatEsperat2.add(s52);
		resultatEsperat2.add(s62);
		
		assertEquals(resultatEsperat2, totalCombinacions2);
		
		/*------------------------------------------*/
		
		InfoPartida ip3 = new InfoPartida(null, null, Dificultat.DIFICIL, null);
		AlgoritmeFiveGuess afg3 = new AlgoritmeFiveGuess(ip3);
		
		List<Integer> sol3  = Arrays.asList(5, 2, 0, 1);
		List<List<Integer>> totalCombinacions3 = new ArrayList<>();
		totalCombinacions3 = afg3.solve(sol3);
		
		List<Integer> s13 = Arrays.asList(0, 0, 1, 1);
		List<Integer> s23 = Arrays.asList(0, 1, 0, 0);
		List<Integer> s33 = Arrays.asList(2, 3, 0, 1);
		List<Integer> s43 = Arrays.asList(4, 2, 0, 1);
		List<Integer> s53 = Arrays.asList(1, 2, 0, 1);
		List<Integer> s63 = Arrays.asList(5, 2, 0, 1);
		List<List<Integer>> resultatEsperat3 = new ArrayList<>();
		resultatEsperat3.add(s13);
		resultatEsperat3.add(s23);
		resultatEsperat3.add(s33);
		resultatEsperat3.add(s43);
		resultatEsperat3.add(s53);
		resultatEsperat3.add(s63);
		
		assertEquals(resultatEsperat3, totalCombinacions3);
		
	}

}