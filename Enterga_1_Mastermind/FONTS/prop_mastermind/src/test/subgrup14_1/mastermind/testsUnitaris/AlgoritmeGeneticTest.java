package test.subgrup14_1.mastermind.testsUnitaris;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.interficies.SuggestionsIterator;
import main.subgrup14_1.mastermind.domini.iteradors.AnsweredIterator;
import main.subgrup14_1.mastermind.domini.iteradors.Exhaustive;
import main.subgrup14_1.mastermind.domini.iteradors.FilteredIterator;
import main.subgrup14_1.mastermind.domini.iteradors.NoDuplicatesIterator;
import main.subgrup14_1.mastermind.domini.models.Answer;
import main.subgrup14_1.mastermind.domini.models.Genetic;
import main.subgrup14_1.mastermind.domini.models.InfoPartida;
import main.subgrup14_1.mastermind.domini.models.RandomCodeBreaker;
import main.subgrup14_1.mastermind.domini.models.Settings;
import main.subgrup14_1.mastermind.domini.models.Suggestion;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;
import main.subgrup14_1.mastermind.utils.Pair;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * 
 * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
 *
 */

public class AlgoritmeGeneticTest {
	
	/**
	 * Objectiu: comprovar que els Settings generats coincideixen amb els plantejats 
	 */
	@Test
	public void TestSettingsEasy(){
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		assertTrue("Easy Settings are decoded badly!", 
				(settings.duplicationsAllowed == false && settings.combinationLength == 4 && settings.maxMoves == 12 && settings.numLetters == 4));
	};	
		
	
	/**
	 * Objectiu: Comprovar el métode Suggestion.equalTo per a comparar Suggestions
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestSuggestionEquals() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Suggestion suggestion = new Suggestion(settings);
		suggestion.fromString("0123");
		boolean testResult = suggestion.equalTo(suggestion);
		Suggestion sugg = new Suggestion(settings);
		sugg.fromString("3210");
		boolean testResult2 = !suggestion.equalTo(sugg);
		assertTrue("Suggestion equality check is not working properly!", (testResult && testResult2));		
	};
	/**
	 * Objectiu: Comprovar el métode Suggestion.fromSuggestion per a copiar el valor d'un Suggestion a l'altre
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestSuggestionSuggestion() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Suggestion suggestion = new Suggestion(settings);
		suggestion.fromString("0123");
		Suggestion sugg = new Suggestion(settings);
		sugg.fromString("3210");
		suggestion.fromSuggestion(sugg);
		assertTrue("Suggestion Suggestion is notr working properly!", suggestion.equalTo(sugg));
	};
	
	/**
	 * Objectiu: Comprovar el métode Suggestion.fromString per a convertir el valor d'un String al value del Suggestion i al revers
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestSuggestionString() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		String str = "0123";
		Settings settings = new Settings();
		settings.fromInfo(info);
		Suggestion suggestion = new Suggestion(settings);
		suggestion.fromString(str);
		assertTrue("Suggestion String is not working properly!", suggestion.toString().equals(str));
	};
	
	/**
	 * Objectiu: Comprovar el métode Suggestion.fromList i Suggestion.toList per a convertir el valor d'un String al value del Suggestion i al revers
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestSuggestionList() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Suggestion suggestion = new Suggestion(settings);
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		suggestion.fromList(list);
		assertTrue("Suggestion List is working incorrectly!", suggestion.toList().equals(list));
	};
	
	/**
	 * Objectiu: Comprovar el métode Suggestion.Check per a validar els valors posibles
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test 
	public void TestSuggestionValues() throws ExcepcioPartida {
		boolean testResult = false;
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		String str = "ABCD";
		Settings settings = new Settings();
		settings.fromInfo(info);
		Suggestion suggestion = new Suggestion(settings);
		try {
		  suggestion.fromString(str);
		  testResult = false;
		}
		catch(ExcepcioPartida e){
			testResult = true; //expected exception 
		};
		
		boolean testResult2 = false;
		str = "0123";
		try {
		  suggestion.fromString(str);
		  testResult2 = true;
		}
		catch(ExcepcioPartida e){
		  testResult2 = false; //unexpected exception 
		};
			
		assertTrue("Suggestion value validation is not working properly!", (testResult && testResult2));
	}; 
	
	/**
	 * Objectiu: Comprovar el métode Suggestion.Check per a validar els valors posibles
	 * @throws ExcepcioPartida si la constructora falla
	 */	
	@Test
	public void TestSuggestionLength() throws ExcepcioPartida {
		boolean testResult = false;
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		String str = "01230";
		Settings settings = new Settings();
		settings.fromInfo(info);
		Suggestion suggestion = new Suggestion(settings);
		try {
			suggestion.fromString(str);
			testResult = false;
			
		}
		catch(ExcepcioPartida e){
			testResult = true; //expected exception
		}
			
		boolean testResult2 = true;
		str = "012";
		try {
			suggestion.fromString(str);
			testResult2 = false;
		}
		catch(ExcepcioPartida e) {
			testResult2 = true; //expected exception
		};
		
		boolean testResult3 = true; 
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(2);
		try{
			suggestion.fromList(list);
			testResult3 = false;
		}
		catch(ExcepcioPartida e) {
			testResult3 = true; //expected exception
		}
		
		boolean testResult4 =  true;
		list.clear();
		list.add(0);
		list.add(1);
		list.add(2);
		try{
			suggestion.fromList(list);
			testResult3 = false;
		}
		catch(ExcepcioPartida e) {
			testResult3 = true; //expected exception
		}
		
		boolean testResult5 = true;
		list.clear();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		try{
			suggestion.fromList(list);
			testResult5 = true;
		}
		catch(ExcepcioPartida e) {
			testResult5 = false; //unexpected exception
		}
		
		
		
		boolean testResult6 = true;
		str = "0123";
		try {
			suggestion.fromString(str);
			testResult3 = true;
		}
		catch(ExcepcioPartida e) {
			testResult3 = false; //unexpected exception
		};
		
		assertTrue("Suggestion length validation is not working properly!",(testResult && testResult2 && testResult3 && testResult4 && testResult5 && testResult6));		
	};
	
