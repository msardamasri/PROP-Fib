
package main.subgrup14_1.mastermind.domini.iteradors;

import main.subgrup14_1.mastermind.domini.interficies.SuggestionsIterator;
import main.subgrup14_1.mastermind.domini.models.Suggestion;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;

/**
 * 
 * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
 *
 */



public class FilteredIterator implements SuggestionsIterator {

	SuggestionsIterator underlay;
	
	/** 
	 * Construeix un FilteredIterator
	 * @param underlay iterador anterior
	 * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
	 */	
	public FilteredIterator(SuggestionsIterator underlay) throws ExcepcioPartida{
	  this.underlay = underlay;
	  this.underlay.reset();
	}
	
	@Override
	/**
	 * Retorna el valor corrent
	 * @return Suggestion
	 */ 
	public Suggestion currentValue() {
		return underlay.currentValue();
	}

	/**
	 * Comprova si el valor corrent de l'iterador es valid 
	 * @return boolean
	 * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
	 */	
	public boolean valid() throws ExcepcioPartida {
		  return true;	
	}

	@Override
	/**
     * Itera l'iterador al siguent valor
     * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
     */	
	public void next() throws ExcepcioPartida {
		do {
		  underlay.next();	
		} while(!eof() && !valid());  
	}


	@Override
	/**
     * Retorna True si l'iterador no te mes valors per iterar, False en cas contrari
     * @return boolean
     */
	public boolean eof() {
		return underlay.eof();
	}

	@Override
	/**
     * Resetea l'iterador al valor inicial
     * @throws ExcepcioPartida de la constructora Suggestion, si se invoca 
     */	
	public void reset() throws ExcepcioPartida{
		// TODO Auto-generated method stub
		underlay.reset();
		while (!eof() && !valid())
		  next();
	}

	



	
	

}
