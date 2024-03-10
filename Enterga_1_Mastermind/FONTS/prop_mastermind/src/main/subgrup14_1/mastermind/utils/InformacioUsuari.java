package main.subgrup14_1.mastermind.utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class InformacioUsuari implements Comparable<InformacioUsuari> {
	private String uid;
	private String username;
	private Integer maxPuntuacio;
	private Integer numPartidesJugades;
	public InformacioUsuari(String uid, String username, Integer maxPuntuacio, Integer numPartidesJugades) {
		super();
		this.uid = uid;
		this.username = username;
		this.maxPuntuacio = maxPuntuacio;
		this.numPartidesJugades = numPartidesJugades;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getMaxPuntuacio() {
		return maxPuntuacio;
	}
	public void setMaxPuntuacio(Integer maxPuntuacio) {
		this.maxPuntuacio = maxPuntuacio;
	}
	public Integer getNumPartidesJugades() {
		return numPartidesJugades;
	}
	public void setNumPartidesJugades(Integer numPartidesJugades) {
		this.numPartidesJugades = numPartidesJugades;
	}
	@Override
	public int compareTo(InformacioUsuari i) {
		return i.getMaxPuntuacio().compareTo(this.getMaxPuntuacio());
	}
}
