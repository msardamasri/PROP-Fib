package test.subgrup14_1.mastermind.drivers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.subgrup14_1.mastermind.domini.controladors.ControladorDomini;
import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Farre Burgos (pol.farre.burgos@estudiantat.upc.edu)
 */
public class DriverEstadistiques {
	
	public ControladorDomini controladorDomini = new ControladorDomini();
	
	private void consultarRanking( ) {
		Utils.printTitol("Ranquing de MasterMind");
    	try {
    		List<InformacioUsuari> llista = this.controladorDomini.llistaRanquingUsuaris();
    		Integer i = 1;
    		for (InformacioUsuari iu: llista) {
    			System.out.print("TOP " + i.toString() + " | ");
    			System.out.println(iu.getUsername() + " - Maxima puntuacio: " + iu.getMaxPuntuacio() + " - Num partides jugades: " + iu.getNumPartidesJugades());
    			++i;
    		}
    	}
        catch (ExcepcioUsuari eu) {
        	System.out.println(eu.getMessage());
        }
    }
	
	private void jugaPartida(Dificultat dif) {
		try {
			this.controladorDomini.crearPartida(dif, Rol.CODEMAKER);
			this.controladorDomini.iniciarPartida();
			List<Integer> llista = new ArrayList<Integer>();
			Integer max = 3;
			if (dif == Dificultat.INTERMIG) max = 4;
			else if (dif == Dificultat.DIFICIL) max = 5;
			for (int i = 0; i < 4; ++i) {
				Integer rand = Utils.numeroAleatori(0, max);
				while (max == 3 && llista.contains(rand)) rand = Utils.numeroAleatori(0, max);
				llista.add(rand);
			}
			this.controladorDomini.iniciarCodi(llista);
			this.controladorDomini.guardarPartida();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void inicialitzaInformacio() {
		System.out.println("Executant joc de proves, aixo pot trigar uns instants...");
		Boolean errors = false;
		try {
			List<String> usernames = Arrays.asList("Jordi", "Laura", "Pere", "Ines", "Ariadna", "Josep");
			for (Integer i = 0; i < 6; ++i) {
				this.controladorDomini.crearUsuari(usernames.get(i), "12345678");
			}
			List<InformacioUsuari> llistaUsers = this.controladorDomini.llistaUsuaris();
			for (Integer i = 0; i < 6; ++i) {
				this.controladorDomini.carregarUsuari(llistaUsers.get(i).getUid(), "12345678");
				jugaPartida(Dificultat.DIFICIL);
				jugaPartida(Dificultat.INTERMIG);
				jugaPartida(Dificultat.FACIL);
				this.controladorDomini.guardarUsuari();
				this.controladorDomini.logout();
			}
		} catch (ExcepcioUsuari e) {
			errors = true;
		}
		if (!errors) System.out.println("El joc de proves s'ha executat correctament");
		else System.out.println("El joc de proves NO s'ha executat correctament");
	}
	
	public static void main(String[] args) {
		DriverEstadistiques driverEstadistiques = new DriverEstadistiques();
		Utils.printTitol("DriverEstadistiques");
		driverEstadistiques.inicialitzaInformacio();
		driverEstadistiques.consultarRanking();
	}
}
