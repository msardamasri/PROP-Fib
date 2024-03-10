package main.subgrup14_1.mastermind.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.models.Settings;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida; 

/**
 * @author Mixt, col.laboracio
 */
public class Utils {
	
	/**
	 * Obte un enter aleatori compres entre dos valors
	 * @param min Valor minim
	 * @param max Valor maxim
	 * @return Retorna un nombre enter aleatori compres entre min i max (ambdos inclosos)
	 */
    public static Integer numeroAleatori(Integer min, Integer max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
	/**
	 * Imprimeix 20 salts de linia per "netejar" la consola
	 */
	public static void cls() {
		for (Integer i = 0; i < 20; ++i) {
			System.out.println(" ");
		}
	}
	/**
	 * Imprimeix un titol de forma "bonica" per pantalla
	 * @param t Text a imprimir
	 */
	public static void printTitol(String t) {
		String separador = "------------------------------------------------------------------";
		Integer numGuions = separador.length() - t.length();
		System.out.println(separador);
		for (int i = 0; i < numGuions/2; ++i) System.out.print("-");
		System.out.print(t);
		for (int i = 0; i < numGuions/2; ++i) System.out.print("-");
		System.out.println();
		System.out.println(separador);
	}
	
	/**
	 * Llegeix un numero de la consola i el retorna
	 * @param hint Text a mostrar
	 * @return Retorna el Integer introduit a la consola
	 * @throws IOException Si hi ha algun problema amb el input
	 */
	public static Integer llegirNum(String hint) throws IOException {
		System.out.print(hint + ": ");
		Boolean ok = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int i = -1;
		while (!ok) {
			try {
	            i = Integer.parseInt(br.readLine());
	            ok = true;
	        } catch(NumberFormatException nfe) {
	            System.err.println("Invalid Format!");
	        }
		}
		return i;
	}
	
	/**
	 * Llegeix un String de la consola i el retorna
	 * @param hint Text a mostrar
	 * @return Retorna el String introduit a la consola
	 * @throws IOException Si hi ha algun problema amb el input
	 */
	public static String llegirString(String hint) throws IOException {
		System.out.print(hint + ": ");
		Boolean ok = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		while (!ok) {
			s = br.readLine();
	        if (!s.isEmpty() && s.length() >= 3) {
	        	ok = true;
	        } else {
	        	System.out.println("El text ha de tenir mes de 3 caracters");
	        }
		}
		return s;
	}
	
	/**
	 * Llegeix un Character de la consola i el retorna
	 * @param hint Text a mostrar
	 * @return Retorna el Character introduit a la consola
	 * @throws IOException Si hi ha algun problema amb el input
	 */
	public static Character llegirchar(String hint) throws IOException {
		System.out.print(hint + ": ");
		Boolean ok = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		while (!ok) {
			s = br.readLine();
	        if (!s.isEmpty() && s.length() == 1) {
	        	ok = true;
	        } else {
	        	System.out.println("El text ha de tenir com a minim 1 caracter");
	        }
		}
		return s.charAt(0);
	}
	
	/**
	 * Crea un Hash SHA-256 a partir d'un String
	 * @param base String base per a crear el Hash
	 * @return Retorna el String resultant d'aplicar la funcio de Hash SHA-256 al String base
	 */
	public static String hashSHA256(String base) {
	    try{
	        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        final byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        final StringBuilder hexString = new StringBuilder();
	        for (int i = 0; i < hash.length; i++) {
	            final String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) 
	              hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	
	/**
	 * Genera un identificador unic universal (UUID)
	 * @return Retorna un identificador unic universal
	 */
	public static String generateUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Validacio d'una combiancio comparant-lo amb una segona segons la posicio dels parametres que les formen
	 * @param combinacio Combinacio que volem avaluar
	 * @param codiAnterior Combinacio amb la que la volem avaluar
	 * @return Retorna el resultat de la comparacio on el primer component son les posicions correctes i el segon les incorrectes
	 * @throws ExcepcioPartida Si el codi no es valid
	 */
    public static Integer[] compararCombinacions(List<Integer> combinacio, List<Integer> codiAnterior) throws ExcepcioPartida {
    	if(combinacio.size() != codiAnterior.size())
    		throw new ExcepcioPartida("Length of lists for comparison is not the same!");
    	
        Integer[] result = new Integer[2];
        boolean[] checkedA = new boolean[combinacio.size()];
        boolean[] checkedB = new boolean[combinacio.size()];
        Arrays.fill(checkedA, false);
        Arrays.fill(checkedB, false);
        result[0] = result[1] = 0;
        
        for(int i = 0; i<combinacio.size(); i++) {
        	if(combinacio.get(i).equals(codiAnterior.get(i))) {
        		checkedA[i] = true;
        		checkedB[i] = true;
        		result[0]++;
        	}        		
        };
        
        for(int i = 0; i<combinacio.size(); i++) {
      	  for(int j = 0; j<combinacio.size(); j++) {
        	if (!checkedA[i] && !checkedB[j]) {  
        		if(codiAnterior.get(i).equals(combinacio.get(j))) {
        			checkedA[i] = true;
        			checkedB[j] = true;
        			result[1]++;
        			break;
        		}
        	  }	
        	};
        };
        
        return result;
    }
	
    /**
     * Converteix enum de tipus Dificultat a String per a imprimir per pantalla
     * @param d Dificultat
     * @return Retorna un String associat a l'enum Dificultat d
     */
	public static String difToString(Dificultat d) {
		if (d == Dificultat.FACIL) return "Facil";
		if (d == Dificultat.INTERMIG) return "Intermig";
		return "Dificil";
	}
	
    /**
     * Calcula el nombre de possibles respostes possibles per a desxifrar el codi
     * @param settings Configuracio de la partida
     * @return Retorna el nombre de possibles respostes possibles per a desxifrar el codi
     */
    public static int AnswersCount(Settings settings) {
        int r = 1;
        for (int i = 0; i<settings.combinationLength;i++) {
            r = r * 3;
        }
        return r;
    }    
    
    /**
     * Formateja la data a text
     * @param d Data a formatejar
     * @return String amb la data formatejada
     */
    public static String formatData(LocalDateTime d) {
    	return ((Integer)d.getDayOfMonth()).toString() + "/" +
    			((Integer)d.getMonthValue()).toString()+ "/" +
    			((Integer)d.getYear()).toString() + " " + 
    			((Integer)d.getHour()).toString() + ":" +
    			((Integer)d.getMinute()).toString()+ ":" +
    			((Integer)d.getSecond()).toString();
    }
	
}
