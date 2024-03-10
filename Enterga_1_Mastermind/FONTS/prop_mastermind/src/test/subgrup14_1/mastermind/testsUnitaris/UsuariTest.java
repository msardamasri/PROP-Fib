package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import main.subgrup14_1.mastermind.domini.models.Usuari;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class UsuariTest {

	/**
     * Objecte de la prova: Test de la constructora de la classe Usuari
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al crear la classe amb la constructora tots els parametres
     * s'inicialitzin correctament.
     */
	@Test
	public void testConstructor() {
		Usuari usuari = new Usuari("nomUsuari", "hContrasenya");
		assertNotNull(usuari);
		assertEquals("nomUsuari", usuari.getNomUsuari());
		assertEquals("hContrasenya", usuari.gethContrasenya());
		assertEquals(Integer.valueOf(0), usuari.getMaxPuntuacio());
		assertEquals(Integer.valueOf(0), usuari.getNumPartidesGuanyades());
		assertEquals(Integer.valueOf(0), usuari.getNumPartidesPerdudes());
	}

	/**
     * Objecte de la prova: Metode numPartidesTotals de la classe Usuari
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar el metode retorni el valor esperat.
     */
	@Test
	public void testNumPartidesTotals() {
		Usuari usuari = new Usuari("nom", "pwd");
		usuari.setNumPartidesGuanyades(2);
		usuari.setNumPartidesPerdudes(3);
		assertEquals(Integer.valueOf(5), usuari.numPartidesTotals());
	}

	/**
     * Objecte de la prova: Metode actualitzaEstadistiques de la classe Usuari
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar els valors s'actualitzin segons l'esperat
     */
	@Test
	public void testActualitzaEstadistiques() {
		Usuari usuari = new Usuari("nom", "pwd");
		usuari.actualitzaEstadistiques(true, 10);
		assertEquals(Integer.valueOf(1), usuari.getNumPartidesGuanyades());
		assertEquals(Integer.valueOf(0), usuari.getNumPartidesPerdudes());
		assertEquals(Integer.valueOf(10), usuari.getMaxPuntuacio());

		usuari.actualitzaEstadistiques(false, 5);
		assertEquals(Integer.valueOf(1), usuari.getNumPartidesGuanyades());
		assertEquals(Integer.valueOf(1), usuari.getNumPartidesPerdudes());
		assertEquals(Integer.valueOf(10), usuari.getMaxPuntuacio());

		usuari.actualitzaEstadistiques(true, 20);
		assertEquals(Integer.valueOf(2), usuari.getNumPartidesGuanyades());
		assertEquals(Integer.valueOf(1), usuari.getNumPartidesPerdudes());
		assertEquals(Integer.valueOf(20), usuari.getMaxPuntuacio());
	}
	
	/**
     * Objecte de la prova: Metodes getX i setX de la classe Usuari
     * Fitxers de dades necessaris: Dades introduides manualment. No calen fitxers addicionals.
     * Valors estudiats: Estrategia caixa gris.
     * Operativa: En aquest test es comprova que al cridar els metodes getX i setX obtenim i
     * configurem correctament els atributs de la classe
     */
	@Test
	public void testGettersSetters() {
		Usuari usuari = new Usuari("nom", "pwd");
		usuari.setUid("uid");
		assertEquals("uid", usuari.getUid());

		usuari.setNomUsuari("nomUsuari");
		assertEquals("nomUsuari", usuari.getNomUsuari());

		usuari.sethContrasenya("hContrasenya");
		assertEquals("hContrasenya", usuari.gethContrasenya());

		usuari.setMaxPuntuacio(10);
		assertEquals(Integer.valueOf(10), usuari.getMaxPuntuacio());

		usuari.setNumPartidesGuanyades(2);
		assertEquals(Integer.valueOf(2), usuari.getNumPartidesGuanyades());

		usuari.setNumPartidesPerdudes(1);
		assertEquals(Integer.valueOf(1), usuari.getNumPartidesPerdudes());
	}
}