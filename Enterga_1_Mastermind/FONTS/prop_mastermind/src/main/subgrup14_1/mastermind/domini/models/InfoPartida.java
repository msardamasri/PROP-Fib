package main.subgrup14_1.mastermind.domini.models;

import java.io.Serializable;
import java.time.LocalDateTime;
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
	}
	public String getUidPartida() {
		return uidPartida;
	}
	public void setUidPartida(String uidPartida) {
		this.uidPartida = uidPartida;
	}
	public Dificultat getDificultat() {
		return dificultat;
	}
	public void setDificultat(Dificultat dificultat) {
		this.dificultat = dificultat;
	}
	public Integer getTotalTorns() {
		return totalTorns;
	}
	public void setTotalTorns(Integer totalTorns) {
		this.totalTorns = totalTorns;
	}
	public LocalDateTime getDataInici() {
		return dataInici;
	}
	public void setDataInici(LocalDateTime dataInici) {
		this.dataInici = dataInici;
	}
	public List<Integer> getCodiSecret() {
		return codiSecret;
	}
	public void setCodiSecret(List<Integer> codiSecret2) {
		this.codiSecret = codiSecret2;
	}
	public String getUidUsuari() {
		return uidUsuari;
	}
	public void setUidUsuari(String uidUsuari) {
		this.uidUsuari = uidUsuari;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
