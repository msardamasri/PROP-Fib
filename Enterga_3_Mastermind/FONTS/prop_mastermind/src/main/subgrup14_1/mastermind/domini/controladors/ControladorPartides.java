package main.subgrup14_1.mastermind.domini.controladors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.domini.models.Partida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPersistencia;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.persistencia.ControladorPersistencia;
import main.subgrup14_1.mastermind.utils.InformacioPartida;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorPartides {
	
	private static ControladorPartides controladorPartides;
	private Map<String, Partida> partides;
	private ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
	private final String path = "partides.prop";
	/**
	 * Constructora del controlador
	 * @throws ExcepcioPersistencia Si hi ha un error llegint de disc
	 */
	public ControladorPartides() throws ExcepcioPersistencia {
		super();
		this.partides = new HashMap<String, Partida>();
		actualitzaPartides();
	}
	/**
	 * Obtenir instancia seguint el patro singleton
	 * @return instancia del controlador
	 * @throws ExcepcioPersistencia Si hi ha un error llegint de disc
	 */
	public static ControladorPartides getInstance() throws ExcepcioPersistencia   {
		if (controladorPartides == null) controladorPartides = new ControladorPartides();
		return controladorPartides;
	}
	/**
	 * Inicialitza les dades amb informacio de la capa de persistencia
	 * @throws ExcepcioPersistencia Si hi ha un error llegint de disc
	*/
	public void actualitzaPartides() throws ExcepcioPersistencia {
		this.partides = (Map<String, Partida>) this.controladorPersistencia.llegeix(this.path);
	}
	/**
	 * Comprova parametres i crea un partida
	 * @param uid Identificador del jugador
	 * @param r Rol del jugador
	 * @param d Dificultat de la partida
	 * @return Retorna la partida creada
	 */
	public Partida crearPartida(String uid, Rol r, Dificultat d) {
		String uidPartida = Utils.generateUID();
 		Partida p = new Partida(uidPartida, uid, d, r);
		this.partides.put(p.getInfoPartida().getUidPartida(), p);
		return p;
	}
	/**
	 * Esborra una partida
	 * @param uid identificador de la partida
	 * @throws ExcepcioPersistencia Si el fitxer no existeix o hi ha un problema a l'escriure en ell
	 */
	public void esborrarPartida(String uid) throws ExcepcioPersistencia {
		this.partides.remove(uid);
		this.controladorPersistencia.escriu(this.partides, path);
	}
	/**
	 * Obte partida amb uid
	 * @param uidPartida Identificador de la partida
	 * @return Retorna la partida amb identificador uidPartida
	 * @throws ExcepcioPartida Si l'identificador no es valid
	 */
	public Partida obtePartida(String uidPartida) throws ExcepcioPartida {
		if (uidPartida == null || uidPartida.isEmpty()) throw new ExcepcioPartida("L'identificador de la partida no es valid");
		
		Partida p = this.partides.get(uidPartida);
		
		if (p == null) throw new ExcepcioPartida("La partida amb identificador " + uidPartida + " no existeix");
		
		return p;
	}
	/**
	 * Obte informacio de les partides d'un usuari
	 * @param uid Identificador de l'usuari
	 * @return Retorna una llista d'InformacioPartida amb la informacio de les partides no acabades de l'usuari
	 * @throws ExcepcioPartida Si no hi ha cappartida pendent de ser acabada
	 */
	public List<InformacioPartida> llistaPartidesUsuari(String uid) throws ExcepcioPartida {
		List<InformacioPartida> llista = new ArrayList<InformacioPartida>();
		for (Entry<String, Partida> e : this.partides.entrySet()) {
			if (!e.getValue().getAcabada() && e.getValue().getInfoPartida().getUidUsuari().equals(uid)) {
				llista.add(
						new InformacioPartida(
								e.getValue().getInfoPartida().getUidPartida(),
								e.getValue().getInfoPartida().getDataInici(),
								e.getValue().getPuntuacio(),
								e.getValue().getInfoPartida().getDificultat()
								)
						);
			}
		}
		if (llista.isEmpty()) throw new ExcepcioPartida("No existeix cap partida pendent de ser acabada per a aquest usuari");
		return llista;
	}
	/**
	 * Guarda la partida en memoria i persistencia
	 * @param p Partida a guardar
	 * @throws ExcepcioPersistencia Si el fitxer no existeix o hi ha un problema a l'escriure en ell
	 */
	public void guardaPartida(Partida p) throws ExcepcioPersistencia {
		this.partides.put(p.getInfoPartida().getUidPartida(), p);
		this.controladorPersistencia.escriu(this.partides, path);
	}
}