	/**
	 * Objectiu: Comprovar el métode Suggestion.HasDoubles per a comprovar si conte valors duplicats
	 * @throws ExcepcioPartida si la constructora falla
	*/	
	@Test
	public void TestSuggestionDoubles() throws ExcepcioPartida {
		boolean testResult = false;
		InfoPartida info = new InfoPartida(null, null, Dificultat.DIFICIL, null);
		String str = "0011";
		Settings settings = new Settings();
		settings.fromInfo(info);
		Suggestion suggestion = new Suggestion(settings);
		suggestion.fromString(str);
		testResult = suggestion.hasDuplicates();
		
		boolean testResult2 = false;
		str = "0123";
		suggestion.fromString(str);
		testResult2 = !suggestion.hasDuplicates();
		
		assertTrue("Suggestion doubles detection is not working properly!" ,(testResult && testResult2));
	};
	
	/**
	 * Objectiu: comprovar el metode Suggestion.increment per a incrementar el valor a 1 pas
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestSuggestionIncrement() throws ExcepcioPartida {
		boolean testResult = false;
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		boolean eof = false;
		settings.fromInfo(info);
		Suggestion suggestion = new Suggestion(settings);
		suggestion.fromString("0000");
		eof = suggestion.increment();
		Suggestion sugg = new Suggestion(settings);
		sugg.fromString("0001");
		testResult = (suggestion.equalTo(sugg) && eof);
		
		boolean testResult2 = false;
		suggestion.fromString("0123");
		eof = suggestion.increment();
		sugg.fromString("0130");
		testResult2 = (suggestion.equalTo(sugg) && eof);
		
		boolean testResult3 = false;
		suggestion.fromString("3333");
		testResult3 = !suggestion.increment();
		
		assertTrue("Suggestion increment is not working properly!", (testResult && testResult2 && testResult3));		
	};	
	
	/**
	 * Objectiu: comprovar la funcio Suggestion.randomize per a generar un valor aleatori
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestSuggestionRandom() throws ExcepcioPartida {
		Settings settings = new Settings();
		Set<String> set = new HashSet<String>();
		int coincidences = 0;
		Suggestion suggestion = new Suggestion(settings);
		for(int i = 0; i<1000; i++) {			
			suggestion.randomize();
			if(!set.contains(suggestion.toString()))
					set.add(suggestion.toString());
			else
				coincidences++;
		};
		
		assertTrue("Suggestion randomize is not working properly!", coincidences<=10); 
	};

	/**
	 * Comprovar tots els metodes del Suggestion
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestSuggestion() throws ExcepcioPartida {
		TestSuggestionEquals();
		TestSuggestionString();
		TestSuggestionDoubles();
		TestSuggestionIncrement();
		TestSuggestionLength();
		TestSuggestionList();
		TestSuggestionSuggestion();
		TestSuggestionValues();
		TestSuggestionRandom();
	};
	
	/**
	 * Objectiu: comprovar el metide Answer.fromString i Answer.toString per a convertir el valor d'un String al valor de l'Answer i al revers
	 * @throws ExcepcioPartida si la constrictora falla
	 */
	@Test
	public void TestAnswerString() throws ExcepcioPartida {
		String str = "01";
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Answer answer = new Answer(settings);
		answer.fromString(str);
		assertTrue("Answer String is not working properly!", answer.toString().equals(str));
	};
	

