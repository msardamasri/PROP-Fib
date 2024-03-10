package main.subgrup14_1.mastermind.domini.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class InfoPartida implements Serializable{

	private static final long serialVersionUID = 1;
	private String uidPartida;
	private String uidUsuari;
	private Dificultat dificultat;
	private Integer totalTorns;
	private LocalDateTime dataInici;
	private List<Integer> codiSecret;
	private Rol rol;
	
	/**
	 * Crea una nova instencia de la classe InfoPartida amb els paremetres especificats.
	 * @param uidUsuari   L'identificador de l'usuari associat a la partida.
	 * @param uidPartida  L'identificador de la partida.
	 * @param dificultat  La dificultat de la partida.
	 * @param rol         El rol de l'usuari en la partida.
	 */
	public InfoPartida(String uidUsuari, String uidPartida, Dificultat dificultat, Rol rol) {
		super();
		this.uidPartida = uidPartida;
		this.uidUsuari = uidUsuari;
		this.dificultat = dificultat;
		this.rol = rol;
		if (dificultat == Dificultat.FACIL) {
			this.totalTorns = 12;
		}
		else if (dificultat == Dificultat.INTERMIG) {
			this.totalTorns = 10;
		}
		else if (dificultat == Dificultat.DIFICIL) {
			this.totalTorns = 8;
		}
		this.dataInici = LocalDateTime.now();
		this.codiSecret = new ArrayList<Integer>();
	}
	/**
	 * Obte l'identificador de la partida.
	 * @return L'identificador de la partida.
	 */
	public String getUidPartida() {
		return uidPartida;
	}
	/**
	 * Estableix l'identificador de la partida.
	 * @param uidPartida L'identificador de la partida.
	 */
	public void setUidPartida(String uidPartida) {
		this.uidPartida = uidPartida;
	}
	/**
	 * Obte la dificultat de la partida.
	 * @return La dificultat de la partida.
	 */
	public Dificultat getDificultat() {
		return dificultat;
	}
	/**
	 * Estableix la dificultat de la partida.
	 * @param dificultat La dificultat de la partida.
	 */
	public void setDificultat(Dificultat dificultat) {
		this.dificultat = dificultat;
	}
	/**
	 * Obte el nombre total de torns de la partida.
	 * @return El nombre total de torns de la partida.
	 */
	public Integer getTotalTorns() {
		return totalTorns;
	}
	/**
	 * Estableix el nombre total de torns de la partida.
	 * @param totalTorns El nombre total de torns de la partida.
	 */
	public void setTotalTorns(Integer totalTorns) {
		this.totalTorns = totalTorns;
	}
	/**
	 * Obte la data d'inici de la partida.
	 * @return La data d'inici de la partida.
	 */
	public LocalDateTime getDataInici() {
		return dataInici;
	}
	/**
	 * Estableix la data d'inici de la partida.
	 * @param dataInici La data d'inici de la partida.
	 */
	public void setDataInici(LocalDateTime dataInici) {
		this.dataInici = dataInici;
	}
	/**
	 * Obte el codi secret de la partida.
	 * @return El codi secret de la partida.
	 */
	public List<Integer> getCodiSecret() {
		return codiSecret;
	}
	/**
	 * Estableix el codi secret de la partida. 
	 * @param codiSecret2 El codi secret de la partida.
	 */
	public void setCodiSecret(List<Integer> codiSecret2) {
		this.codiSecret = codiSecret2;
	}
	/**
	 * Obte l'identificador de l'usuari.
	 * @return L'identificador de l'usuari.
	 */
	public String getUidUsuari() {
		return uidUsuari;
	}
	/**
	 * Estableix l'identificador de l'usuari.
	 * @param uidUsuari L'identificador de l'usuari.
	 */
	public void setUidUsuari(String uidUsuari) {
	    this.uidUsuari = uidUsuari;
	}
	/**
	 * Obte el rol de l'usuari en la partida.
	 * @return El rol de l'usuari en la partida.
	 */
	public Rol getRol() {
	    return rol;
	}
	/**
	 * Estableix el rol de l'usuari en la partida.
	 * @param rol El rol de l'usuari en la partida.
	 */
	public void setRol(Rol rol) {
	    this.rol = rol;
	}
}
