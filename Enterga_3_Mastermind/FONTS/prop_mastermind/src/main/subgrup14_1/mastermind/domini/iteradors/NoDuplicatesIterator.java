
package main.subgrup14_1.mastermind.domini.iteradors;

import main.subgrup14_1.mastermind.domini.interficies.SuggestionsIterator;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;

/**
 * 
 * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
 *
 */

public class NoDuplicatesIterator extends FilteredIterator {
	
	 /** 
		 * Construeix un NoDuplicatesIterator
		 * @param underlay anterior
		 * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
		 */
    public NoDuplicatesIterator(SuggestionsIterator underlay) throws ExcepcioPartida{
		super(underlay); 
		while(!valid())
			next();
	}

	@Override
	/**
	 * Comprova si el valor corrent de l'iterador es valid 
	 * @return boolean
	 */
	public 
	boolean valid(){
      return !currentValue().hasDuplicates();	
    };
    
    
	
    
}
