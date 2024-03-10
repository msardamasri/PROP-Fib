package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.utils.Pair;
import main.subgrup14_1.mastermind.utils.PartidaEnJoc;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class PartidaEnJocTest {

	/**
     * Objecte de la prova: Test de la constructora de la classe PartidaEnJoc
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris. Valors introduits manualment
     * Operativa: En aquest test es comprova que al crear una instancia de la classe amb la
     * constructora tots els valors s'inicialitzin correctament.
     */
    @Test
    public void testConstructor() {
        Integer puntuacio = 50;
        List<List<Integer>> intents = new ArrayList<>();
        List<Pair<Integer, Integer>> correccions = new ArrayList<>();
        Boolean acabada = false;
        Dificultat dificultat = Dificultat.DIFICIL;
        PartidaEnJoc partida = new PartidaEnJoc(puntuacio, intents, correccions, acabada, dificultat);

        assertEquals(puntuacio, partida.getPuntuacio());
        assertEquals(intents, partida.getIntents());
        assertEquals(correccions, partida.getCorreccions());
        assertEquals(acabada, partida.getAcabada());
        assertEquals(dificultat, partida.getDificultat());
    }

    /**
     * Objecte de la prova: Metodes getX i setX de la classe PartidaEnJoc
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar els metodes getX i setX obtenim i
     * configurem correctament els atributs de la classe
     */
    @Test
    public void testGettersSetters() {
        Integer puntuacio = 50;
        List<List<Integer>> intents = new ArrayList<>();
        List<Pair<Integer, Integer>> correccions = new ArrayList<>();
        Boolean acabada = false;
        Dificultat dificultat = Dificultat.DIFICIL;
        PartidaEnJoc partida = new PartidaEnJoc(puntuacio, intents, correccions, acabada, dificultat);

        Integer newPuntuacio = 100;
        List<List<Integer>> newIntents = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
        List<Pair<Integer, Integer>> newCorreccions = Arrays.asList(new Pair<Integer, Integer>(1, 1), new Pair<Integer, Integer>(2, 0));
        Boolean newAcabada = true;
        Dificultat newDificultat = Dificultat.INTERMIG;

        partida.setPuntuacio(newPuntuacio);
        partida.setIntents(newIntents);
        partida.setCorreccions(newCorreccions);
        partida.setAcabada(newAcabada);
        partida.setDificultat(newDificultat);

        assertEquals(newPuntuacio, partida.getPuntuacio());
        assertEquals(newIntents, partida.getIntents());
        assertEquals(newCorreccions, partida.getCorreccions());
        assertEquals(newAcabada, partida.getAcabada());
        assertEquals(newDificultat, partida.getDificultat());
    }
}
