package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.domini.models.Codebreaker;
import main.subgrup14_1.mastermind.domini.models.CodebreakerMaquina;
import main.subgrup14_1.mastermind.domini.models.InfoPartida;

public class CodebreakerTest {
	/**
     * Objecte de la prova: Test de la constructora de la classe Codebreaker
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris. Valors introduits manualment
     * Operativa: En aquest test es comprova que al crear una instancia de la classe amb la
     * constructora aquesta no es nula
     */
    @Test
    public void constructoraTest() {
    	InfoPartida ip = new InfoPartida("uidUsuari", "uidPartida", Dificultat.FACIL, Rol.CODEMAKER);
    	Codebreaker cb = new Codebreaker(ip);
    	assertNotNull(cb);
    }
    
    /**
     * Objecte de la prova: Test del metode solve de la classe Codebreaker
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris. Valors introduits manualment
     * Operativa: En aquest test es comprova que al cridar al metode aquest retorna una llista
     * amb com a molt totalTorns itents per desxifrar el codi secret i que aquests tenen el tamany correcte
     * @throws Exception 
     */
    @Test
    public void solveGeneticTest() throws Exception {
    	InfoPartida ip = new InfoPartida("uidUsuari", "uidPartida", Dificultat.FACIL, Rol.CODEMAKER);
    	ip.setCodiSecret(Arrays.asList(0,1,2,3));
    	Codebreaker cb = new CodebreakerMaquina(ip, 0);
    	
    	List<List<Integer>> intents = cb.solve(Arrays.asList(0,1,2,3));
    	
    	assertNotNull(intents);
    	assertTrue(intents.size() <= (Integer)ip.getTotalTorns());
    	
    	for (List<Integer> intent: intents) assertEquals((Integer)intent.size(), Integer.valueOf(4));
    }
    
    /**
     * Objecte de la prova: Test del metode solve de la classe Codebreaker
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris. Valors introduits manualment
     * Operativa: En aquest test es comprova que al cridar al metode aquest retorna una llista
     * amb com a molt totalTorns itents per desxifrar el codi secret i que aquests tenen el tamany correcte
     * @throws Exception 
     */
    @Test
    public void solveFiveGuessTest() throws Exception {
    	InfoPartida ip = new InfoPartida("uidUsuari", "uidPartida", Dificultat.DIFICIL, Rol.CODEMAKER);
    	// No repetint Colors
    	ip.setCodiSecret(Arrays.asList(0,1,2,3));
    	Codebreaker cb = new CodebreakerMaquina(ip, 1);
    	
    	List<List<Integer>> intents = cb.solve(Arrays.asList(0,1,2,3));
    	
    	assertNotNull(intents);
    	assertTrue(intents.size() <= (Integer)ip.getTotalTorns());
    	
    	for (List<Integer> intent: intents) assertEquals((Integer)intent.size(), Integer.valueOf(4));
    	
    	// Repetint Colors
    	ip.setCodiSecret(Arrays.asList(1,1,4,4));
    	
    	intents = cb.solve(Arrays.asList(1,1,4,4));
    	
    	assertNotNull(intents);
    	assertTrue(intents.size() <= (Integer)ip.getTotalTorns());
    	
    	for (List<Integer> intent: intents) assertEquals((Integer)intent.size(), Integer.valueOf(4));
    }
}
