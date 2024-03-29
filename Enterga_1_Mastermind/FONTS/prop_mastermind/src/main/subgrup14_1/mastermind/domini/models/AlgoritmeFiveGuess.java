package main.subgrup14_1.mastermind.domini.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import main.subgrup14_1.mastermind.domini.interficies.Maquina;

//AUTOR MARC SARDÀ
public class AlgoritmeFiveGuess implements Maquina {
    
	/** Colors disponibles per a generar combinacions */
    private List<Integer> colorsTotals  = Arrays.asList(0, 1, 2, 3, 4, 5);
    
    /** Totes les possibles combinacions de un tamany concret */
    private List<List<Integer>> possiblesCombinacions = new ArrayList<>();
    
    /** Totes les possibles combinacions de un tamany concret */
    private List<List<Integer>> totalCombinacions = new ArrayList<>();
    
    /** Codi utilitzat anteriorment per resoldre el joc */
    private List<Integer> codiAnterior = new ArrayList<Integer>();
    
    /** Tamany d'una combinació */
    private Integer tamanyCombinacio = 4;
    
    /** Repetició o no de colors */
    private boolean repeticions = true;
    
    /** Nombre de colors de la combinació */
    private Integer nColors = 5;
    
    /** Resolucó entre dues combinacions */
    private Integer[] resolucio = new Integer[] {0,0};
    
    /** Guarda el nombre total d'intents que pot emplear */
    private int intents = 0;
    
    /**
	 * Creadora
	 * @param ip És l'informació de partida necessaria per a poder posar la dificultat que empleem
	 */
    public AlgoritmeFiveGuess (InfoPartida ip) {
    	switch(ip.getDificultat()) {
    	case FACIL:
    			this.nColors = 4;
    			this.repeticions = false;
    			break;
    	case INTERMIG:
    			this.nColors = 5;
    			this.repeticions = true;
    			break;
    	case DIFICIL:
    			this.nColors = 6;
    			this.repeticions = true;
    			break;
    	}
    	this.intents = ip.getTotalTorns();
    }
    
    public List<List<Integer>> getPossiblesCombinacions() {
    	return this.possiblesCombinacions;
    }
    
    /**
	 * Itera fins a trobar la solució o acabar els torns i retorna una estructura de dades amb totes les combinacions usades fins a arribar a la correcta.
	 * @param tamanyCombinacio Tamany que han de tenir totes les combinacions
	 * @param resolucio Indica les posicions correctes i incorrectes de l'intent anterior
	 * @param codiAnterior Estructura de dades que guarda el codi utilitzat anteriorment
	 * @param repeticions Indica si hi ha dhaver colors repetits o no
	 * @return retorna la combinació a provar òptima
	 */
    public List<List<Integer>> solve(List<Integer> codiSecret) {
    	int torn = 0;
    	List<Integer> proximIntent = new ArrayList<>();
    	if (repeticions) generarCombinacionsRepetides();
    	else generarCombinacionsNoRepetides();
    	combinacioInicial(proximIntent);
    	codiAnterior.addAll(proximIntent);
    	resolucio = compararCombinacions(codiSecret, codiAnterior);
    	totalCombinacions.add(new ArrayList<>(proximIntent));
    	++torn;
    	while (!proximIntent.equals(codiSecret) && intents > torn) {
    		possiblesCombinacions.removeIf(p -> !Arrays.equals(compararCombinacions(p, codiAnterior), resolucio));
    		minmax(proximIntent);
    		totalCombinacions.add(new ArrayList<>(proximIntent));
    		codiAnterior.clear();
        	codiAnterior.addAll(proximIntent);
        	resolucio = compararCombinacions(codiSecret, codiAnterior);
        	++torn;
    	}
    	return totalCombinacions;
    }
    
    /**
	 * Crea la combinacio inicial que pot eliminar mes possiblitats
	 * @param tamanyCombinacio Tamany que han de tenir totes les combinacions
	 * @param repeticions Indica si hi ha dhaver colors repetits o no
	 * @param novaCombinacio Estructura de dades on quedara guardat la resolució de la funció
	 */
    public void combinacioInicial(List<Integer> novaCombinacio) {
    	if (repeticions) {
    		int colors = 0;
    		for (int i = 0; i < tamanyCombinacio; i += 2) {
    			novaCombinacio.add(colorsTotals.get(colors));
    			if (tamanyCombinacio > i+1) novaCombinacio.add(colorsTotals.get(colors));
    			++colors;
    		}
    	}
    	else {
    		for (int i = 0; i < tamanyCombinacio; ++i) {
    			novaCombinacio.add(colorsTotals.get(i));
    		}
    	}
    }
    
    /**
	 * Crea les estructures de dades necessaries per a generar totes les possibles combinacions amb repeticions
	 * @param tamanyCombinacio Tamany que han de tenir totes les combinacions
	 */
    public void generarCombinacionsRepetides() {
    	List<Integer> resposta = new ArrayList<Integer>();
        gCR(0, resposta);
    }
    
