package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.domini.models.InfoPartida;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class InfoPartidaTest {
	
	/**
     * Objecte de la prova: Test de la constructora de la classe InfoPartida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris. Valors introduits manualment
     * Operativa: En aquest test es comprova que al crear una instancia de la classe amb la
     * constructora tots els valors s'inicialitzin correctament.
     */
    @Test
    public void constructoraTest() {
    	String uidUsuari = "aaa123";
    	String uidPartida = "bbb123";
    	Dificultat dificultat = Dificultat.FACIL;
    	Rol rol = Rol.CODEBREAKER;
    	
    	InfoPartida ip = new InfoPartida(uidUsuari, uidPartida, dificultat, rol);
    	
    	String uidUsuariResultat = ip.getUidUsuari();
    	String uidPartidaResultat = ip.getUidPartida();
    	Dificultat dificultatResultat = ip.getDificultat();
    	Rol rolResultat = ip.getRol();

    	assertEquals(uidUsuari, uidUsuariResultat);
    	assertEquals(uidPartida, uidPartidaResultat);
    	assertEquals(dificultat, dificultatResultat);
    	assertEquals(rol, rolResultat);
    }
    
    /**
     * Objecte de la prova: Metodes getX i setX de la classe InfoPartida
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar els metodes getX i setX obtenim i
     * configurem correctament els atributs de la classe
     */
    @Test
    public void testGettersSetters() {
    	InfoPartida ip = new InfoPartida("uidUsuari", "uidPartida", Dificultat.FACIL, Rol.CODEMAKER);
        List<Integer> codiSecret = Arrays.asList(1, 2, 3, 4);
        ip.setCodiSecret(codiSecret);
        
        assertEquals("uidPartida", ip.getUidPartida());
        assertEquals(Dificultat.FACIL, ip.getDificultat());
        assertEquals(Integer.valueOf(12), ip.getTotalTorns());
        assertEquals("uidUsuari", ip.getUidUsuari());
        assertEquals(Rol.CODEMAKER, ip.getRol());
        assertEquals(Arrays.asList(1, 2, 3, 4), ip.getCodiSecret());
        
        LocalDateTime now = LocalDateTime.now();
        ip.setUidPartida("newUidPartida");
        ip.setDificultat(Dificultat.DIFICIL);
        ip.setTotalTorns(8);
        ip.setDataInici(now);
        ip.setUidUsuari("newUidUsuari");
        ip.setRol(Rol.CODEBREAKER);
        ip.setCodiSecret(null);
        
        assertEquals("newUidPartida", ip.getUidPartida());
        assertEquals(Dificultat.DIFICIL, ip.getDificultat());
        assertEquals(Integer.valueOf(8), ip.getTotalTorns());
        assertEquals(now, ip.getDataInici());
        assertEquals("newUidUsuari", ip.getUidUsuari());
        assertEquals(Rol.CODEBREAKER, ip.getRol());
        assertNull(ip.getCodiSecret());
    }
}
