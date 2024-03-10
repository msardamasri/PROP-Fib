package main.subgrup14_1.mastermind.domini.models;

import java.util.List;
import java.util.ArrayList;

import main.subgrup14_1.mastermind.domini.interficies.Maquina;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.utils.Utils;

public final class Genetic implements Maquina  {
	public Settings settings;
	
	/**
	 * Constructora per defecte
	 * @param info Informacio de la partida
	 */
	public Genetic(InfoPartida info){
		this.settings = new Settings();
		settings.fromInfo(info);
	};	
	
	/**
	 * Resol el joc
	 */
	public List<List<Integer>> solve(List<Integer> solution) throws ExcepcioPartida{		
	    int move = 0;
		Answer answer = null;
		RandomCodeBreaker codeBreaker = new RandomCodeBreaker(settings);
		List<List<Integer>> list = new ArrayList<>();
		do {
		  Suggestion suggestion = codeBreaker.GetSuggestion();
		  answer = new Answer(settings);
		  answer.fromArray(Utils.compararCombinacions(solution, suggestion.toList()));
		  list.add(suggestion.toList());
		  if(answer.gameOver())
			  break;
		  codeBreaker.AnswerRecieved(answer);
		  move++;
		} while (!answer.gameOver() || (settings.maxMoves > 0 && move < settings.maxMoves));   
		
		return list;
	}
}
