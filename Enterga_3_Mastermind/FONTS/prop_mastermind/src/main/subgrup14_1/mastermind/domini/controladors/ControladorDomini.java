package main.subgrup14_1.mastermind.domini.controladors;

import java.util.List;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.domini.models.Partida;
import main.subgrup14_1.mastermind.domini.models.Usuari;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPersistencia;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.utils.InformacioPartida;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.PartidaEnJoc;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorDomini {
	
	private static ControladorDomini controladorDomini;
	/**
	 * Constructor del controlador
	 */
	private ControladorDomini()   {
	}
	/**
	 * Obtenir instancia seguint el patro singleton
	 * @return instancia del controlador
	 */
	public static ControladorDomini getInstance()   {
		if (controladorDomini == null) controladorDomini = new ControladorDomini();
		return controladorDomini;
	}
	/**
	 * Comprova si hi ha un usari carregat
	 * @return Retorna cert si hi ha un usuari carregat o fals si no
	 */
	public Boolean usuariCarregat()   {
		return ControladorJoc.getInstance().usuariCarregat();
	}
	/**
	 * Crea un usuari
	 * @param username Nom de l'usuari
	 * @param pwd Contrasenya de l'usuari
	 * @throws ExcepcioUsuari Si algun dels parametres no es valid
	 * @throws ExcepcioPersistencia Si hi ha hagut un problema escrivint a disc
	 */
	public void crearUsuari(String username, String pwd) throws ExcepcioPersistencia, ExcepcioUsuari {
		ControladorUsuaris.getInstance().crearUsuari(username, pwd);
	}
	/**
	 * Carrega un usuari
	 * @param uid Nom de l'usuari
	 * @param pwd Contrasenya de l'usuari
	 * @throws ExcepcioUsuari Si l'identificador o la contrasenya no son valids
	 * @throws ExcepcioPersistencia Si hi ha hagut un problema llegint de disc
	 */
	public void carregarUsuari(String uid, String pwd) throws ExcepcioUsuari, ExcepcioPersistencia {
		Usuari usuari = ControladorUsuaris.getInstance().retornaUsuariContrasenya(uid, pwd);
		
		ControladorJoc.getInstance().carregarUsuari(usuari);
	}
	/**
	 * Esborra un usuari
	 * @param uid Identificador de l'usuari
	 * @param pwd Contrasenya de l'usuari
	 * @throws ExcepcioUsuari Si l'identificador o la contrasenya no son valids
	 * @throws ExcepcioPersistencia Si hi ha hagut un problema llegint de disc
	 */
	public void esborrarUsuari(String uid, String pwd) throws ExcepcioPersistencia, ExcepcioUsuari {	
		ControladorUsuaris.getInstance().esborrarUsuari(uid, pwd);

		if (ControladorJoc.getInstance().usuariCarregat() && ControladorJoc.getInstance().uidUsuari().equals(uid)) {
			ControladorJoc.getInstance().esborrarUsuari();
			ControladorJoc.getInstance().abandonarPartida();
		}
	}
	/**
	 * Obtenir informacio de tots els usuari
	 * @return Retorna una llista amb informacio de tots els usuari (uid, username, maxPuntuacio...)
	 * @throws ExcepcioUsuari Si no hi ha cap usuari registrat
	 * @throws ExcepcioPersistencia Si hi ha hagut un problema llegint de disc
	 */
	public List<InformacioUsuari> llistaUsuaris() throws ExcepcioUsuari, ExcepcioPersistencia {
		return ControladorUsuaris.getInstance().llistaUsuaris();
	}
	/**
	 * Obte informacio dels usuaris ordenada segons la maxima puntuacio
	 * @return Llista d'InformacioUsuaris de tots els usuaris ordenada per maxima puntuacio
	 * @throws ExcepcioUsuari Si no hi ha cap usuari registrat
	 * @throws ExcepcioPersistencia Si hi ha hagut un problema llegint de disc
	 */
	public List<InformacioUsuari> llistaRanquingUsuaris() throws ExcepcioUsuari, ExcepcioPersistencia {
		return ControladorUsuaris.getInstance().llistaRanquingUsuaris();
	}
	/**
	 * Descarrega l'usuari carregat (tanca la sessio)
	 */
	public void logout()   {
		ControladorJoc.getInstance().esborrarUsuari();
	}
	/**
	 * Guarda l'usuari carregat en memoria i persistencia
	 * @throws ExcepcioUsuari Si no hi ha cap usuari carregat
	 * @throws ExcepcioPersistencia Si hi ha hagut un problema llegint del disc
	 */
	public void guardarUsuari() throws ExcepcioUsuari, ExcepcioPersistencia {
		Usuari u = ControladorJoc.getInstance().recuperarUsuariCarregat();
		
		ControladorUsuaris.getInstance().guardarUsuari(u);
	}
	/**
	 * Obte informacio de l'usuari carregat
	 * @return Retorna informacio de l'usuari carregat
	 * @throws ExcepcioUsuari Si no hi ha cap usuari carregat
	 */
	public InformacioUsuari informacioUsuariCarregat() throws ExcepcioUsuari {
		return ControladorJoc.getInstance().informacioUsuariCarregat();
	}
	/**
	 * Comprova si hi ha una partida carregada
	 * @return Retorna cert si hi ha una partida carregada o fals si no
	 */
	public Boolean partidaActiva ()   {
		return ControladorJoc.getInstance().partidaCarregada();
	}
	/**
	 * Obte el codi secret
	 * @return Retorna el codi secret de la partida carregada
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 */
	public List<Integer> codiJoc() throws ExcepcioPartida {
		return ControladorJoc.getInstance().codiJoc();
	}
	/**
	 * Usa una pista
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 * @throws ExcepcioPersistencia Si hi ha algun problema escrivint a disc
	 */
	public void usaPista() throws ExcepcioPartida, ExcepcioPersistencia {
		ControladorJoc.getInstance().usaPista();
		
		Partida p = ControladorJoc.getInstance().recuperarPartidaCarregada();
		ControladorPartides.getInstance().guardaPartida(p);		
	}
	/**
	 * Comprova parametres i crea partida
	 * @param d Dificultat de la partida
	 * @param r Rol del jugador a la partida
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioUsuari Si no hi ha cap usuari carregat
	 * @throws ExcepcioPartida Si algun parametre no es valid
	 * @throws ExcepcioPersistencia Si hi ha algun problema escrivint a disc
	 */
	public PartidaEnJoc crearPartida(Dificultat d, Rol r) throws ExcepcioUsuari, ExcepcioPartida, ExcepcioPersistencia {
		if (!ControladorJoc.getInstance().usuariCarregat()) throw new ExcepcioUsuari("No hi ha cap usuari carregat");
		
		Partida p = ControladorPartides.getInstance().crearPartida(ControladorJoc.getInstance().uidUsuari(), r, d);
		if (p.getAcabada()) {
			guardarUsuari();
			guardarPartida();
		}
		return ControladorJoc.getInstance().carregarPartida(p);
	}
	/**
	 * Comprova parametres i carrega una partida
	 * @param uidPartida Identificador de la partida
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioUsuari Si no hi ha cap usuari carregat
	 * @throws ExcepcioPartida Si no existeix la partida
	 * @throws ExcepcioPersistencia Si hi ha algun problema llegint de disc
	 */
	public PartidaEnJoc carregarPartida(String uidPartida) throws ExcepcioPartida, ExcepcioPersistencia, ExcepcioUsuari {
		Partida p = ControladorPartides.getInstance().obtePartida(uidPartida);
		
		return ControladorJoc.getInstance().carregarPartida(p);
	}
	/**
	 * Guarda la partida en memoria i persistencia si hi ha una carregada
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 * @throws ExcepcioPersistencia Si hi ha algun problema escrivint a disc
	 */
	public void guardarPartida() throws ExcepcioPartida, ExcepcioPersistencia {
		Partida p = ControladorJoc.getInstance().recuperarPartidaCarregada();
		
		ControladorPartides.getInstance().guardaPartida(p);
	}
	/**
	 * Esborrar partida
	 * @param uidP Identificador de la partida
	 * @throws ExcepcioPersistencia Si hi ha algun problema escrivint a disc
	 */
	public void esborrarPartida(String uidP) throws ExcepcioPersistencia {
		ControladorPartides.getInstance().esborrarPartida(uidP);
	}
	/**
	 * Abandona la partida: invalida la partida carregada
	 */
	public void abandonarPartida() {
		ControladorJoc.getInstance().abandonarPartida();
	}
	/**
	 * Obtenir llista de partides no acabades d'un usuari
	 * @param uid Identificador de l'usuari
	 * @return Llista d'informacio de les partides no acabades per l'usuari amb identificador uid
	 * @throws ExcepcioUsuari Si no existeix l'usuari
	 * @throws ExcepcioPartida Si no hi ha partides per acabar per a aquest usuari
	 * @throws ExcepcioPersistencia Si hi ha algun problema llegint de disc
	 */
	public List<InformacioPartida> llistaPartidesUsuari(String uid) throws ExcepcioUsuari, ExcepcioPartida, ExcepcioPersistencia {
		if (uid == null || uid.isEmpty()) throw new ExcepcioUsuari("L'identificador d'usuari no es valid");
		
		if (!ControladorUsuaris.getInstance().existeixUsuari(uid)) throw new ExcepcioUsuari("L'usuari no existeix");
			
		return ControladorPartides.getInstance().llistaPartidesUsuari(uid);
	}
	/**
	 * Inicia la partida carregada
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 */
	public PartidaEnJoc iniciarPartida() throws ExcepcioPartida{
		return ControladorJoc.getInstance().iniciarPartida();
	}
	/**
	 * Inicialitza el codi secret de la partida
	 * @param codiSecret Codi secret
	 * @return Retorna informacio de l'estat de la partida
	 * @throws Exception Si el codi no es valid
	 */
	public PartidaEnJoc iniciarCodi(List<Integer> codiSecret) throws Exception {
		PartidaEnJoc p = ControladorJoc.getInstance().tornCodemakerHuma(codiSecret);
		if (p.getAcabada()) {
			guardarUsuari();
			guardarPartida();
		}
		return p;
	}
	/**
	 * Realitza un intent de trobar el codi secret
	 * @param codi Codi per a validar
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioPartida Si el codi no es valid
	 * @throws ExcepcioPersistencia Si hi ha un problema escrivint a disc
	 * @throws ExcepcioUsuari Si no hi ha cap usuari carregat
	 */
	public PartidaEnJoc intentarCodi(List<Integer> codi) throws ExcepcioPartida, ExcepcioPersistencia, ExcepcioUsuari {
		PartidaEnJoc p = ControladorJoc.getInstance().tornCodebreakerHuma(codi);
		if (p.getAcabada()) {
			guardarUsuari();
			guardarPartida();
		}
		return p;
	}
	/**
	 * Obtenir informacio de la partida en joc
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 */
	public PartidaEnJoc infoPartidaEnJoc() throws ExcepcioPartida  {
		return ControladorJoc.getInstance().infoPartidaEnJoc();
	}
}