    /**
	 * Backtracking per a generar totes les possibles combinacions d'un tamany en concret i amb repeticions
	 * @param tamanyCombinacio Tamany que han de tenir totes les combinacions
	 * @param i Index per iterar sobre el vector resposta
	 * @param resposta Vector on s'emmagatzeman les respostes
	 */
    
    private void gCR(int i, List<Integer> resposta) {
        if (i == tamanyCombinacio) possiblesCombinacions.add(new ArrayList<>(resposta));
        else {
        	for (int j = 0; j < nColors; ++j) {
        		resposta.add(colorsTotals.get(j));
        		gCR(i+1, resposta);
        		resposta.remove(resposta.size() - 1);
        	}
        }
    }
    
    /**
	 * Crea les estructures de dades necessaries per a generar totes les possibles combinacions sense repeticions
	 * @param tamanyCombinacio Tamany que han de tenir totes les combinacions
	 */
    public void generarCombinacionsNoRepetides() {
        boolean[] visitats = new boolean[tamanyCombinacio];
        Arrays.fill(visitats, false);
        List<Integer> resposta = new ArrayList<Integer>();
        gCnR(0, visitats, resposta);
    }
    
    
    /**
	 * Backtracking per a generar totes les possibles combinacions d'un tamany en concret i sense repeticions
	 * @param tamanyCombinacio Tamany que han de tenir totes les combinacions
	 * @param i Index per iterar sobre el vector resposta
	 * @param visitats Vector de colors visitats per comprovar si esta repetit
	 * @param resposta Vector on s'emmagatzeman les respostes
	 */
    private void gCnR(int i, boolean[] visitats, List<Integer> resposta) {
        if (i == tamanyCombinacio) possiblesCombinacions.add(new ArrayList<>(resposta));
        else {
        	for (int j = 0; j < nColors; ++j) {
        		if (!visitats[j]) {
        			resposta.add(colorsTotals.get(j));
        			visitats[j] = true;
        			gCnR(i+1, visitats, resposta);
        			visitats[j] = false;
        			resposta.remove(resposta.size() - 1);
        		}
        	}
        }
    }

    /**
	 * Evaluacio de totes les combinacions i tria de la millor d'elles
	 * @param proximIntent Estructura de dades on quedara guardat la resolució de la funció
	 */
    public void minmax(List<Integer> proximIntent) {
        Integer min = Integer.MAX_VALUE;
        HashMap<Integer, ArrayList<List<Integer>>> taulaPuntuacions = new HashMap<>();
        for (List<Integer> possibleCombinacio : possiblesCombinacions) {
            for (List<Integer> possibleIntent : possiblesCombinacions) {
            	Integer[] feedback = compararCombinacions(possibleIntent, possibleCombinacio);
                Integer key = feedback[0] * 10 + feedback[1];
                ArrayList<List<Integer>> codes = taulaPuntuacions.getOrDefault(key, new ArrayList<>());
                codes.add(possibleCombinacio);
                taulaPuntuacions.put(key, codes);
            }
        }
        for (List<Integer> possibleIntent : possiblesCombinacions) {
            HashMap<Integer, ArrayList<List<Integer>>> particions = new HashMap<>();
            for (List<Integer> possibleCombinacio : possiblesCombinacions) {
                if (!possibleCombinacio.equals(possibleIntent)) {
                	Integer[] feedback = compararCombinacions(possibleIntent, possibleCombinacio);
                    Integer key = feedback[0] * 10 + feedback[1];
                    ArrayList<List<Integer>> combinacions = taulaPuntuacions.get(key);
                    if (combinacions != null) {
                        particions.putIfAbsent(key, new ArrayList<>(combinacions));
                        particions.get(key).add(possibleCombinacio);
                    }
                }
            }
            Integer max = 0;
            for (ArrayList<List<Integer>> particio : particions.values()) {
                if (particio.size() > max) {
                    max = particio.size();
                }
            }
            if (max < min) {
                min = max;
                proximIntent.clear();
                proximIntent.addAll(possibleIntent);
            }
        }
    }
 
 
    /**
	 * Validacio d'una combinacio comparant-lo amb una segona segons la posicio dels parametres que les formen
	 * @param combinacio Combinacio que volem avaluar
	 * @param codiAnterior Combinacio amb la que la volem avaluar
	 * @return Retorna el resultat de la comparació on el primer component son les posicions correctes i el segon les incorrectes
	 */
    public Integer[] compararCombinacions(List<Integer> combinacio, List<Integer> codiAnterior) {
        Integer[] resultatComparacio = new Integer[] {0, 0};
        Integer size = combinacio.size();
        boolean[] indexUtilitzats = new boolean[5];
        for (int i = 0; i < size; i++) {
            if (combinacio.get(i).equals(codiAnterior.get(i))) {
                resultatComparacio[0]++;
                indexUtilitzats[i] = true;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j && combinacio.get(i).equals(codiAnterior.get(j)) && !indexUtilitzats[j]) {
                    resultatComparacio[1]++;
                    indexUtilitzats[j] = true;
                    break;
                }
            }
        }
        return resultatComparacio;
    }

}