package main.subgrup14_1.mastermind.domini.iteradors;

import main.subgrup14_1.mastermind.domini.interficies.SuggestionsIterator;
import main.subgrup14_1.mastermind.domini.models.Settings;
import main.subgrup14_1.mastermind.domini.models.Suggestion;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;

/**
 * 
 * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
 *
 */

public class Exhaustive implements SuggestionsIterator {

	
	boolean _eof;
	Settings settings;
	Suggestion current;  
	/**
	 * Construeix un Exhaustive iterator
	 * @param settings parametres de la partida
	 * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
	 */
	public Exhaustive(Settings settings) throws ExcepcioPartida{
		current = new Suggestion(settings);
		_eof = false;
		this.settings = settings;
	}
	
	/**
	 * Retorna el valor corrent
	 * @return Suggestion
	 */
    public Suggestion currentValue(){
		return current;
	}
    
    /**
     * Itera l'iterador al siguent valor
     * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
     */    
    public void next() throws ExcepcioPartida{
	   _eof = !current.increment();	
	}
    
    /**
     * Retorna True si l'iterador ha arribat al seu final, False en el cas contrari
     * @return boolean
     */
    public boolean eof(){
	  return _eof;	
	}
    
    /**
     * Resetea l'iterador al valor inicial
     * @throws ExcepcioPartida de la constructora Suggestion, si se invoca 
     */
	public void reset() throws ExcepcioPartida{
      current = new Suggestion(settings);
	  _eof = false;	
	}
	

}
