package test.subgrup14_1.mastermind.drivers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.subgrup14_1.mastermind.domini.controladors.ControladorDomini;
import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.utils.InformacioPartida;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.PartidaEnJoc;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Farre Burgos (pol.farre.burgos@estudiantat.upc.edu)
 */
public class DriverPartida {
	private ControladorDomini controladorDomini = new ControladorDomini();
    
	private void printJoc(PartidaEnJoc partida) {
		Integer torn = partida.getIntents().size();
		Utils.printTitol("JOC");
		System.out.print("Torn: " + torn.toString());
		System.out.println(" | Puntuacio: " + partida.getPuntuacio().toString());
		
		System.out.println("----------------------------------------");
		for (Integer i = 0; i < torn; ++i) {
			Boolean first = true;
			for (Integer j = 0; j < 4; ++j) {
				if (first) {
					first = false;
					System.out.print("Torn " + i + ": | ");
				}
				System.out.print(partida.getIntents().get(i).get(j));
				System.out.print(" | ");
			}
			System.out.println("  Correccio  B: " + partida.getCorreccions().get(i).getL().toString() + ", N: " + partida.getCorreccions().get(i).getR().toString());
		}
		System.out.println("----------------------------------------");
	}
	
	private List<Integer> inputCodi(Dificultat dif) {
		System.out.println("Estas jugant en dificultat " + Utils.difToString(dif) + ", introdueix un codi secret acorde a aquesta");
		if (dif == Dificultat.FACIL) {
			System.out.println("Pots usar els colors 0,1,2,3 i no es poden repetir");
		}
		else if (dif == Dificultat.INTERMIG) {
			System.out.println("Pots usar els colors 0,1,2,3,4 i es poden repetir");
		}
		else {
			System.out.println("Pots usar els colors 0,1,2,3,4,5 i es poden repetir");
		}
		try {
			List<Integer> codiSecret = new ArrayList<Integer>();
			for (Integer i = 0; i < 4; ++i) {
				Integer aux = Utils.llegirNum("Intodueix color per a la posicio num " + i.toString() + " del codi");
				if (dif == Dificultat.FACIL) while (aux < 0 || aux > 3 || codiSecret.contains(aux)) aux = Utils.llegirNum("Color no valid, torna'l a introduir");
				else if (dif == Dificultat.INTERMIG) while (aux < 0 || aux > 4) aux = Utils.llegirNum("Color no valid, torna'l a introduir");
				else while (aux < 0 || aux > 5) aux = Utils.llegirNum("Color no valid, torna'l a introduir");
				codiSecret.add(aux);
			}
			return codiSecret;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private int pausa(String msg) {
		Utils.cls();
		System.out.println(msg);
		Utils.printTitol("Menu de pausa");
		System.out.println("1 | Tornar a la partida");
		System.out.println("2 | Guardar la partida");
		System.out.println("3 | Abandonar la partida");
		try {
			Integer i = Utils.llegirNum("Selecciona una opcio");
			if (i == 2) {
				this.controladorDomini.guardarPartida();
				if (pausa("Partida guardada correctament") == -1) return -1;
			}
			else if (i == 3) {
				this.controladorDomini.abandonarPartida();
				Utils.cls();
				if (joc(null, null, true) == -1) return -1;
			}
		}
		catch (IOException e) {
			System.out.println("Error llegint de la consola");
		}
		catch (ExcepcioPartida ep) {
			System.out.println(ep.getMessage());
		}
		return 0;
	}
	
	private int joc(PartidaEnJoc partida, Boolean codemaker, Boolean abandonar) {
		if (abandonar) return -1;
		try {
			Character c = Utils.llegirchar("Si vols pausar la partida, introdueix 'P', si no, prem qualsevol tecla");
			if (c == 'P') if (pausa("") == -1) return -1;
		}
		catch (IOException e) {
			System.out.println("Error llegint de la consola");
		}
		if (!partida.getAcabada()) {
			printJoc(partida);
			if (codemaker) {
				System.out.println("Es el teu torn per escollir el codi secret!");
				List<Integer> codiSecret = inputCodi(partida.getDificultat());
				while (codiSecret == null) {
					System.out.println("Hi ha hagut un error, torna a intentar-ho");
					codiSecret = inputCodi(partida.getDificultat());
				}
				try {
					PartidaEnJoc seguentTorn = this.controladorDomini.iniciarCodi(codiSecret);
					if (joc(seguentTorn, true, false) == -1) return -1;
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			else {
				System.out.println("Es el teu torn per intentar desxifrar el codi!");
				List<Integer> codiSecret = inputCodi(partida.getDificultat());
				while (codiSecret == null) {
					System.out.println("Hi ha hagut un error, torna a intentar-ho");
					codiSecret = inputCodi(partida.getDificultat());
				}
				try {
					PartidaEnJoc seguentTorn = this.controladorDomini.intentarCodi(codiSecret);
					if (joc(seguentTorn, false, false) == -1) return -1;
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		else {
			printJoc(partida);
			System.out.println("La partida ha acabat!");
			Integer torn = partida.getCorreccions().size();
			if (partida.getCorreccions().get(torn-1).getR() == 4) {
				if (!codemaker) System.out.println("HAS GUANYAT! ENHORABONA");
				else System.out.println("HAS PERDUT, PROVA SORT EN UNA NOVA PARTIDA");
			}
			else {
				if (codemaker) System.out.println("HAS GUANYAT! ENHORABONA");
				else System.out.println("HAS PERDUT, PROVA SORT EN UNA NOVA PARTIDA");
			}
		}
		return 0;
	}
	
	private void novaPartida() {
		Utils.cls();
        Utils.printTitol("NOVA PARTIDA");
        try {
	        Dificultat dif = null;
	        while (dif == null) {
	            System.out.println("Escull la dificultat:");
	            System.out.println("F | Facil");
	            System.out.println("I | Intermig");
	            System.out.println("D | Dificil");
	            
	            char dif_escollida = Utils.llegirchar("Indica la dificultat de la partida");
	            
	            if (dif_escollida == 'F' || dif_escollida == 'f') dif = Dificultat.FACIL;
	            else if (dif_escollida == 'I' || dif_escollida == 'i') dif = Dificultat.INTERMIG;
	            else if (dif_escollida == 'D' || dif_escollida == 'd') dif = Dificultat.DIFICIL;
	            else {
	                System.out.println("La dificultat escollida no esta disponible.");
	                System.out.println("Si us plau, escull una de les opcions presentades.");
	            }
	        }
	        System.out.println("Dificultat escollida correctament.");
	        System.out.println("");
	        
	        Rol rol = null;
	        while (rol == null) {
	             System.out.println("Escull el rol:");
	             System.out.println("1 | CODEMAKER");
	             System.out.println("2 | CODEBREAKER");
	
	             int rol_escollit = Utils.llegirNum("Indica el rol de la partida");
	
	             if (rol_escollit == 1) rol = Rol.CODEMAKER;
	             else if (rol_escollit == 2) rol = Rol.CODEBREAKER;
	             else {
	                System.out.println("El rol escollit no esta disponible.");
	                System.out.println("Si us plau, escull una de les dues opcions presentades.");
	             }
	        }
	        System.out.println("Rol escollit correctament.");
	        System.out.println("");

        	this.controladorDomini.crearPartida(dif, rol);
        	System.out.println("S'ha configurat la partida correctament.");
        	PartidaEnJoc partida = controladorDomini.iniciarPartida();
        	joc(partida, rol == Rol.CODEMAKER, false);
        }
        catch (ExcepcioUsuari eu) {
        	System.out.println(eu.getMessage());
        }
        catch (ExcepcioPartida ep) {
        	System.out.println(ep.getMessage());
        }
        catch (IOException e) {
        	System.out.println("Error llegint de consola");
        }
    }
    
    private void carregarPartida() {
    	Utils.cls();
        Utils.printTitol("CARREGAR PARTIDA");
        try {
        	InformacioUsuari iu = this.controladorDomini.informacioUsuariCarregat();
        	List<InformacioPartida> llista = controladorDomini.llistaPartidesUsuari(iu.getUid());
        	
        	Integer i;
			for (i = 0; i < llista.size(); ++i) {
				System.out.println(i.toString() +
						" | Puntuacio: " + llista.get(i).getPuntuacio()
						+ " - Dificultat: " + Utils.difToString(llista.get(i).getDificultat())
						+ " - Data: " + llista.get(i).getData().toString());
			}
			
			Integer opcio = -1;
			while (opcio < 0 || opcio >= llista.size()) {
				opcio = Utils.llegirNum("Selecciona la partida que vulguis carregar");
				if (opcio < 0 && opcio >= llista.size()) System.out.println("Aquesta opcio no es valida!");
			}
			
			PartidaEnJoc partida = this.controladorDomini.carregarPartida(llista.get(opcio).getUidPartida());
			joc(partida, false, false);
        }
        catch (ExcepcioPartida ep) {
        	System.out.println(ep.getMessage());
        }
        catch (ExcepcioUsuari eu) {
        	System.out.println(eu.getMessage());
        }
        catch (IOException e) {
        	System.out.println("Error llegint les dades");
        }
    }

    private void jocProves() {
    	Boolean errors = false;
		try {
			System.out.println("...jugant partida com a codemaker en facil...");
			this.controladorDomini.crearPartida(Dificultat.FACIL, Rol.CODEMAKER);
			PartidaEnJoc p = this.controladorDomini.iniciarCodi(Arrays.asList(0,1,2,3));
			if (p.getAcabada() && p.getCorreccions().get(p.getCorreccions().size()-1).getR() != 4) System.out.println("...partida guanyada (s'ha endevinat el codi)...");
			else System.out.println("...partida perduda...");
			
			System.out.println("...jugant partida com a codemaker en intermig...");
			this.controladorDomini.crearPartida(Dificultat.INTERMIG, Rol.CODEMAKER);
			p = this.controladorDomini.iniciarCodi(Arrays.asList(1,1,2,4));
			if (p.getAcabada() && p.getCorreccions().get(p.getCorreccions().size()-1).getR() != 4) System.out.println("...partida guanyada (s'ha endevinat el codi)...");
			else System.out.println("...partida perduda...");
			
			System.out.println("...jugant partida com a codemaker en dificil...");
			this.controladorDomini.crearPartida(Dificultat.DIFICIL, Rol.CODEMAKER);
			p = this.controladorDomini.iniciarCodi(Arrays.asList(0,5,2,1));
			if (p.getAcabada() && p.getCorreccions().get(p.getCorreccions().size()-1).getR() != 4) System.out.println("...partida guanyada (s'ha endevinat el codi)...");
			else System.out.println("...partida perduda...");
			
			System.out.println("...jugant partida com a codebreaker...");
			this.controladorDomini.crearPartida(Dificultat.FACIL, Rol.CODEBREAKER);
			p = this.controladorDomini.intentarCodi(Arrays.asList(0,1,2,3));
			System.out.println("...guardant partida...");
			this.controladorDomini.guardarPartida();
			System.out.println("...abandonant partida...");
			this.controladorDomini.abandonarPartida();
			String uid = this.controladorDomini.informacioUsuariCarregat().getUid();
			System.out.println("...obtenint partides guardades...");
			List<InformacioPartida> l = this.controladorDomini.llistaPartidesUsuari(uid);
			System.out.println("...carregant partida...");
			this.controladorDomini.carregarPartida(l.get(0).getUidPartida());
			p = this.controladorDomini.infoPartidaEnJoc();
			System.out.println("...carregada...");
			this.controladorDomini.abandonarPartida();
		}
		catch (Exception e) {
			errors = true;
		}
		if (!errors) System.out.println("El joc de proves s'ha executat correctament");
		else System.out.println("El joc de proves NO s'ha executat correctament");
	}
    
    public static void main(String[] args) {

        DriverPartida driverPartida = new DriverPartida();
        try {
        	driverPartida.controladorDomini.crearUsuari("usuariDriver", "passwordDriver");
        	List<InformacioUsuari> llista = driverPartida.controladorDomini.llistaUsuaris();
        	driverPartida.controladorDomini.carregarUsuari(llista.get(0).getUid(), "passwordDriver");
        }
        catch (ExcepcioUsuari eu) {
        	System.out.println(eu.getMessage());
        }
        
        try {
        	Integer opcio = 0;
			while (opcio != 4) {
		        Utils.printTitol("DriverPartida");
		        System.out.println("1 | Crear una nova partida");
				System.out.println("2 | Carregar una partida existent");
				System.out.println("3 | Executar joc de proves");
		        System.out.println("4 | Canviar de perfil d'usuari (Tanca el Driver)");
	        	opcio = Utils.llegirNum("Escull una opcio");
	        	
				if (opcio == 1) driverPartida.novaPartida();
				else if (opcio == 2) driverPartida.carregarPartida();
				else if (opcio == 3) driverPartida.jocProves();
				if (opcio < 1 || opcio > 4) {
					System.out.println("L'opcio indicada no es cap de les disponibles");
					opcio = Utils.llegirNum("Si us plau, torna a indicar una opcio");
				}
			}
        }
        catch (IOException e) {
        	System.out.println(e.getMessage());
        	main(args);
        }
    }
}
