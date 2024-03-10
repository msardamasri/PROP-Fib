package test.subgrup14_1.mastermind.testsUnitaris;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.utils.InformacioPartida;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class InformacioPartidaTest {

	/**
     * Objecte de la prova: Test de la constructora de la classe InformacioPartida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al crear la classe amb la constructora tots els parametres
     * s'inicialitzin correctament.
     */
    @Test
    public void testConstructor() {
        String uidPartida = "uidPartida";
        LocalDateTime data = LocalDateTime.now();
        Integer puntuacio = 100;
        Dificultat dificultat = Dificultat.FACIL;

        InformacioPartida informacioPartida = new InformacioPartida(uidPartida, data, puntuacio, dificultat);

        assertEquals(uidPartida, informacioPartida.getUidPartida());
        assertEquals(data, informacioPartida.getData());
        assertEquals(puntuacio, informacioPartida.getPuntuacio());
        assertEquals(dificultat, informacioPartida.getDificultat());
    }

    /**
     * Objecte de la prova: Metodes getX i setX de la classe InformacioPartida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar els metodes getX i setX obtenim i
     * configurem correctament els atributs de la classe
     */
    @Test
    public void testGettersSetters() {
        InformacioPartida informacioPartida = new InformacioPartida("uidPartida", LocalDateTime.now(), 100, Dificultat.FACIL);

        String newUidPartida = "newUidPartida";
        LocalDateTime newData = LocalDateTime.now().plusDays(1);
        Integer newPuntuacio = 200;
        Dificultat newDificultat = Dificultat.INTERMIG;

        informacioPartida.setUidPartida(newUidPartida);
        informacioPartida.setData(newData);
        informacioPartida.setPuntuacio(newPuntuacio);
        informacioPartida.setDificultat(newDificultat);

        assertEquals(newUidPartida, informacioPartida.getUidPartida());
        assertEquals(newData, informacioPartida.getData());
        assertEquals(newPuntuacio, informacioPartida.getPuntuacio());
        assertEquals(newDificultat, informacioPartida.getDificultat());
    }
}