	@Test
	public void TestAnswerEqualTo() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Answer answer = new Answer(settings);
		answer.fromString("01");
		Answer answr = new Answer(settings);
		answr.fromString("00");
		boolean testResult = !answer.equalTo(answr);
		boolean testResult2 = answer.equalTo(answer);
		assertTrue("Answer equality check is not working properly!", (testResult && testResult2));
	};
	
	@Test
	public void TestAnswerLength() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Answer answer = new Answer(settings);
		boolean testResult = false;
		try {
			answer.fromString("100");
			testResult = false;
		}
		catch(ExcepcioPartida e) {
			testResult = true; //expected exception
		};
		
		boolean testResult2 = false;
		try {
			answer.fromString("1");
			testResult2 = false;
		}
		catch(ExcepcioPartida e) {
			testResult2 = true; //excepted exception
		};
		
		boolean testResult3 = false;
		try {
			answer.fromString("01");
			testResult3 = true;
		}
		catch(ExcepcioPartida e) {
			testResult3 = false; //unexpected exception
		};
		
		Pair<Integer, Integer> pair = new Pair<>(0, 1);
		boolean testResult4 = false;
		try {
			answer.fromPair(pair);
			testResult4 = true;
		}
		catch(ExcepcioPartida e) {
			testResult4 = false; //unexpected exception
		};
		
		boolean testResult5 = false;
		Integer[] array = new Integer[3];
		array[0] = 0;
		array[1] = 1;
		array[2] = 2;		
		try {
			answer.fromArray(array);
			testResult5 = false;			
		}
		catch(ExcepcioPartida e) {
			testResult5 = true; //expected exception
		};
		
		boolean testResult6 = false;
		array = new Integer[1];
		array[0] = 1;
		try {
			answer.fromArray(array);
			testResult6 = false;			
		}
		catch(ExcepcioPartida e) {
			testResult6 = true; //expected exception
		};
		
		boolean testResult7 = false;
		array = new Integer[2];
		array[0] = 0;
		array[1] = 1;
		try {
			answer.fromArray(array);
			testResult7 = true;			
		}
		catch(ExcepcioPartida e) {
			testResult7 = false; //unexpected exception
		};
		assertTrue("Answer length validation is not working properly!", (testResult && testResult2 && testResult3 && testResult4 && testResult5 && testResult6 && testResult7));
	};
	
	@Test
	public void TestAnswerValues() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Answer answer = new Answer(settings);
		
		boolean testResult = false;
		try {
			answer.fromString("AB");
			testResult = false;
		} catch (ExcepcioPartida e) {
			testResult = true; //expected exception
		}
		
		boolean testResult2 = false;
		try {
			answer.fromString("34");
			testResult2 = false;
		}
		catch(ExcepcioPartida e) {
			testResult2 = true; //expected exception
		};
		
		boolean testResult3 = false;
		try {
			answer.fromString("60");
			testResult3 = false;
		}
		catch(ExcepcioPartida e) {
			testResult3 = true; //expected exception
		};
		
		boolean testResult4 = false;
		try {
			answer.fromString("06");
			testResult4 = false;
		}
		catch(ExcepcioPartida e) {
			testResult4 = true; //expected exception
		};
		
		boolean testResult5 = false;
		try {
			answer.fromString("12");
			testResult5 = true;
		}
		catch(ExcepcioPartida e) {
			testResult5 = false; //unexpected exception
		};
		
		assertTrue("Answer value validation is not working properly!", (testResult && testResult2 && testResult3 && testResult4 && testResult5));
	};
	
	/**
	 * Objectiu: comprovar el metide Answer.fromArray i Answer.toArray per a convertir el valor d'un array al valor de l'Answer i al revers
	 * @throws ExcepcioPartida si la constrictora falla
	 */
	@Test
	public void TestAnswerArray() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Answer answer = new Answer(settings);
		Integer[] array = new Integer[2];
		array[0] = 0;
		array[1] = 1;
		answer.fromArray(array);
		assertTrue("Answer Array is not working properly!", (answer.toArray()[0] == array[0]) && answer.toArray()[1] == array[1]);
	};
	
	/**
	 * Objectiu: comprovar el metide Answer.fromPair i Answer.toString per a convertir el valor d'un Pair al valor de l'Answer i al revers
	 * @throws ExcepcioPartida si la constrictora falla
	 */
	@Test
	public void TestAnswerPair() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Answer answer = new Answer(settings);
		Pair<Integer, Integer> pair = new Pair<>(0, 1);
		answer.fromPair(pair);
		assertTrue("Answer Pair is not working properly!", (answer.value.getL() == pair.getL() && answer.value.getR() == pair.getR()));		
	};
	
	/**
	 * Objectiu: comprovar el metide Answer.fromAnswer per a copiar el valor d'un Answer a l'altre
	 * @throws ExcepcioPartida si la constrictora falla
	 */
	@Test
	public void TestAnswerAnswer() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Answer answer = new Answer(settings);
		answer.fromString("01");
		Answer answr = new Answer(settings);
		answr.fromString("10");
		answer.fromAnswer(answr);
		assertTrue("Answer Answer is not working properly!", answer.equalTo(answr));		
	};
	
	/**
	 * Objectiu: comprovar el metode Answer.gameOver que retorna true o false d'acord amb si el valor de l'Answer es el ganador
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestAnswerGameOver() throws ExcepcioPartida {
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Settings settings = new Settings();
		settings.fromInfo(info);
		Answer answer = new Answer(settings);
		answer.fromString("40");		
		boolean testResult = answer.gameOver();
		answer.fromString("04");
		boolean testResult2 = !answer.gameOver();
		answer.fromString("12");
		boolean testResult3 = !answer.gameOver();
		assertTrue("Answer gameOver is not working properly!", (testResult && testResult2 && testResult3));
	};
	
	
	/**
	 * Objectiu: comprovar el metide Answer.fromString i Answer.toString per a convertir el valor d'un String al valor de l'Answer i al revers
	 * @throws ExcepcioPartida si la constrictora falla
	 */
	@Test
	public void TestAnswer() throws ExcepcioPartida {
		TestAnswerString();
		TestAnswerEqualTo();
		TestAnswerValues();
		TestAnswerArray();
		TestAnswerGameOver();
		TestAnswerLength();
		TestAnswerPair();
		TestAnswerAnswer();
	};
	
	/**
	 * Objectiu: comprovar el metode Genetic.solve per la dificultat FACIL
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestRandomEasy() throws ExcepcioPartida{
		InfoPartida info = new InfoPartida(null, null, Dificultat.FACIL, null);
		Genetic rand = new Genetic(info);
		rand.settings.maxMoves = 0;
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		
		boolean testResult = false;
		List<List<Integer>> suggestions = new ArrayList<>();
		
		try {
			suggestions = rand.solve(list);
			testResult = true;
		} catch (ExcepcioPartida e) {
			testResult = false; //unexpected exception			
		};
		
		boolean testResult2 = true;
		if(suggestions.size() != 0) {
		  for(int i = 0; i<4; i++) {
			if(!suggestions.get(suggestions.size()-1).get(i).equals(list.get(i))) {
			  testResult2 = false;
			  break;
			};
		  }
		}
		else {
			testResult2 = false; //unexpected exception
		};	
		
		assertTrue("Random breaker invoker EASY is not working properly!", (testResult && testResult2));		
	};
	
	/**
	 * Objectiu: comprovar el metode Genetic.solve per la dificultat Intermig
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestRandomIntermediate(){
		InfoPartida info = new InfoPartida(null, null, Dificultat.INTERMIG, null);
		Genetic rand = new Genetic(info);
		rand.settings.maxMoves = 0;
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(2);
		list.add(2);
		list.add(4);
		
		boolean testResult = false;
		List<List<Integer>> suggestions = new ArrayList<>();
		
		try {
			suggestions = rand.solve(list);
			testResult = true;
		} catch (ExcepcioPartida e) {
			testResult = false; //unexpected exception			
		};
		
		boolean testResult2 = true;
		if(suggestions.size() != 0) {
		  for(int i = 0; i<4; i++) {
			if(!suggestions.get(suggestions.size()-1).get(i).equals(list.get(i))) {
			  testResult2 = false;
			  break;
			};
		  }
		}
		else {
			testResult2 = false; //unexpected exception
		};
		
		assertTrue("Random breaker invoker INTERMEDIATE is not working properly!", (testResult && testResult2));		
	};
	
	/**
	 * Objectiu: comprovar el metode Genetic.solve per la dificultat FACIL
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestRandomDifficult(){
		InfoPartida info = new InfoPartida(null, null, Dificultat.DIFICIL, null);
		Genetic rand = new Genetic(info);
		rand.settings.maxMoves = 0;
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(4);
		list.add(4);
		list.add(5);
		
		boolean testResult = false;
		List<List<Integer>> suggestions = new ArrayList<>();
		
		try {
			suggestions = rand.solve(list);
			testResult = true;
		} catch (ExcepcioPartida e) {
			testResult = false; //unexpected exception			
		};
		
		boolean testResult2 = true;
		if(suggestions.size() != 0) {
		  for(int i = 0; i<4; i++) {
			if(!suggestions.get(suggestions.size()-1).get(i).equals(list.get(i))) {
			  testResult2 = false;
			  break;
			};
		  }
		}
		else {
			testResult2 = false; //unexpected exception
		};
		
		assertTrue("Random breaker invoker DIFFICULT is not working properly!", (testResult && testResult2));
	};
	
	/**
	 * Objectiu: comprovar el metode Genetic.solve
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestRandom() throws ExcepcioPartida{
		TestRandomEasy();
		TestRandomIntermediate();
		TestRandomDifficult();
	};
	
	
	/**
	 * Objectiu: comprovar el metode RandomCodeBreaker.Cardinality que retorna el nombre de les combinacions possibles dins de l'iterator
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestRandomCodeBreakerCardinality() throws ExcepcioPartida{
		Settings settings = new Settings();
		SuggestionsIterator variants = new Exhaustive(settings);
				
		variants.reset();
		int cardinal = 0;
		while (!variants.eof()) {
		  cardinal++;
		  variants.next();
		}
		assertTrue("Random breaker EASY cardinality check is not working properly!", cardinal == RandomCodeBreaker.Cardinality(variants));	
	};
	
	
	/**
	 * Objectiu: comprovar el metode RandomCodeBreaker.PeekRandom que retorna una combinacio aleatoria de l'iterator de todes les combinaciones possibles
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void	TestRandomCodebreakerPeekRandom() throws ExcepcioPartida {
		Settings settings = new Settings();
		settings.combinationLength = 4;
		settings.numLetters = 8;
		SuggestionsIterator variants = new Exhaustive(settings);
		
		Suggestion suggestion = RandomCodeBreaker.PeekRandom(variants);
	    boolean testResult = false;
	    while(!variants.eof()) {
	    	if(variants.currentValue().equalTo(suggestion)) {
	    		testResult = true;
	    		break;
	    	};
	    	variants.next();	    		
	    };
	    
	    assertTrue("Random breaker Peek is not working properly!", testResult);
	};
	
	/**
	 * Objectiu: comprovar el metode RandomCodeBreaker.PeekRandom que retorna una combinacio aleatoria de l'iterator de todes les combinaciones possibles
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void	TestRandomCodeBreakerAnswerRecieved() throws ExcepcioPartida {
		Settings settings = new Settings();
		settings.combinationLength = 4;
		settings.numLetters = 8;
		SuggestionsIterator variants = new Exhaustive(settings);
		RandomCodeBreaker codeBreaker = new RandomCodeBreaker(settings);
		Suggestion suggestion = codeBreaker.GetSuggestion();
		Suggestion sugg = new Suggestion(settings);
		sugg.randomize();
		Answer answer = new Answer(settings);
		
		answer.fromArray(Utils.compararCombinacions(suggestion.toList(), sugg.toList()));
		
		variants = new AnsweredIterator(variants, suggestion, answer, settings);
		codeBreaker.AnswerRecieved(answer);
		suggestion = codeBreaker.GetSuggestion();
		
	
		boolean testResult = false;
		while(!variants.eof()) {
			if(variants.currentValue().equalTo(suggestion)) {
	    		testResult = true;
	    		break;
	    	};
	    	variants.next();	    		
	    };
	    
		assertTrue("Random breaker AnswerRecieved is not working properly!", testResult);
	};
	
	/**
	 * Objectiu: comprovar el metode RandomCodeBreaker.GetSuggestion que retorna una combinacio per a provar en el joc
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void	TestRandomCodeBreakerGetSuggestion() throws ExcepcioPartida {
		Settings settings = new Settings();
		settings.combinationLength = 4;
		settings.numLetters = 8;
		RandomCodeBreaker codeBreaker = new RandomCodeBreaker(settings);
		SuggestionsIterator variants = new Exhaustive(settings);
		
		Suggestion suggestion = codeBreaker.GetSuggestion();
	    boolean testResult = false;
	    while(!variants.eof()) {
	    	if(variants.currentValue().equalTo(suggestion)) {
	    		testResult = true;
	    		break;
	    	};
	    	variants.next();	    		
	    };	    
	    assertTrue("Random breaker GetSuggestion is not working properly!", testResult);	
	};
	
	/**
	 * Objectiu: comprovar tots els metodes de RandomCodeBreaker
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestRandomCodeBreaker() throws ExcepcioPartida {
		TestRandomCodeBreakerCardinality();
		TestRandomCodebreakerPeekRandom();
		TestRandomCodeBreakerAnswerRecieved();
		TestRandomCodeBreakerGetSuggestion();
	};
	
	/**
	 * Objectiu: comprovar que l'iterator conte el nombre correcte de combinacions
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void	TestExhaustiveIterator() throws ExcepcioPartida {
		Settings settings = new Settings();
		settings.combinationLength = 4;
		settings.numLetters = 8;
		SuggestionsIterator variants = new Exhaustive(settings);
		
		int variantsCount = 0;
		while(!variants.eof()) {
			variantsCount++;		
			variants.next();
		};		
		
		assertTrue("Exhaustive iterator is not working properly!", (4096 == variantsCount));		
	};
	
	/**
	 * Objectiu: comprovar que l'iterator conte el nombre correcte de combinacions
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestNoDuplicatesIterator() throws ExcepcioPartida {
		Settings settings = new Settings();
		settings.combinationLength = 4;
		settings.numLetters = 8;
		SuggestionsIterator variants = new Exhaustive(settings);
		variants = new NoDuplicatesIterator(variants);
		
		int variantsCount = 0;
		while(!variants.eof()) {
			variantsCount++;		
			variants.next();
		};		
		
		assertTrue("NoDuplicated iterator is not working properly!", (1680 == variantsCount));
	};
	
	/**
	 * Objectiu: comprovar que l'iterator conte el nombre correcte de combinacions
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void	TestFilteredIterator() throws ExcepcioPartida {
		Settings settings = new Settings();
		settings.combinationLength = 4;
		settings.numLetters = 8;
		SuggestionsIterator variants = new Exhaustive(settings); 
		variants = new FilteredIterator(variants);
		
		int variantsCount = 0;
		while(!variants.eof()) {
			variantsCount++;		
			variants.next();
		};		
		assertTrue("Filtered iterator is not working properly!", (4096 == variantsCount));		
	};
	
	/**
	 * Objectiu: comprovar que l'iterator conte el nombre correcte de combinacions
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestAnsweredIterator() throws ExcepcioPartida {
		Settings settings = new Settings();
		settings.combinationLength = 4;
		settings.numLetters = 8;
		SuggestionsIterator variants = new Exhaustive(settings);
		Suggestion suggestion = new Suggestion(settings);
		Suggestion sugg = new Suggestion(settings);
		suggestion.fromString("0123");
		sugg.fromString("3210");
		Answer answer = new Answer(settings);
		answer.fromArray(Utils.compararCombinacions(suggestion.toList(), sugg.toList()));
		variants = new AnsweredIterator(variants, suggestion, answer, settings);
		
		int variantsCount = 0;
		while(!variants.eof()) {
			variantsCount++;		
			variants.next();
		};		
		assertTrue("Answered iterator is not working properly!", (10 == variantsCount));		
		
	};
	
	/**
	 * Objectiu: comprovar tots els iterators
	 * @throws ExcepcioPartida si la constructora falla
	 */
	@Test
	public void TestIterator() throws ExcepcioPartida{
	    	TestExhaustiveIterator();
		    TestNoDuplicatesIterator();
		    TestFilteredIterator();
		    TestAnsweredIterator();
	};
}
