package main.subgrup14_1.mastermind.domini.iteradors;

import main.subgrup14_1.mastermind.domini.interficies.SuggestionsIterator;
import main.subgrup14_1.mastermind.domini.models.Answer;
import main.subgrup14_1.mastermind.domini.models.Settings;
import main.subgrup14_1.mastermind.domini.models.Suggestion;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * 
 * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
 *
 */

public class AnsweredIterator extends FilteredIterator {

	Answer answer;
	Suggestion suggestion;
	Settings settings;
	
	/**
	 * Construeix un AnsweredIterator
	 * @param underlay iterator anterior
	 * @param suggestion la combinacio anterior
	 * @param answer rebut pel la combinacio anterior
	 * @param settings parametres de la partida
	 * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
	 */
	public AnsweredIterator(SuggestionsIterator underlay, Suggestion suggestion, Answer answer, Settings settings) throws ExcepcioPartida {
		super(underlay);
		this.answer = answer;
		this.suggestion = suggestion;
		this.settings = settings;
	}
	
	
	@Override
	/**
	 * Comprova si el valor corrent de l'iterador es valid 
	 * @return boolean
	 * @throws ExcepcioPartida de la constructora Suggestion, si se invoca
	 */
	public boolean valid() throws ExcepcioPartida {
	  Answer ansr = new Answer(settings);
	  ansr.fromArray(Utils.compararCombinacions(currentValue().toList(), suggestion.toList()));
	  return answer.equalTo(ansr) 
        && !suggestion.equals(currentValue());	    
    };
    
   
}
