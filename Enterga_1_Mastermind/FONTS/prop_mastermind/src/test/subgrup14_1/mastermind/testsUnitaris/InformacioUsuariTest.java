package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.*;

import org.junit.Test;

import main.subgrup14_1.mastermind.utils.InformacioUsuari;

public class InformacioUsuariTest {

	/**
     * Objecte de la prova: Test de la constructora de la classe InformacioUsuari
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al crear la classe amb la constructora tots els parametres
     * s'inicialitzin correctament.
     */
    @Test
    public void testConstructor() {
        InformacioUsuari iu = new InformacioUsuari("123", "user1", 100, 5);
        assertNotNull(iu);
    }

    /**
     * Objecte de la prova: Metodes getX i setX de la classe InformacioUsuari
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar els metodes getX i setX obtenim i
     * configurem correctament els atributs de la classe
     */
    @Test
    public void testGettersSetters() {
        InformacioUsuari iu = new InformacioUsuari("123", "user1", 100, 5);

        assertEquals("123", iu.getUid());
        assertEquals("user1", iu.getUsername());
        assertEquals(Integer.valueOf(100), iu.getMaxPuntuacio());
        assertEquals(Integer.valueOf(5), iu.getNumPartidesJugades());

        iu.setUid("456");
        assertEquals("456", iu.getUid());

        iu.setUsername("user2");
        assertEquals("user2", iu.getUsername());

        iu.setMaxPuntuacio(200);
        assertEquals(Integer.valueOf(200), iu.getMaxPuntuacio());

        iu.setNumPartidesJugades(10);
        assertEquals(Integer.valueOf(10), iu.getNumPartidesJugades());
    }

    /**
     * Objecte de la prova: Comparador de la classe InformacioUsuari
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al usar el comparador, aquest compara les diferents
     * instancies en funcio de l'atribut maxPuntuacio
     */
    @Test
    public void testCompareTo() {
        InformacioUsuari iu1 = new InformacioUsuari("123", "user1", 100, 5);
        InformacioUsuari iu2 = new InformacioUsuari("456", "user2", 200, 10);

        assertTrue(iu1.compareTo(iu2) > 0);
        assertTrue(iu2.compareTo(iu1) < 0);

        iu1.setMaxPuntuacio(200);
        assertTrue(iu1.compareTo(iu2) == 0);
    }
}
