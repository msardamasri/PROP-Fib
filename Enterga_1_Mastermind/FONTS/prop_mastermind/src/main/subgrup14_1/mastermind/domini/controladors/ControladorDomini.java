package main.subgrup14_1.mastermind.domini.controladors;

import java.util.List;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.domini.models.Partida;
import main.subgrup14_1.mastermind.domini.models.Usuari;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.utils.InformacioPartida;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.PartidaEnJoc;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorDomini {
	
	private ControladorPartides controladorPartides = new ControladorPartides();
	private ControladorUsuaris controladorUsuaris = new ControladorUsuaris();
	private ControladorJoc controladorJoc = new ControladorJoc();
	
	/**
	 * Comprova si hi ha un usari carregat
	 * @return Retorna cert si hi ha un usuari carregat o fals si no
	 */
	public Boolean usuariCarregat() {
		return this.controladorJoc.usuariCarregat();
	}
	
	/**
	 * Crea un usuari
	 * @param username Nom de l'usuari
	 * @param pwd Contrasenya de l'usuari
	 * @throws ExcepcioUsuari 
	 */
	public void crearUsuari(String username, String pwd) throws ExcepcioUsuari {
		this.controladorUsuaris.crearUsuari(username, pwd);
	}
	
	/**
	 * Carrega un usuari
	 * @param username Nom de l'usuari
	 * @param pwd Contrasenya de l'usuari
	 * @throws ExcepcioUsuari 
	 */
	public void carregarUsuari(String uid, String pwd) throws ExcepcioUsuari {
		Usuari usuari = this.controladorUsuaris.retornaUsuariContrasenya(uid, pwd);
		
		this.controladorJoc.carregarUsuari(usuari);
	}
	
	/**
	 * Esborra un usuari
	 * @param uid Identificador de l'usuari
	 * @param pwd Contrasenya de l'usuari
	 * @throws ExcepcioUsuari 
	 */
	public void esborrarUsuari(String uid, String pwd) throws ExcepcioUsuari {	
		this.controladorUsuaris.esborrarUsuari(uid, pwd);

		if (this.controladorJoc.uidUsuari().equals(uid)) {
			this.controladorJoc.esborrarUsuari();
			this.controladorJoc.abandonarPartida();
		}
	}
	
	/**
	 * Obtenir informacio de tots els usuari
	 * @return Retorna una llista amb informacio de tots els usuari (uid, username, maxPuntuacio...)
	 * @throws ExcepcioUsuari 
	 */
	public List<InformacioUsuari> llistaUsuaris() throws ExcepcioUsuari {
		return this.controladorUsuaris.llistaUsuaris();
	}
	
	/**
	 * Obte informacio dels usuaris ordenada segons la maxima puntuacio
	 * @return Llista d'InformacioUsuaris de tots els usuaris ordenada per maxima puntuacio
	 * @throws ExcepcioUsuari 
	 */
	public List<InformacioUsuari> llistaRanquingUsuaris() throws ExcepcioUsuari {
		return this.controladorUsuaris.llistaRanquingUsuaris();
	}
	
	/**
	 * Esborra l'usuari carregat (tanca la sessio)
	 */
	public void logout() {
		this.controladorJoc.esborrarUsuari();
	}
	
	/**
	 * Guarda l'usuari carregat en memoria i persistencia
	 * @throws ExcepcioUsuari
	 */
	public void guardarUsuari() throws ExcepcioUsuari {
		Usuari u = this.controladorJoc.recuperarUsuariCarregat();
		
		this.controladorUsuaris.guardarUsuari(u);
	}
	
	/**
	 * Obte informacio de l'usuari carregat
	 * @return Retorna informacio de l'usuari carregat
	 * @throws ExcepcioUsuari
	 */
	public InformacioUsuari informacioUsuariCarregat() throws ExcepcioUsuari {
		return this.controladorJoc.informacioUsuariCarregat();
	}

	/**
	 * Comprova si hi ha una partida carregada
	 * @return Retorna cert si hi ha una partida carregada o fals si no
	 */
	public Boolean partidaActiva () {
		return controladorJoc.partidaCarregada();
	}
	
	/**
	 * Comprova parametres i crea partida
	 * @param dif Dificultat de la partida
	 * @param rol Rol del jugador a la partida
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioPartida 
	 * @throws ExcepcioUsuari 
	 */
	public PartidaEnJoc crearPartida(Dificultat d, Rol r) throws ExcepcioPartida, ExcepcioUsuari {
		if (!this.controladorJoc.usuariCarregat()) throw new ExcepcioUsuari("No hi ha cap usuari carregat");
		
		Partida p = this.controladorPartides.crearPartida(this.controladorJoc.uidUsuari(), r, d);
		return this.controladorJoc.carregarPartida(p);
	}
	
	/**
	 * Comprova parametres i carrega una partida
	 * @param uidPartida Identificador de la partida
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioPartida 
	 */
	public PartidaEnJoc carregarPartida(String uidPartida) throws ExcepcioPartida {
		Partida p = this.controladorPartides.obtePartida(uidPartida);
		
		return this.controladorJoc.carregarPartida(p);
	}

	/**
	 * Guarda la partida en memoria i persistencia si hi ha una carregada
	 * @throws ExcepcioPartida 
	 */
	public void guardarPartida() throws ExcepcioPartida {
		Partida p = this.controladorJoc.recuperarPartidaCarregada();
		
		this.controladorPartides.guardaPartida(p);
	}

	/**
	 * Abandona la partida: invalida la partida carregada
	 */
	public void abandonarPartida() {
		this.controladorJoc.abandonarPartida();
	}

	/**
	 * Obtenir llista de partides no acabades d'un usuari
	 * @param uid Identificador de l'usuari
	 * @return Llista d'informacio de les partides no acabades per l'usuari amb identificador uid
	 * @throws ExcepcioUsuari 
	 * @throws ExcepcioPartida 
	 */
	public List<InformacioPartida> llistaPartidesUsuari(String uid) throws ExcepcioUsuari, ExcepcioPartida {
		if (uid == null || uid.isEmpty()) throw new ExcepcioUsuari("L'identificador d'usuari no es valid");
		
		if (!this.controladorUsuaris.existeixUsuari(uid)) throw new ExcepcioUsuari("L'usuari no existeix");
			
		return this.controladorPartides.llistaPartidesUsuari(uid);
	}
	
	/**
	 * Inicia la partida carregada
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioPartida 
	 */
	public PartidaEnJoc iniciarPartida() throws ExcepcioPartida {
		return this.controladorJoc.iniciarPartida();
	}
	
	/**
	 * Inicialitza el codi secret de la partida
	 * @param codiSecret Codi secret
	 * @return Retorna informacio de l'estat de la partida
	 * @throws Exception 
	 */
	public PartidaEnJoc iniciarCodi(List<Integer> codiSecret) throws Exception {
		return this.controladorJoc.tornCodemakerHuma(codiSecret);
	}
	
	/**
	 * Realitza un intent de trobar el codi secret
	 * @param codi Codi per a validar
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioPartida 
	 */
	public PartidaEnJoc intentarCodi(List<Integer> codi) throws ExcepcioPartida {
		return this.controladorJoc.tornCodebreakerHuma(codi);
	}
	
	/**
	 * Obtenir informacio de la partida en joc
	 * @return Retorna informacio de l'estat de la partida
	 * @throws ExcepcioPartida
	 */
	public PartidaEnJoc infoPartidaEnJoc() throws ExcepcioPartida {
		return this.controladorJoc.infoPartidaEnJoc();
	}
}