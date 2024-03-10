package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;


import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class UtilsTest {

	/**
     * Objecte de la prova: Test del metode estatic numeroAleatori
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que retorni un numero dins del rang indicat
     */
    @Test
    public void testNumeroAleatori() {
        int num = Utils.numeroAleatori(1, 10);
        assertTrue(num >= 1 && num <= 10);
    }

    /**
     * Objecte de la prova: Test del metode estatic cls
     * Fitxers de dades necessaris: Cap
     * Operativa: En aquest test es comprova que el metode no llenci cap excepcio
     */
    @Test
    public void testCls() {
        // No s'ha de provar gaire cosa
        Utils.cls();
    }

    /**
     * Objecte de la prova: Test del metode estatic printTitol
     * Fitxers de dades necessaris: Cap
     * Operativa: En aquest test es comprova que el metode no llenci cap excepcio
     */
    @Test
    public void testPrintTitol() {
        // No s'ha de provar gaire cosa
        Utils.printTitol("TEST DE TITOL");
    }

    /**
     * Objecte de la prova: Test del metode estatic llegirNum
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que el metode llegeixi i retorni el valor introduit correctament
     */
    @Test
    public void testLlegirNum() throws IOException {
        String entrada = "42\n";
        InputStream in = new ByteArrayInputStream(entrada.getBytes());
        System.setIn(in);

        int num = Utils.llegirNum("Introdueix un numero");

        assertEquals(42, num);
    }

    /**
     * Objecte de la prova: Test del metode estatic llegirString
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que el metode llegeixi i retorni el valor introduit correctament
     */
    @Test
    public void testLlegirString() throws IOException {
        String entrada = "hola\n";
        InputStream in = new ByteArrayInputStream(entrada.getBytes());
        System.setIn(in);

        String s = Utils.llegirString("Introdueix un string");

        assertEquals("hola", s);
    }

    /**
     * Objecte de la prova: Test del metode estatic llegirchar
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que el metode llegeixi i retorni el valor introduit correctament
     */
    @Test
    public void testLlegirchar() throws IOException {
        String entrada = "x\n";
        InputStream in = new ByteArrayInputStream(entrada.getBytes());
        System.setIn(in);

        char c = Utils.llegirchar("Introdueix un caracter");

        assertEquals('x', c);
    }

    /**
     * Objecte de la prova: Test del metode estatic hashSHA256
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que el metode calculi el hash correcte comparant-lo
     * amb un generat amb un altre aplicacio usant el mateix algoritme de hash
     */
    @Test
    public void testHashSHA256() {
        String hash = Utils.hashSHA256("proba de hash");
        assertEquals("df9dae8297a15932ede66be2618405a6a9b337c43499affd83706ac2bb528923", hash);
    }

    /**
     * Objecte de la prova: Test del metode estatic generateUID
     * Fitxers de dades necessaris: Cap
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que el metode generi i retorni un valor no nul
     */
    @Test
    public void testGenerateUID() {
        String uid = Utils.generateUID();
        assertNotNull(uid);
    }
    
    	
}
