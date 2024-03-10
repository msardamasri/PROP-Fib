package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.models.Codemaker;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;

public class CodemakerTest {

	/**
     * Objecte de la prova: Test de la constructora de la classe Codemaker
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris. Valors introduits manualment
     * Operativa: En aquest test es comprova que al crear una instancia de la classe amb la
     * constructora aquesta no es nula
     */
    @Test
    public void constructoraTest() {
    	Codemaker cm = new Codemaker();
    	assertNotNull(cm);
    }
    
    /**
     * Objecte de la prova: Test del metode generarCodi de la classe Codemaker
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris. Valors introduits manualment
     * Operativa: En aquest test es comprova que al generar un codi secret aquest es
     * correcte acorde a la dificultat
     */
    @Test
    public void generarCodiTest() {
    	Codemaker cm = new Codemaker();
    	
    	List<Integer> codiFacil = cm.generarCodi(Dificultat.FACIL);
    	assertTrue(codiFacil.size() == 4);
    	for (Integer i: codiFacil) assertTrue(i >= 0 && i <= 3);
    	
    	List<Integer> codiIntermig = cm.generarCodi(Dificultat.INTERMIG);
    	assertTrue(codiIntermig.size() == 4);
    	for (Integer i: codiIntermig) assertTrue(i >= 0 && i <= 4);
    	
    	List<Integer> codiDificil = cm.generarCodi(Dificultat.DIFICIL);
    	assertTrue(codiDificil.size() == 4);
    	for (Integer i: codiDificil) assertTrue(i >= 0 && i <= 5);
    }
    
    /**
     * Objecte de la prova: Test del metode generarCodi de la classe Codemaker
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris. Valors introduits manualment
     * Operativa: En aquest test es comprova que es corretgeix be els codis segons la solucio
     * @throws ExcepcioPartida En cas de que el codi no sigui valid
     */
    @Test
    public void validarCodiTest() throws ExcepcioPartida {
    	Codemaker cm = new Codemaker();
    	
    	List<Integer> solucio = Arrays.asList(0,1,2,3);
    	
    	List<Integer> codiTotNegre = solucio;
    	assertEquals(Integer.valueOf(4), cm.validarCodi(codiTotNegre, solucio).getR());
    	
    	List<Integer> codiTotBlanc = Arrays.asList(1,2,3,0);
    	assertEquals(Integer.valueOf(4), cm.validarCodi(codiTotNegre, codiTotBlanc).getL());
    	
    	List<Integer> codiDosdeCada = Arrays.asList(0,1,3,2);
    	assertEquals(Integer.valueOf(2), cm.validarCodi(codiTotNegre, codiDosdeCada).getL());
    	assertEquals(Integer.valueOf(2), cm.validarCodi(codiTotNegre, codiDosdeCada).getR());
    }
}
