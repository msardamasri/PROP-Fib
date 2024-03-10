package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.domini.models.InfoPartida;
import main.subgrup14_1.mastermind.domini.models.Partida;
import main.subgrup14_1.mastermind.utils.Pair;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class PartidaTest {
    private Partida partida;
    
    @Before
    public void setUp() {
        partida = new Partida("uidPartida", "uidUsuari", Dificultat.FACIL, Rol.CODEBREAKER);
    }
    
    /**
     * Objecte de la prova: Test de la constructora de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al crear la classe amb la constructora tots els parametres
     * s'inicialitzin correctament.
     */
    @Test
    public void testConstructor() {
        assertFalse(partida.getAcabada());
        assertEquals(Integer.valueOf(0), partida.getNumTorn());
        assertEquals("uidPartida", partida.getInfoPartida().getUidPartida());
        assertEquals("uidUsuari", partida.getInfoPartida().getUidUsuari());
        assertEquals(Dificultat.FACIL, partida.getInfoPartida().getDificultat());
        assertEquals(Rol.CODEBREAKER, partida.getInfoPartida().getRol());
        assertEquals(Long.valueOf(0), partida.getTempsPartida());
        assertEquals(Integer.valueOf(0), partida.getPuntuacio());
        assertEquals(new ArrayList<List<Integer>>(), partida.getIntents());
        assertEquals(new ArrayList<Pair<Integer, Integer>>(), partida.getCorreccions());
    }
    
    /**
     * Objecte de la prova: Test del metode inicialitzarCodiSecret de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode els atributs es configurin
     * correctament
     */
    @Test
    public void testIncialitzarCodiSecret() {
        List<Integer> codiSecret = Arrays.asList(1, 2, 3, 4);
        partida.inicialitzarCodiSecret(codiSecret);
        assertEquals(codiSecret, partida.getInfoPartida().getCodiSecret());
    }
    
    /**
     * Objecte de la prova: Test del metode incrementaTorn de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode els atributs canviin
     * correctament
     */
    @Test
    public void testIncrementaTorn() {
        assertEquals(Integer.valueOf(0), partida.getNumTorn());
        partida.incrementaTorn();
        assertEquals(Integer.valueOf(1), partida.getNumTorn());
    }
    
    /**
     * Objecte de la prova: Test del metode restaPuntuacio de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode els atributs canviin
     * correctament
     */
    @Test
    public void testRestaPuntuacio() {
        assertEquals(Integer.valueOf(0), partida.getPuntuacio());
        partida.restaPuntuacio(50);
        assertEquals(Integer.valueOf(-50), partida.getPuntuacio());
    }
    
    /**
     * Objecte de la prova: Test del metode fi de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode els atributs canviin
     * correctament
     */
    @Test
    public void testFi() {
        assertFalse(partida.getAcabada());
        partida.fi();
        assertTrue(partida.getAcabada());
    }
    
    /**
     * Objecte de la prova: Test del metode actualitzaTemps de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode els atributs canviin
     * correctament
     */
    @Test
    public void testActualitzaTemps() {
        assertEquals(Long.valueOf(0), partida.getTempsPartida());
        partida.actualitzaTemps(5000L);
        assertEquals(Long.valueOf(5000), partida.getTempsPartida());
    }
    
    /**
     * Objecte de la prova: Test del metode intentarCodi de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode els atributs canviin
     * correctament
     */
    @Test
    public void testIntentarCodi() {
        List<Integer> codi = Arrays.asList(1, 2, 3, 4);
        partida.intentarCodi(codi);
        assertEquals(Arrays.asList(codi), partida.getIntents());
    }
    
    /**
     * Objecte de la prova: Test del metode esMaquinaCodemaker de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode es retorni el valor esperat
     */
    @Test
    public void testEsMaquinaCodemaker() {
        assertTrue(partida.esMaquinaCodemaker());
        partida.setInfoPartida(new InfoPartida("uidUsuari", "uidPartida", Dificultat.FACIL, Rol.CODEMAKER));
        assertFalse(partida.esMaquinaCodemaker());
    }
    
    /**
     * Objecte de la prova: Test del metode corretgirCodi de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode els atributs canviin
     * correctament
     */
    @Test
    public void testCorretgirCodi() {
        Pair<Integer, Integer> correccio = new Pair<Integer, Integer>(2, 1);
        partida.corretgirCodi(correccio);
        assertEquals(1, partida.getCorreccions().size());
        assertEquals(correccio, partida.getCorreccions().get(0));
    }
    
    /**
     * Objecte de la prova: Metodes getX i setX de la classe Partida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar els metodes getX i setX obtenim i
     * configurem correctament els atributs de la classe
     */
    @Test
    public void testGettersSetters() {
        partida.setAcabada(true);
        assertTrue(partida.getAcabada());

        partida.setNumTorn(5);
        assertEquals(5, (int) partida.getNumTorn());

        InfoPartida infoPartida = new InfoPartida("uidUsuari", "uidPartida", Dificultat.FACIL, Rol.CODEMAKER);
        partida.setInfoPartida(infoPartida);
        assertEquals(infoPartida, partida.getInfoPartida());

        partida.setTempsPartida(100L);
        assertEquals(100L, (long) partida.getTempsPartida());

        partida.setPuntuacio(500);
        assertEquals(500, (int) partida.getPuntuacio());

        List<List<Integer>> intents = new ArrayList<>();
        intents.add(Arrays.asList(1, 2, 3, 4));
        intents.add(Arrays.asList(5, 6, 7, 8));
        partida.setIntents(intents);
        assertEquals(intents, partida.getIntents());

        List<Pair<Integer, Integer>> correccions = new ArrayList<>();
        correccions.add(new Pair<Integer, Integer>(2, 1));
        correccions.add(new Pair<Integer, Integer>(0, 4));
        partida.setCorreccions(correccions);
        assertEquals(correccions, partida.getCorreccions());
    }
}
