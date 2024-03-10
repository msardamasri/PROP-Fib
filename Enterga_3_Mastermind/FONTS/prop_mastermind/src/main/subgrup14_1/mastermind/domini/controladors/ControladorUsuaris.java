package main.subgrup14_1.mastermind.domini.controladors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.subgrup14_1.mastermind.domini.models.Usuari;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPersistencia;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.persistencia.ControladorPersistencia;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorUsuaris {
	
	private static ControladorUsuaris controladorUsuaris;
	private Map<String, Usuari> usuaris;
	private ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
	private final String path = "users.prop"; 
	/**
	 * Constructora del controlador
	 * @throws ExcepcioPersistencia Si hi ha un error al llegir de disc
	 */
	public ControladorUsuaris() throws ExcepcioPersistencia {
		super();
		this.usuaris = new HashMap<String, Usuari>();
		actualitzaUsuaris();
	}
	/**
	 * Obtenir instancia seguint el patro singleton
	 * @return instancia del controlador
	 * @throws ExcepcioPersistencia Si hi ha un error llegint de disc
	 */
	public static ControladorUsuaris getInstance() throws ExcepcioPersistencia   {
		if (controladorUsuaris == null) controladorUsuaris = new ControladorUsuaris();
		return controladorUsuaris;
	}
	/**
	 * Comprova parametres i crea un usuari
	 * @param user Nom d'usuari
	 * @param pwd Contrasenya
	 * @throws ExcepcioPersistencia Si el fitxer no existeix o hi ha un problema a l'escriure en ell
	 * @throws ExcepcioUsuari Si les contrasenyes no quadren o hi h aalgun valor nul
	 */
	public void crearUsuari(String user, String pwd) throws ExcepcioPersistencia, ExcepcioUsuari {
		if (user == null || user.isEmpty()) throw new ExcepcioUsuari("Has d'introduir un nom d'usuari valid");
		
		if (nomUsuariEnUs(user)) throw new ExcepcioUsuari("Ja existeix un perfil enregistrar amb aquest username: " + user);
		
		if (pwd == null || pwd.isEmpty()) throw new ExcepcioUsuari("Has d'introduir una contrasenya valida");
		
		String hPwd = Utils.hashSHA256(pwd);
		Usuari usuariNou = new Usuari(user, hPwd);
		this.usuaris.put(usuariNou.getUid(), usuariNou);
		this.controladorPersistencia.escriu(this.usuaris, path);
	}
	/**
	 * Esborra un usuari i les partides associades a aquest
	 * @param uid Identificador de l'usuari
	 * @param pwd Contrasenya de l'usuari a esborrar
	 * @throws ExcepcioPersistencia Si el fitxer no existeix o hi ha un problema a l'escriure en ell
	 * @throws ExcepcioUsuari Si no existeix l'usuari o la contrasenya es incorrecta
	 */
	public void esborrarUsuari(String uid, String pwd) throws ExcepcioPersistencia, ExcepcioUsuari {
		Usuari u = retornaUsuariContrasenya(uid, pwd);
		
		if (u != null) this.usuaris.remove(uid);
		this.controladorPersistencia.escriu(this.usuaris, path);
	}
	/**
	 * Inicialitza les dades amb informacio de la capa de persistencia
	 * @throws ExcepcioPersistencia Si hi ha un error llegint de disc
	 */
	public void actualitzaUsuaris() throws ExcepcioPersistencia {
		this.usuaris = (Map<String, Usuari>) this.controladorPersistencia.llegeix(this.path);
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
	 * @throws ExcepcioUsuari Si la contrasneya es incorrecta o l'usuari no existeix
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
	 * @throws ExcepcioUsuari Si no hi ha cap usuari registrat
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
	 * @throws ExcepcioUsuari Si no hi ha cap usuari registrar
	 */
	public List<InformacioUsuari> llistaRanquingUsuaris() throws ExcepcioUsuari {
		List<InformacioUsuari> llista = llistaUsuaris();
		Collections.sort(llista);
		return llista;
	}
	/**
	 * Guarda un usuari
	 * @param u Usuari a guardar
	 * @throws ExcepcioPersistencia Si el fitxer no existeix o hi ha un problema a l'escriure en ell
	 */
	public void guardarUsuari(Usuari u) throws ExcepcioPersistencia {
		this.usuaris.put(u.getUid(), u);
		this.controladorPersistencia.escriu(this.usuaris, path);
	}
}
