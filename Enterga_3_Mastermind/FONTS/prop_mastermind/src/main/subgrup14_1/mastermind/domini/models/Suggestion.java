
package main.subgrup14_1.mastermind.domini.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;


/**
 * 
 * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
 *
 */


public final class Suggestion {
	
	public static String alphabeth = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	Random rand = new Random(System.nanoTime());
	
	public int[] value;
    Settings settings;
    
    /**
	   * Comprova el valor del Suggestion
	   * @throws ExcepcioPartida si el valor es incorrect
	   */
    public void Check() throws ExcepcioPartida{
		  if (value.length != settings.combinationLength)
			  throw new ExcepcioPartida("Invalid suggestion length!");
	       for (int i=0;i<settings.combinationLength;i++) {
	     	   if (value[i] < 0)
	 			  throw new ExcepcioPartida("Negative letters are not supported!");
	     	   if (value[i] >= settings.numLetters)
	 			  throw new ExcepcioPartida("Too many letters!");
	       }
	}
    
    /**
	   * Construeix un nou Suggestion
	   * @param settings informacio dobre la partida
	   * @throws ExcepcioPartida en el caso de Check fallado
	   */
	public Suggestion(Settings settings) throws ExcepcioPartida{
		value = new int[settings.combinationLength];
		this.settings = settings;
		Check();
	}
	
	/**
	 * Copia els valors del List a this.value
	 * @param value Intent
	 * @throws ExcepcioPartida si el tamany del List no es valid
	 */
	public void fromList(List<Integer> value) throws ExcepcioPartida{
	  if(value.size() != settings.combinationLength)
		  throw new ExcepcioPartida("Invalid suggestion length!");
	  for(int i = 0; i<settings.combinationLength; i++) {
		  this.value[i] = value.get(i);		  
	  };
	  Check();
	};
	
	/**
	 * Passa el value a llista
	 * @return Valor transformat en llista
	 */
	public List<Integer> toList(){
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i<settings.combinationLength; i++) {
			list.add(value[i]);			
		};
		return list;		
	};
	
	/**
	 * Passa el value a String
	 * @return String
	 */
	public String toString() {
		String result = "";
		  for (int i=0;i<settings.combinationLength;i++) 
			  result = result + alphabeth.charAt(value[i]);  
		return result;
	}

	/**
	 * Passa el String a Suggestion
	 * @param s string a passar
	 * @throws ExcepcioPartida si falla el Check
	 */
	public void fromString(String s) throws ExcepcioPartida{
	   if(s.length() != settings.combinationLength)
		   throw new ExcepcioPartida("Invalid suggestion length!");
       for (int i=0;i<settings.combinationLength;i++)
     	   value[i] = alphabeth.indexOf(s.charAt(i));
       Check();
	}
	
	/**
	 * Comprova si value tiene valors duplicats
	 * @return boolean
	 */
	public boolean hasDuplicates() {
	  for (int i=0;i<settings.combinationLength;i++) 
		for (int j=i+1;j<settings.combinationLength;j++)
		  if (value[i]==value[j])
			  return true;
	  return false;
	}
	
	/**
	 * Cambia el value de Suggestion a valors generats aleatoriament
	 */
	public void randomize() {
		do {
	        for(int i = 0; i<settings.combinationLength; i++) 
	        	value[i] = rand.nextInt(settings.numLetters);
		} while (settings.duplicationsAllowed ? false : hasDuplicates());    
	}
	

	/**
	 * Incrementa value a 1 pas i retorna si es el valor máxim o no
	 * @return boolean
	 * @throws ExcepcioPartida si falla Check
	 */
	public boolean increment() throws ExcepcioPartida {
      for (int i = settings.combinationLength - 1; i >=0; i--) {
  		value[i]++;
  		if (value[i] < settings.numLetters)
  		  return true;
        value[i] = 0;
      }		
      Check();
  	  return false;
	}
	
	/**
	 * /**
	 * Copia Suggestion.value() a this.value()
	 * @param suggestion Intent
	 */
	public void fromSuggestion(Suggestion suggestion) {
      for(int i = 0; i<settings.combinationLength; i++) 
       	value[i] = suggestion.value[i];
	}
	
	/**
	 * Compara el Suggestion.value a this.value
	 * @param suggestion intent
	 * @return boolea
	 */
	public boolean equalTo(Suggestion suggestion) {
		for (int i = 0; i<settings.combinationLength; i++)
		      if (value[i] != suggestion.value[i]) 
		   	  return false;
		 return true;
	}	
}
