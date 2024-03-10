package test.subgrup14_1.mastermind.drivers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.subgrup14_1.mastermind.domini.controladors.ControladorDomini;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Farre Burgos (pol.farre.burgos@estudiantat.upc.edu)
 */
public class DriverPerfil {
	
	private ControladorDomini controladorDomini = new ControladorDomini();
	
	private void crearPerfil() {
		Utils.printTitol("Crear perfil");
		String nom = "";
		String pwd = "";

		try {
			nom = Utils.llegirString("Introdueix un nom d'usuari (username) (min. 3 car.)");
			pwd = Utils.llegirString("Introdueix una contrasenya (min. 3 car.)");
			this.controladorDomini.crearUsuari(nom, pwd);
			Utils.cls();
			System.out.println("Perfil creat! Benvingut " + nom + "!");
		}
		catch (IOException e) {
			System.out.println("Error llegint les dades");
		}
		catch (ExcepcioUsuari eu) {
			System.out.println(eu.getMessage());
		}
		for (int i = 0; i < 3; ++i) System.out.println();
	}
		
	private void carregarPerfil() throws IOException {
		Utils.printTitol("Carregar perfil");
		
		Integer opcio = 0;
		String pwd = "";
		
		List<InformacioUsuari> llista = new ArrayList<InformacioUsuari>();
		
		try {
			llista = this.controladorDomini.llistaUsuaris();
			Integer i;
			for (i = 0; i < llista.size(); ++i) {
				System.out.println(i.toString() + ": " + llista.get(i).getUsername() + " - Partides jugades: " + llista.get(i).getNumPartidesJugades().toString() + " - Puntuacio maxima: " + llista.get(i).getMaxPuntuacio().toString());
			}
			
			opcio = Utils.llegirNum("Selecciona el perfil d'usuari per carregar");
			pwd = Utils.llegirString("Introdueix la contrasenya del perfil");
			
			this.controladorDomini.carregarUsuari(llista.get(opcio).getUid(), pwd);
			Utils.cls();
			System.out.println("S'ha carregat el perfil d'usuari " + llista.get(opcio).getUsername() + " correctament!");
		}
		catch (IOException e) {
			System.out.println("Error llegint les dades");
		}
		catch (ExcepcioUsuari eu) {
			System.out.println(eu.getMessage());
		}
		for (int i = 0; i < 3; ++i) System.out.println();
	}
	
	private void esborrarPerfil() throws IOException {
		Utils.printTitol("Esborrar perfil");
		
		Integer opcio = 0;
		String pwd = "";
		
		List<InformacioUsuari> llista = new ArrayList<InformacioUsuari>();
		
		try {
			llista = this.controladorDomini.llistaUsuaris();
			Integer i;
			for (i = 0; i < llista.size(); ++i) {
				System.out.println(i.toString() + ": " + llista.get(i).getUsername() + " - Partides jugades: " + llista.get(i).getNumPartidesJugades().toString() + " - Puntuacio maxima: " + llista.get(i).getMaxPuntuacio().toString());
			}
			
			opcio = Utils.llegirNum("Selecciona el perfil d'usuari per esborrar");
			pwd = Utils.llegirString("Introdueix la contrasenya del perfil");
			
			this.controladorDomini.esborrarUsuari(llista.get(opcio).getUid(), pwd);

			Utils.cls();
			System.out.println("S'ha esborrat el perfil d'usuari " + llista.get(opcio).getUsername() + " correctament!");
		}
		catch (IOException e) {
			System.out.println("Error llegint les dades");
		}
		catch (ExcepcioUsuari eu) {
			System.out.println(eu.getMessage());
		}
		for (int i = 0; i < 3; ++i) System.out.println();
	}
	
