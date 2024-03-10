package main.subgrup14_1.mastermind.presentacio.controladors;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import main.subgrup14_1.mastermind.domini.controladors.ControladorDomini;
import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPersistencia;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.presentacio.vistes.AlertaPopUp;
import main.subgrup14_1.mastermind.presentacio.vistes.Joc;
import main.subgrup14_1.mastermind.presentacio.vistes.MenuConfigPartida;
import main.subgrup14_1.mastermind.presentacio.vistes.MenuPartida;
import main.subgrup14_1.mastermind.presentacio.vistes.MenuPausa;
import main.subgrup14_1.mastermind.presentacio.vistes.MenuSeleccioPartidaGuardada;
import main.subgrup14_1.mastermind.presentacio.vistes.MenuSeleccioUsuari;
import main.subgrup14_1.mastermind.presentacio.vistes.MenuUsuari;
import main.subgrup14_1.mastermind.presentacio.vistes.SeleccioCodiSecret;
import main.subgrup14_1.mastermind.utils.InformacioPartida;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.PartidaEnJoc;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorPresentacio {

	public static ControladorPresentacio controladorPresentacio;
	/**
	 * Obtenir instancia seguint el patro singleton
	 * @return instancia del controlador
	 */
	public static ControladorPresentacio getInstance() {
		if (controladorPresentacio == null) controladorPresentacio = new ControladorPresentacio();
		return controladorPresentacio;
	}
	/**
	 * Instancia la vista de MenuSeleccioUsuari
	 */
	public void botoIniciarSessio() {
		MenuSeleccioUsuari dialog = new MenuSeleccioUsuari();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Crea un usuari
	 * @param nom Nom d'usuari
	 * @param pwd Contrasenya
	 * @param pwd2 Confirmacio de la contrasenya
	 * @return Indicador d'errors
	 */
	public Boolean crearUsuari(String nom, String pwd, String pwd2) {
		if (!pwd.equals(pwd2)) {
			showPopUp("Les contrasenyes no coincideixen!");
			return false;
		}
		try {
			ControladorDomini.getInstance().crearUsuari(nom, pwd);
			showPopUp("Usuari registrat correctament!");
			return true;
		}
		catch (ExcepcioUsuari | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
			return false;
		}
	}

	/**
	 * Inicia sessio i fa control d'errors
	 * @param uid Identificador de l'usuar
	 * @param pwd Contrasenya de l'usuari
	 * @return Boolea indicant si no hi ha hagut cap problema
	 */
	public Boolean iniciSessio(String uid, String pwd) {
		try {
			ControladorDomini.getInstance().carregarUsuari(uid, pwd);
			showPopUp("Usuari registrat correctament!");
			return true;
		}
		catch (ExcepcioUsuari | ExcepcioPersistencia e) {
			return false;
		}
	}
	/**
	 * Instancia la vista MenuSeleccioPartidaGuardada
	 */
	public void botoCarregarPartida() {
		MenuSeleccioPartidaGuardada dialog = new MenuSeleccioPartidaGuardada();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista AlertaPopUp
	 * @param text a mostrar en el popup
	 */
	public void showPopUp(String text) {
		AlertaPopUp dialog = new AlertaPopUp(text);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista MenuConfigPartida
	 */
	public void botoCrearPartida() {
		MenuConfigPartida dialog = new MenuConfigPartida();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista MenuSeleccioUsuari
	 */
	public void showMenuSeleccioUsuari() {
		MenuSeleccioUsuari dialog = new MenuSeleccioUsuari();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista MenuUsuari
	 */
	public void showMenuUsuari() {
		MenuUsuari dialog = new MenuUsuari();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista MenuPartida
	 */
	public void showMenuPartida() {
		MenuPartida dialog = new MenuPartida();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista Joc
	 * @param codebreaker indicador de si es juga com a codebreaker
	 */
	public void showJoc(Boolean codebreaker) {
		Joc dialog = new Joc(codebreaker);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista SeleccioCodiSecret
	 */
	public void showSeleccioCodiSecret() {
		SeleccioCodiSecret dialog = new SeleccioCodiSecret();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista MenuPausa
	 * @param acabada Indicador de si la partida esta acabada
	 */
	public void showMenuPausa(Boolean acabada) {
		MenuPausa dialog = new MenuPausa(acabada);
    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Instancia la vista MenuSeleccioPartidaGuardada
	 */
	public void showSeleccioPartidaGuardada() {
		MenuSeleccioPartidaGuardada dialog = new MenuSeleccioPartidaGuardada();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Carrega partida i instancia la vista de Joc
	 * @param uidP Identificador de la partida
	 */
	public void carregarPartida(String uidP) {
		try {
			ControladorDomini.getInstance().carregarPartida(uidP);
			Joc dialog = new Joc(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (ExcepcioPartida | ExcepcioUsuari | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
		}
	}
	/**
	 * Obte el codi secret del joc
	 * @return Codi secret
	 */
	public List<Integer> codiJoc() {
		try {
			return ControladorDomini.getInstance().codiJoc();
		} catch (ExcepcioPartida e) {
			showPopUp(e.getMessage());
			return new ArrayList<Integer>();
		}
	}
	/**
	 * Usa una pista
	 */
	public void usaPista() {
		try {
			ControladorDomini.getInstance().usaPista();
		} catch (ExcepcioPartida | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
		}
	}
	/**
	 * Obte informacio de la partida en joc
	 * @return Informacio sobre la partida en joc
	 */
	public PartidaEnJoc infoPartidaEnJoc() {
		try {
			return ControladorDomini.getInstance().infoPartidaEnJoc();
		} catch (ExcepcioPartida e) {
			showPopUp(e.getMessage());
			return null;
		}
	}
	/**
	 * Intentar un codi
	 * @param codi Codi a intentar
	 * @return Estat de la partida en joc
	 */
	public PartidaEnJoc intentarCodi(List<Integer> codi) {
		try {
			return ControladorDomini.getInstance().intentarCodi(codi);
		} catch (ExcepcioPartida | ExcepcioUsuari | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
			return null;
		}
	}
	/**
	 * Crea una partida
	 * @param dif Dificultat de la partida
	 * @param rol Rol del jugador
	 * @return Estat de la partida en joc
	 */
	public PartidaEnJoc crearPartida(Dificultat dif, Rol rol) {
		try {
			return ControladorDomini.getInstance().crearPartida(dif, rol);
		} catch (ExcepcioPartida | ExcepcioUsuari | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
			return null;
		}
	}
	/**
	 * Inicia el codi secret
	 * @param codi Codi secret per la partida
	 * @return Estat de la partida en joc
	 */
	public PartidaEnJoc iniciarCodi(List<Integer> codi) {
		try {
			return ControladorDomini.getInstance().iniciarCodi(codi);
		} catch (Exception e) {
			showPopUp(e.getMessage());
			return null;
		}
	}
	/**
	 * Obte informacio de l'usuari carregat
	 * @return Informacio de l'usuari carregat
	 */
	public InformacioUsuari informacioUsuariCarregat() {
		try {
			return ControladorDomini.getInstance().informacioUsuariCarregat();
		} catch (ExcepcioUsuari e) {
			showPopUp(e.getMessage());
			return null;
		}
	}
	/**
	 * Llista les partides de l'usuari
	 * @param uidP Identificador de l'usuari
	 * @return Llista amb les partides no acabades de l'usuari
	 */
	public List<InformacioPartida> llistaPartidesUsuari(String uidP) {
		try {
			return ControladorDomini.getInstance().llistaPartidesUsuari(uidP);
		} catch (ExcepcioPartida | ExcepcioUsuari | ExcepcioPersistencia e) {
			return new ArrayList<InformacioPartida>();
		}
	}
	/**
	 * Obte llista dels usuaris ordenada per puntuacio
	 * @return Llista dels usuaris ordenada per puntuacio
	 */
	public List<InformacioUsuari> llistaRanquingUsuaris() {
		try {
			return ControladorDomini.getInstance().llistaRanquingUsuaris();
		} catch (ExcepcioUsuari | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
			return new ArrayList<InformacioUsuari>();
		}
	}
	/**
	 * Tanca la sessio
	 */
	public void logout() {
		ControladorDomini.getInstance().logout();
	}
	/**
	 * Guarda la partida
	 */
	public void guardarPartida() {
		try {
			ControladorDomini.getInstance().guardarPartida();
		} catch (ExcepcioPartida | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
		}
	}
	/**
	 * Abandona la partida sense guardar
	 */
	public void abandonarPartida() {
		ControladorDomini.getInstance().abandonarPartida();
	}
	/**
	 * Esborra la partida
	 * @param uidP Identificador de la partida
	 */
	public void esborrarPartida(String uidP) {
		try {
			ControladorDomini.getInstance().esborrarPartida(uidP);
		} catch (ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
		}
	}
	/**
	 * Llista els usuaris
	 * @return Llista dels usuaris existents 
	 */
	public List<InformacioUsuari> llistaUsuaris() {
		try {
			return ControladorDomini.getInstance().llistaUsuaris();
		} catch (ExcepcioUsuari | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
			return new ArrayList<InformacioUsuari>();
		}
	}
	/**
	 * Esborrar un usuari
	 * @param uid Identificador de l'usuari
	 * @param pwd Contrasenya de l'usuari
	 * @return Boolea indicant si hi ha hagut algun problema
	 */
	public Boolean esborrarUsuari(String uid, String pwd) {
		try {
			ControladorDomini.getInstance().esborrarUsuari(uid, pwd);
			return true;
		} catch (ExcepcioUsuari | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
			return false;
		}
	}
	/**
	 * Carrega un usuari
	 * @param uid Identificador de l'usuari
	 * @param pwd Contrasneya de l'usuari
	 * @return Boolean indicant si hi ha hagut algun problema
	 */
	public Boolean carregarUsuari(String uid, String pwd) {
		try {
			ControladorDomini.getInstance().carregarUsuari(uid, pwd);
			return true;
		} catch (ExcepcioUsuari | ExcepcioPersistencia e) {
			showPopUp(e.getMessage());
			return false;
		}
	}
	/**
	 * Crea l'applicacio
	 * @throws ExcepcioPartida Si hi ha hgaut un problema instanciant els elements
	 * @throws ExcepcioUsuari Si hi ha hgaut un problema instanciant els elements
	 */
	public static void initialize() throws ExcepcioUsuari, ExcepcioPartida{
		MenuUsuari dialog = new MenuUsuari();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
}
