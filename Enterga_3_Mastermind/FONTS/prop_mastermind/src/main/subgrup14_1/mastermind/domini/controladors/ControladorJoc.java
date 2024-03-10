package main.subgrup14_1.mastermind.domini.controladors;

import java.util.List;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.domini.models.Codebreaker;
import main.subgrup14_1.mastermind.domini.models.CodebreakerMaquina;
import main.subgrup14_1.mastermind.domini.models.Codemaker;
import main.subgrup14_1.mastermind.domini.models.Partida;
import main.subgrup14_1.mastermind.domini.models.Usuari;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPersistencia;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.Pair;
import main.subgrup14_1.mastermind.utils.PartidaEnJoc;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorJoc {
	
	private static ControladorJoc controladorJoc;
	private Codemaker codemaker;
	private Codebreaker codebreaker;
	private Partida partidaCarregada;
	private Usuari usuariCarregat;
	/**
	 * Constructora buida
	 */
	public ControladorJoc() {
		super();
	}
	/**
	 * Obtenir instancia seguint el patro singleton
	 * @return instancia del controlador
	 */
	public static ControladorJoc getInstance() {
		if (controladorJoc == null) controladorJoc = new ControladorJoc();
		return controladorJoc;
	}
	/**
	 * Invalida l'usuari carregat
	 */
	public void esborrarUsuari() {
		this.usuariCarregat = null;
	}
	/**
	 * Comprova si hi ha un usuari carregat
	 * @return Retorna cert si hi ha un usuari carregat, fals si no
	 */
	public Boolean usuariCarregat() {
		return this.usuariCarregat != null;
	}
	/**
	 * Obtenir l'identificador de l'usuari carregat
	 * @return Retorna l'identificador de l'usuari carregat
	 * @throws ExcepcioUsuari Si no hi ha cap usuari carregat
	 */
	public String uidUsuari() throws ExcepcioUsuari {
		if (!usuariCarregat()) throw new ExcepcioUsuari("No hi ha cap usuari carregat");
			
		return this.usuariCarregat.getUid();
	}
	/**
	 * Obtenir el nom d'usuari de l'usuari carregat
	 * @return Retorna el nom d'usuari de l'usuari carregat
	 * @throws ExcepcioUsuari Si no hi ha cap usuari carregat
	 */
	public String usernameUsuari() throws ExcepcioUsuari {
		if (!usuariCarregat()) throw new ExcepcioUsuari("No hi ha cap usuari carregat");
		
		return this.usuariCarregat.getNomUsuari();
	}
	/**
	 * Carrega un usuari
	 * @param usuari Usuari a carregar
	 */
	public void carregarUsuari(Usuari usuari) {
		this.usuariCarregat = usuari;
	}
	/**
	 * Comprova si hi ha una partida carregada
	 * @return Retorna cert si hi ha una partida carregada, fals si no
	 */
	public Boolean partidaCarregada() {
		return this.partidaCarregada != null;
	}
	/**
	 * Comprova si la maquina te el rol de CODEMAKER
	 * @return Retorna cert si la maquina te el rol de CODEMAKER
	 */
	public Boolean esMaquinaCodemaker() {
		return this.partidaCarregada.esMaquinaCodemaker();
	}
	/**
	 * Abandona la partida, invalidant la partida i els jugadors
	 */
	public void abandonarPartida() {
		this.partidaCarregada = null;
	}
	/**
	 * Carrega una partida i instancia els jugadors segons el rol escollit
	 * @param p Partida a carregar
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 * @return Informacio de la partida en joc
	 */
	public PartidaEnJoc carregarPartida(Partida p) throws ExcepcioPartida {
		this.partidaCarregada = p;
		if (this.partidaCarregada.getInfoPartida().getRol() == Rol.CODEMAKER) {
			Integer algoritme = 0;
			if (this.partidaCarregada.getInfoPartida().getDificultat() != Dificultat.FACIL) algoritme = 1;
			this.codemaker = new Codemaker();
			this.codebreaker = new CodebreakerMaquina(this.partidaCarregada.getInfoPartida(), algoritme);
		}
		else {
			this.codemaker = new Codemaker();
			this.codebreaker = new Codebreaker();
			if (this.partidaCarregada.getNumTorn() == 0) return tornCodemakerMaquina();
		}
		return infoPartidaEnJoc();
	}
	/**
	 * Comprova si el codi compleix la dificultat de la partida
	 * @param codi Codi a comprovar
	 * @param dificultat Dificultat de la partida
	 * @return Retorna cert si el codi segueix les normes de la dificultat i fals si no
	 */
	private Boolean codiDificultatValid(List<Integer> codi, Dificultat dificultat) {
		for (int i = 0; i < 4; ++i) {
			Integer color = codi.get(i);
			if (color < 0) return false;
			if (dificultat == Dificultat.FACIL) {
				if (color > 3) return false;
				for (Integer j = 0; j < 4; ++j) {
					if (i != j && codi.get(i) == codi.get(j)) return false;
				}
			}
			else if (dificultat == Dificultat.INTERMIG) {
				if (color > 4) return false;
			}
			else {
				if (color > 5) return false;
			}
		}
		return true;
	}
	/**
	 * Obte informacio de l'usuari carregat
	 * @return Retorna informacio de l'usuari carregat
	 * @throws ExcepcioUsuari Si no hi ha cap usuari carregat
	 */
	public InformacioUsuari informacioUsuariCarregat() throws ExcepcioUsuari {
		if (!usuariCarregat()) throw new ExcepcioUsuari("No hi ha cap usuari carregat");
		
		return new InformacioUsuari(this.usuariCarregat.getUid(), this.usuariCarregat.getNomUsuari(), this.usuariCarregat.getMaxPuntuacio(), this.usuariCarregat.numPartidesTotals());
	}
	/**
	 * Comprova parametres i inicia la partida
	 * @return Retorna informacio de l'estat de la partida en joc
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 */
	public PartidaEnJoc iniciarPartida() throws ExcepcioPartida {
		if (!partidaCarregada()) throw new ExcepcioPartida("No hi ha cap partida carregada");
		
		if (this.partidaCarregada.esMaquinaCodemaker()) return tornCodemakerMaquina();
		else return infoPartidaEnJoc();
	}
	/**
	 * Obte el codi secret
	 * @return Retorna el codi secret de la partida
	 * @throws ExcepcioPartida si no hi ha cap partida carregada
	 */
	public List<Integer> codiJoc() throws ExcepcioPartida {
		if (!partidaCarregada()) throw new ExcepcioPartida("No hi ha cap partida carregada");
		
		return this.partidaCarregada.getInfoPartida().getCodiSecret();
	}
	/**
	 * Comprova parametres i inicialitza el codi secret proporcionat per l'usuari
	 * @param codiSecret Codi secret
	 * @return Retorna informacio de l'estat de la partida en joc
	 * @throws Exception Si no hi ha cap partida carregada, si el jugador no te el rol de CODEMAKER, si el codiSecret no es valid o si el codi no correspon a la dificultat de la partida
	 */
	public PartidaEnJoc tornCodemakerHuma(List<Integer> codiSecret) throws Exception {
		if (!partidaCarregada()) throw new ExcepcioPartida("No hi ha cap partida carregada");
		
		if (esMaquinaCodemaker()) throw new ExcepcioPartida("El jugador no te el rol de CODEMAKER");
		
		if (codiSecret == null || codiSecret.size() != 4) throw new ExcepcioPartida("El codi secret no es valid");
		
		Dificultat dificultat = this.partidaCarregada.getInfoPartida().getDificultat();
		PartidaEnJoc partidaEnJoc = infoPartidaEnJoc();
		if (codiDificultatValid(codiSecret, dificultat)) {
			this.partidaCarregada.inicialitzarCodiSecret(codiSecret);
			List<List<Integer>> intents = this.codebreaker.solve(codiSecret);
			for (Integer i = 0; i < intents.size(); ++i) {
				List<Integer> intent = intents.get(i);
				Pair<Integer, Integer> correccio = codemaker.validarCodi(intent, this.partidaCarregada.getInfoPartida().getCodiSecret());
				this.partidaCarregada.intentarCodi(intent);
				this.partidaCarregada.corretgirCodi(correccio);
				this.partidaCarregada.incrementaTorn();
				if (i < intents.size()-1) partidaEnJoc = infoPartidaEnJoc();
			}
			this.partidaCarregada.setAcabada(true);
			partidaEnJoc = infoPartidaEnJoc();
		}
		else {
			if (dificultat == Dificultat.FACIL) throw new ExcepcioPartida("En dificultat facil el codi no pot contenir colors repetits");
			throw new ExcepcioPartida("No es possible jugar amb aquesta combinacio de colors. Si us plau, torna-la a introduir.");
		}
		
		return partidaEnJoc;
	}
	/**
	 * La maquina inicialitza el codi secret 
	 * @return Retorna informacio de l'estat de la partida en joc
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 */
	public PartidaEnJoc tornCodemakerMaquina() throws ExcepcioPartida {
		if (!partidaCarregada()) throw new ExcepcioPartida("No hi ha cap partida carregada");
		
		List<Integer> codiSecret = this.codemaker.generarCodi(this.partidaCarregada.getInfoPartida().getDificultat());
		this.partidaCarregada.inicialitzarCodiSecret(codiSecret);
		return infoPartidaEnJoc();
	}
	/**
	 * Comprova parametres i realiza un intent per a desxifrar el codi secret per part de l'usuari
	 * @param codi Codi a intentar
	 * @return Retorna informacio de l'estat de la partida en joc
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada, el jugador no te el rol de CODEBREAKER o el codi no es valid
	 */
	public PartidaEnJoc tornCodebreakerHuma(List<Integer> codi) throws ExcepcioPartida {
		if (!partidaCarregada()) throw new ExcepcioPartida("No hi ha cap partida carregada");
		
		if (!esMaquinaCodemaker()) throw new ExcepcioPartida("El jugador no te el rol de CODEBREAKER");
		
		if (codi == null || codi.size() != 4) throw new ExcepcioPartida("El codi no es valid");
		
		Dificultat dificultat = this.partidaCarregada.getInfoPartida().getDificultat();
		if (!codiDificultatValid(codi, dificultat)) {
			if (dificultat == Dificultat.FACIL) throw new ExcepcioPartida("En dificultat facil el codi no pot contenir colors repetits");
			throw new ExcepcioPartida("No es possible jugar amb aquesta combinacio de colors. Si us plau, torna-la a introduir.");
		}

		this.partidaCarregada.intentarCodi(codi);
		
		Pair<Integer, Integer> correccio = this.codemaker.validarCodi(codi, codiJoc());

		this.partidaCarregada.corretgirCodi(correccio);
		
		this.partidaCarregada.incrementaTorn();
		
		if (dificultat == Dificultat.FACIL) this.partidaCarregada.restaPuntuacio(100);
		else if (dificultat == Dificultat.INTERMIG) this.partidaCarregada.restaPuntuacio(200);
		else this.partidaCarregada.restaPuntuacio(300);
		
		if (correccio.getR() == 4) this.partidaCarregada.fi();
		else {
			Integer maxTorns = this.partidaCarregada.getInfoPartida().getTotalTorns();
			if (this.partidaCarregada.getNumTorn() == maxTorns) this.partidaCarregada.fi();
		}
		return infoPartidaEnJoc();
	}
	/**
	 * Comprova si la partida ha finalitzat i obte informacio de la partida en joc
	 * @return Retorna informacio de l'estat de la partida en joc
	 * @throws ExcepcioPartida si no hi ha cap partida carregada 
	 */
	public PartidaEnJoc infoPartidaEnJoc() throws ExcepcioPartida {
		if (!partidaCarregada()) throw new ExcepcioPartida("No hi ha cap partida carregada");
		
		Dificultat dif = this.partidaCarregada.getInfoPartida().getDificultat();
		if (!this.partidaCarregada.getIntents().isEmpty() && this.partidaCarregada.getInfoPartida().getRol() == Rol.CODEMAKER) {
			if (dif == Dificultat.FACIL) this.partidaCarregada.setPuntuacio(300*this.partidaCarregada.getNumTorn());
			else if (dif == Dificultat.INTERMIG) this.partidaCarregada.setPuntuacio(200*this.partidaCarregada.getNumTorn());
			else this.partidaCarregada.setPuntuacio(100*this.partidaCarregada.getNumTorn());
		}
		
		if (this.partidaCarregada.getAcabada()) {
			Boolean guanyat = false;
			Integer darreraCorreccio = this.partidaCarregada.getCorreccions().size();
			if (this.partidaCarregada.getInfoPartida().getRol() == Rol.CODEBREAKER) {
				if (this.partidaCarregada.getCorreccions().get(darreraCorreccio - 1).getR() == 4) guanyat = true;
			}
			else {
				if (this.partidaCarregada.getCorreccions().get(darreraCorreccio - 1).getR() != 4) guanyat = true;
			}
			
			Integer puntuacio = this.partidaCarregada.getPuntuacio();
			this.usuariCarregat.actualitzaEstadistiques(guanyat, puntuacio);
		}
		
		return new PartidaEnJoc(
				this.partidaCarregada.getPuntuacio(),
				this.partidaCarregada.getIntents(),
				this.partidaCarregada.getCorreccions(),
				this.partidaCarregada.getAcabada(),
				this.partidaCarregada.getInfoPartida().getDificultat()
				);
	}
	/**
	 * Usa pista, decrementant la puntuacio de la partida
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 */
	public void usaPista() throws ExcepcioPartida {
		if (!partidaCarregada()) throw new ExcepcioPartida("No hi ha cap partida carregada");
		
		Dificultat dif = this.partidaCarregada.getInfoPartida().getDificultat();
		if (dif == Dificultat.FACIL) this.partidaCarregada.restaPuntuacio(400);
		else if (dif == Dificultat.INTERMIG) this.partidaCarregada.restaPuntuacio(700);
		else this.partidaCarregada.restaPuntuacio(900);
	}
	/**
	 * Obte la partida carregada
	 * @return Retorna la partida que es troba carregada
	 * @throws ExcepcioPartida Si no hi ha cap partida carregada
	 */
	public Partida recuperarPartidaCarregada() throws ExcepcioPartida {
		if (!partidaCarregada()) throw new ExcepcioPartida("No hi ha cap partida carregada");
		
		return this.partidaCarregada;
	}
	/**
	 * Obte el perfil d'usuari carregat
	 * @return Retorna l'usuari carregat
	 * @throws ExcepcioUsuari si no hi ha cap usuari carregat
	 */
	public Usuari recuperarUsuariCarregat() throws ExcepcioUsuari {
		if (!usuariCarregat()) throw new ExcepcioUsuari("No hi ha cap usuari carregat");
		
		return this.usuariCarregat;
	}	
}
