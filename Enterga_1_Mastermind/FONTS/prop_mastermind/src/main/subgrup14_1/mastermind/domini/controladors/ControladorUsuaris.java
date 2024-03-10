package main.subgrup14_1.mastermind.domini.controladors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.subgrup14_1.mastermind.domini.models.Usuari;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorUsuaris {
	
	private Map<String, Usuari> usuaris;
	
	public ControladorUsuaris() {
		super();
		this.usuaris = new HashMap<String, Usuari>();
		actualitzaUsuaris();
	}
	
	/**
	 * Comprova parametres i crea un usuari
	 * @param username Nom d'usuari
	 * @param pwd Contrasenya
	 * @throws ExcepcioUsuari
	 */
	public void crearUsuari(String user, String pwd) throws ExcepcioUsuari {
		if (user == null || user.isEmpty()) throw new ExcepcioUsuari("Has d'introduir un nom d'usuari valid");
		
		if (nomUsuariEnUs(user)) throw new ExcepcioUsuari("Ja existeix un perfil enregistrar amb aquest username: " + user);
		
		if (pwd == null || pwd.isEmpty()) throw new ExcepcioUsuari("Has d'introduir una contrasenya valida");
		
		String hPwd = Utils.hashSHA256(pwd);
		Usuari usuariNou = new Usuari(user, hPwd);
		this.usuaris.put(usuariNou.getUid(), usuariNou);
		// Emmagatzemar usuari a capa persistencia
	}
	
	/**
	 * Esborra un usuari i les partides associades a aquest
	 * @param uid Identificador de l'usuari
	 * @param pwd Contrasenya de l'usuari a esborrar
	 * @throws ExcepcioUsuari
	 */
	public void esborrarUsuari(String uid, String pwd) throws ExcepcioUsuari {
		Usuari u = retornaUsuariContrasenya(uid, pwd);
		
		if (u != null) this.usuaris.remove(uid);
		// Esborrar usuari i partides associades a capa persistencia
	}
	
	/**
	 * Llegeix els usuaris de la capa de persistencia
	 */
	public void actualitzaUsuaris() {
		/* List<Usuari> llista = crida a persistencia
		 * for (Usuari u : llista) {
		 * 		this.usuaris.put(u.getUid(), u);
		 * }
		*/
	}
	
	/**
	 * Obtenir usuari amb username
	 * @param username Nom d'usuari (unic), identificador de l'usuari
	 * @return objecte usuari amb l'username especificat
	 */
	private Usuari retornaUsuari(String uid) {
		return this.usuaris.get(uid);
	}
	
	/**
	 * Comprova paramtres i obte un usuari amb uid i contrasenya
	 * @param uid Identificador de l'usuari
	 * @param pwd Contrasenya de l'usuari
	 * @return L'usuari amb uid si la contrasenya es correcta o null si no
	 * @throws ExcepcioUsuari 
	 */
	public Usuari retornaUsuariContrasenya(String uid, String pwd) throws ExcepcioUsuari {
		if (uid == null || uid.isEmpty()) throw new ExcepcioUsuari("L'identificador d'usuari no es valid");
		
		if (pwd == null || pwd.isEmpty()) throw new ExcepcioUsuari("Introdueix un valor valid per a la contrasenya");
		
		Usuari u = retornaUsuari(uid);
		String hPwd = Utils.hashSHA256(pwd);
		if (hPwd.equals(u.gethContrasenya())) {
			return u;
		}
		throw new ExcepcioUsuari("La contrasenya introduida no es correcta, torna a intentar-ho o selecciona un altre perfil d'usuari");
	}
	
	/**
	 * Comprova si un nom d'usuari esta en us
	 * @param username Nom d'usuari a comprovar
	 * @return Retorna cert si el nom ja esta sent utilitzat o fals si no
	 */
	public Boolean nomUsuariEnUs(String username) {
		for (Entry<String, Usuari> e : this.usuaris.entrySet()) {
			if (e.getValue().getNomUsuari().equals(username)) return true;
		}
		return false;
	}
	
	/**
	 * Comprova si existeix un usuari mitjancant un identificador
	 * @param uid Identificador de l'usuari a comprovar
	 * @return Retorna cert si l'usuari existeix, fals si no
	 */
	public Boolean existeixUsuari(String uid) {
		return this.usuaris.get(uid) != null;
	}
	
	/**
	 * Obte informacio dels usuaris (UID, nom, maxPuntuacio i nombre de partides jugades)
	 * @return llista amb parells d'UIDs i noms d'usuari de tots els usuaris
	 * @throws ExcepcioUsuari 
	 */
	public List<InformacioUsuari> llistaUsuaris() throws ExcepcioUsuari {
		List<InformacioUsuari> llista = new ArrayList<InformacioUsuari>();
		for (Entry<String, Usuari> e : this.usuaris.entrySet()) {
			llista.add(
					new InformacioUsuari(
							e.getValue().getUid(),
							e.getValue().getNomUsuari(),
							e.getValue().getMaxPuntuacio(),
							e.getValue().numPartidesTotals()
							)
					);
		}
		if (llista.isEmpty()) throw new ExcepcioUsuari("Actualment no hi ha cap usuari registrat");
		return llista;
	}
	
	/**
	 * Obte llistaUsuaris() ordenada segons la maxima puntuacio
	 * @return Llista d'InformacioUsuaris de tots els usuaris ordenada per maxima puntuacio
	 * @throws ExcepcioUsuari 
	 */
	public List<InformacioUsuari> llistaRanquingUsuaris() throws ExcepcioUsuari {
		List<InformacioUsuari> llista = llistaUsuaris();
		Collections.sort(llista);
		return llista;
	}
	
	/**
	 * Guarda un usuari
	 * @param u Usuari a guardar
	 */
	public void guardarUsuari(Usuari u) {
		this.usuaris.put(u.getUid(), u);
		// Guardar a capa persitencia
	}
}
