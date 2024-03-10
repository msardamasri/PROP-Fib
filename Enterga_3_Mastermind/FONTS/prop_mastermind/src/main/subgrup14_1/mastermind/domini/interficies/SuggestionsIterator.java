
package main.subgrup14_1.mastermind.domini.interficies;

import main.subgrup14_1.mastermind.domini.models.Suggestion;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;

 /**
  * 
  * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
  *
  */

public interface SuggestionsIterator {
	/**
	 * Retorna el valor corrent
	 * @return Suggestion
	 */
    Suggestion currentValue();
    
    /**
     * Itera l'iterador al siguent valor
     * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
     */
    void next() throws ExcepcioPartida ;
    
    /**
     * Retorna True si l'iterador no te mes valors per iterar, False en cas contrari
     * @return boolean
     */
    boolean eof();
    
    /**
     * Resetea l'iterador al valor inicial
     * @throws ExcepcioPartida de la constructora Suggestion, si se invoca 
     */
	void reset() throws ExcepcioPartida;
	
	
}

