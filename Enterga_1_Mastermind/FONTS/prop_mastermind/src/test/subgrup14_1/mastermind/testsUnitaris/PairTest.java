package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.subgrup14_1.mastermind.utils.Pair;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class PairTest {
    
	/**
     * Objecte de la prova: Test de la constructora de la classe Pair
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al crear la classe amb la constructora tots els parametres
     * s'inicialitzin correctament.
     */
    @Test
    public void testConstructor() {
        Integer i = 1;
        String s = "hola";
        Pair<Integer, String> pair = new Pair<Integer, String>(i, s);
        assertEquals(i, pair.getL());
        assertEquals(s, pair.getR());
    }
    
    /**
     * Objecte de la prova: Metodes getX i setX de la classe Pair
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar els metodes getX i setX obtenim i
     * configurem correctament els atributs de la classe
     */
    @Test
    public void testGettersAndSetters() {
        Integer l = 1;
        String r = "hola";
        Pair<Integer, String> pair = new Pair<Integer, String>(l, r);
        assertEquals(l, pair.getL());
        assertEquals(r, pair.getR());
        
        Integer l2 = 2;
        String r2 = "bon dia";
        pair.setL(l2);
        pair.setR(r2);
        assertEquals(l2, pair.getL());
        assertEquals(r2, pair.getR());
    }
    
}
