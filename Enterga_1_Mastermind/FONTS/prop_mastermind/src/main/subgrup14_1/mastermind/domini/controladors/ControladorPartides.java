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
import main.subgrup14_1.mastermind.utils.InformacioPartida;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorPartides {
	
	private Map<String, Partida> partides = new HashMap<String, Partida>();
	
	public ControladorPartides() {
		super();
	}
	
	/**
	 * Comprova parametres i crea un partida
	 * @param uid Identificador del jugador
	 * @param rol Rol del jugador
	 * @param dif Dificultat de la partida
	 * @return Retorna la partida creada
	 * @throws ExcepcioPartida
	 */
	public Partida crearPartida(String uid, Rol r, Dificultat d) {
		String uidPartida = Utils.generateUID();
 		Partida p = new Partida(uidPartida, uid, d, r);
		this.partides.put(p.getInfoPartida().getUidPartida(), p);
		return p;
	}
	
	/**
	 * Obte partida amb uid
	 * @param uidPartida Identificador de la partida
	 * @return Retorna la partida amb identificador uidPartida
	 * @throws ExcepcioPartida
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
	 * @throws ExcepcioPartida 
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
	 */
	public void guardaPartida(Partida p) {
		this.partides.put(p.getInfoPartida().getUidPartida(), p);
		// Guardar a capa persistencia
	}
}
