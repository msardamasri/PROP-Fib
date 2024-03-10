package main.subgrup14_1.mastermind;

import java.awt.EventQueue;

import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.excepcions.ExcepcioUsuari;
import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Main {
	
	/**
	 * Main del programa, punt d'inici de l'aplicacio
	 * @param args Arguments (no se'n usa cap)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControladorPresentacio.initialize();
				} catch (ExcepcioUsuari | ExcepcioPartida e) {
					e.printStackTrace();
				}
			}
		});
	}
}