	private void consultarManual() throws IOException {
		Utils.printTitol("Consultar manual");
		
		//Introduccio:
		System.out.println("Benvingut a Mastermind!");
		System.out.println("Mastermind es un joc d'estrategia i habilitat mental en el qual l'objectiu es endevinar un codi secret generat pel sistema si esculls el rol de codebreaker o generat per tu si esculls el rol de codemaker. Per jugar-hi, segueix les seguents instruccions:");

		int opcio = -1;
		
		while (opcio != 0) {
			try {
				//Index:
				System.out.println("Index:");
				System.out.println("  1. Crear perfil");
				System.out.println("  2. Carregar perfil");
				System.out.println("  3. Esborrar perfil");
				System.out.println("  4. Consultar ranquing");
				System.out.println("  5. Jugar partida");
				opcio = Utils.llegirNum("Indica un punt de l'index a consultar o 0 per a sortir del manual");
			}
			catch (IOException e) {
				System.out.println("Error llegint les dades");
			}
			//Crear perfil:
			if (opcio == 1) {
				Utils.cls();
				System.out.println("Crear perfil:");
				System.out.println("   - Selecciona l'opcio 'Crear perfil'.");
				System.out.println("   - Completa els camps necessaris per introduir el teu nom d'usuari i contrasenya.");
				System.out.println("   - Prem el boto 'Crear' per registrar el teu perfil.");
				System.out.println("   - Si no introdueixes un nom d'usuari o contrasenya valids, apareixera un missatge d'error. Torna-ho a intentar.");
				System.out.println(" ");
				System.out.println(" ");
			}
			//Carregar perfil:
			else if (opcio == 2) {
				Utils.cls();
				System.out.println("Carregar perfil:");
				System.out.println("   - Selecciona l'opcio 'Carregar perfil'.");
				System.out.println("   - Es mostrara una llista d'usuaris registrats amb la seva puntuacio maxima i el nombre de partides jugades.");
				System.out.println("   - Selecciona el perfil que desitgis carregar i introdueix la contrasenya associada a aquest perfil.");
				System.out.println("   - Si no introdueixes una contrasenya valida, apareixera un missatge d'error. Torna-ho a intentar.");
				System.out.println(" ");
				System.out.println(" ");
			}
			//Esborrar perfil:
			else if (opcio == 3) {
				Utils.cls();
				System.out.println("Esborrar perfil:");
				System.out.println("   - Selecciona l'opcio 'Esborrar perfil'.");
				System.out.println("   - Es mostrara una llista d'usuaris registrats amb la seva puntuacio maxima i el nombre de partides jugades.");
				System.out.println("   - Selecciona el perfil que vulguis esborrar i introdueix la contrasenya associada a aquest perfil.");
				System.out.println("   - Si no introdueixes una contrasenya valida, apareixera un missatge d'error. Torna-ho a intentar.");
				System.out.println("   - Un cop confirmada la contrasenya, el perfil i totes les dades associades a ell seran esborrades, incloses les partides històriques.");
				System.out.println(" ");
				System.out.println(" ");
			}
			//Consultar ranquing
			else if (opcio == 4) {
				Utils.cls();
				System.out.println("Consultar ranquing:");
				System.out.println("   - Selecciona l'opcio 'Consultar ranquing'.");
				System.out.println("   - Es mostrara una llista d'usuaris registrats ordenats per la seva puntuacio maxima.");
				System.out.println("   - Podras veure la posicio al ranquing de cada usuari i comparar la teva puntuacio amb la d'altres jugadors.");
				System.out.println(" ");
				System.out.println(" ");
			}
			//Jugar partida
			else if (opcio == 5) {
				Utils.cls();
				System.out.println("Jugar partida:");
				System.out.println("   - Un cop hagis carregat un perfil, podras seleccionar l'opcio 'Crear partida' o 'Carregar partida' per comencar a jugar.");
				System.out.println(" ");
				//Crear partida:
				System.out.println("Crear partida:");
				System.out.println("   - Selecciona l'opcio 'Crear una nova partida'.");
				System.out.println("   - Es mostraran per pantalla les diferents opcions de dificultat a escollir.");
				System.out.println("   - Facil 4 colors a escollir sense repeticions entre 4 colors diferents.");
				System.out.println("   - Intermig 4 colors a escollir amb possibles repeticions entre 5 colors diferents.");
				System.out.println("   - Dificil 4 colors a escollir amb possibles repeticions entre 6 colors diferents.");
				System.out.println("   - Escull una d'aquestes opcions.");
				System.out.println("   - Es mostraran per pantalla les diferents opcions de rol ha escollit.");
				System.out.println("   - Codemaker: Tu seras qui creara la combinacio secreta de colors i, per tant, sera la maquina qui l'haura d'endevinar. Com mes torns tardi la maquina a endevinar la combinacio, mes puntuacio rebras.");
				System.out.println("   - Codebreaker: Seras tu qui endevinara la combinacio secreta de colors creada per la maquina i per tant, com menys torns tardis a endevinar-la, mes puntuacio tindras.");
				System.out.println("   - Si indiques en algun cas, una opcio que no correspon a cap de les possibles, indicades per pantalla, es mostrara per pantalla un missatge d'error.");
				System.out.println(" ");
				//Carregar partida:
				System.out.println("Carregar partida:");
				System.out.println("   - Selecciona l'opcio 'Carregar una partida existent'.");
				System.out.println("   - Es mostrara per pantalla una llista amb les partides que has guardat anteriorment. Si no n’hi ha cap guardada, veuras un missatge que ho indica.");
				System.out.println("   - Selecciona de les partides mostrades, la que desitges carregar per a continuar.");
				System.out.println(" ");
				//Jugar com a CODEBREAKER
				System.out.println("Jugar com a CODEBREAKER:");
				System.out.println("   - En aquest cas, el teu objectiu es endevinar el codi secret amb el menor nombre d'intents possibles.");
				System.out.println("   - Selecciona els colors de la combinacio que creguis correcta i prem el boto de confirmar.");
				System.out.println("   - El sistema et donara pistes sobre la correccio de la teva eleccio, indicant-te quants colors estan a la posicio correcta (color i posicio correcta) i quants colors estan presents pero a la posicio incorrecta (color correcte pero posicio incorrecta).");
				System.out.println("   - Utilitza les pistes per deduir la combinacio correcta i fer mes intents fins a endevinar el codi secret.");
				System.out.println(" ");
				//Jugar com a CODEMAKER
				System.out.println("Jugar com a CODEMAKER:");
				System.out.println("   - En aquest cas, el teu objectiu es crear un codi secret per tal que el teu contrincant, el codebreaker, tardi el maxims torns possibles en endevinar la combinacio per tal de tenir tu, la maxima puntuacio possible.");
				System.out.println(" ");
				//Menú de pausa
				System.out.println("Menu de pausa:");
				System.out.println("   - Un cop estiguis jugant una partida, podras pausar aquesta i es mostrara per pantalla el menu de pausa i les seves opcions.");
				System.out.println(" ");
				System.out.println("Opcio de sortir sense desar:");
				System.out.println("   - Si esculls aquesta opcio, sortiras de la partida i les dades d'aquesta es perdran. Per tant, no podras continuar-la en un futur.");
				System.out.println(" ");
				System.out.println("Opcio de desar i sortir:");
				System.out.println("   - Aquesta opcio et permet sortir de la partida i desar l'estat actual d'aquesta per si en un futur la vols continuar.");
				System.out.println(" ");
				System.out.println("Opcio de continuar:");
				System.out.println("   - Si esculls aquesta opcio, es tancara el menu de pausa i la partida continuara en el punt on estava abans de ser pausada.");
				System.out.println(" ");
				//Final de partida:
				System.out.println("Final de partida:");
				System.out.println("   - La partida es considera finalitzada quan aconsegueixes endevinar el codi secret o quan has fet el nombre maxim d'intents permesos.");
				System.out.println("   - En finalitzar la partida, se't mostrara el resultat, indicant-te el nombre d'intents que has necessitat per endevinar el codi secret i la teva puntuacio per aquesta partida.");
				System.out.println("   - La puntuacio es calcula d'acord amb el nombre d'intents utilitzats.");
				System.out.println(" ");
				System.out.println(" ");
			}
			else if (opcio == 0) {
				Utils.cls();
				System.out.println("Ara estas preparat per gaudir del joc de Mastermind!");
				System.out.println("Endevina o crea un codi secret i millora la teva puntuacio per convertir-te en el millor jugador. Molta sort!");
				try {
					// Pausa el programa durant 2 segons
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return;
			}
			else {
				System.out.println("L'opcio indicada no es cap de les disponibles");
				opcio = Utils.llegirNum("Si us plau, torna a indicar l'opcio");
			}
		}
	}
	
	private void jocProves() {
		Utils.cls();
		System.out.println("Executant joc de proves, aixo pot trigar uns instants...");
		List<String> nomsUsuaris = Arrays.asList("Jordi", "Pere", "Maria", "Joana", "Manel");
		String pwd = "contrasenyaGenerica";
		Boolean errors = false;
		for (String s: nomsUsuaris) {
			try {
				System.out.println("...creant usuari...");
				this.controladorDomini.crearUsuari(s, pwd);
				System.out.println("...obtenint info usuari...");
				List<InformacioUsuari> l = this.controladorDomini.llistaUsuaris();
				System.out.println("...carregant usuari...");
				this.controladorDomini.carregarUsuari(l.get(0).getUid(), pwd);
				System.out.println("...esborrant usuari...");
				this.controladorDomini.esborrarUsuari(l.get(0).getUid(), pwd);
			}
			catch (ExcepcioUsuari eu) {
				errors = true;
			}
		}
			if (!errors) System.out.println("El joc de proves s'ha executat correctament");
			else System.out.println("El joc de proves NO s'ha executat correctament");
	}
	
	public static void main(String[] args) throws IOException {
		DriverPerfil driverPerfil = new DriverPerfil();
	
		
		int opcio = 0;
		
		while (opcio != 6) {
			try {
				Utils.printTitol("DriverPerfil");
				System.out.println("1 | Crear un perfil d'usuari");
				System.out.println("2 | Carregar un perfil d'usuari");
				System.out.println("3 | Esborrar un perfil d'usuari");
				System.out.println("4 | Consultar manual de joc");
				System.out.println("5 | Executar joc de proves");
				System.out.println("6 | Sortir del joc");

				opcio = Utils.llegirNum("Indica una opcio");
			}
			catch (IOException e) {
				System.out.println("Error llegint les dades");
			}
			
			if (opcio == 1) driverPerfil.crearPerfil();
			else if (opcio == 2) driverPerfil.carregarPerfil();
			else if (opcio == 3) driverPerfil.esborrarPerfil();
			else if (opcio == 4) driverPerfil.consultarManual();
			else if (opcio == 5) driverPerfil.jocProves();
			else if (opcio == 6) return;
			else {
				System.out.println("L'opcio indicada no es cap de les disponibles");
				opcio = Utils.llegirNum("Si us plau, torna a indicar l'opcio");
			}
		}
	}
}
