package main.subgrup14_1.mastermind.domini.models;

import java.util.Random;

import main.subgrup14_1.mastermind.domini.interficies.SuggestionsIterator;
import main.subgrup14_1.mastermind.domini.iteradors.AnsweredIterator;
import main.subgrup14_1.mastermind.domini.iteradors.Exhaustive;
import main.subgrup14_1.mastermind.domini.iteradors.NoDuplicatesIterator;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;

public class RandomCodeBreaker{

    Settings settings;
    Suggestion nextSuggestion;
    SuggestionsIterator variants;
	static Random rand = new Random(System.nanoTime());
    
	
	public RandomCodeBreaker(Settings settings) throws ExcepcioPartida{
	  this.settings = settings;
	  
	  nextSuggestion = new Suggestion(settings);
	  nextSuggestion.randomize();
	  
	  variants = new Exhaustive(settings);
	  if (!settings.duplicationsAllowed)
	    variants = new NoDuplicatesIterator(variants);
	}
	
	
	public Suggestion GetSuggestion() {
	  return nextSuggestion;	
	}
	
	public static int Cardinality(SuggestionsIterator variants) throws ExcepcioPartida {
		variants.reset();
		int i = 0;
		while (!variants.eof()) {
		  i++;
		  variants.next();
		}
		return i;
	}
	
	public static  Suggestion PeekRandom(SuggestionsIterator variants) throws ExcepcioPartida{
      int cardinality = Cardinality(variants);	
      if (cardinality == 0)
     	  throw new ExcepcioPartida("Iterator is empty!");

      int v = rand.nextInt(cardinality);
      variants.reset();
	  int i = 0;
      while (!variants.eof()) {
		if (i==v) 
		  return variants.currentValue();
        i++;
		variants.next();
     }
     return null;
	}
	
	public void AnswerRecieved(Answer anwser) throws ExcepcioPartida{
		variants = new AnsweredIterator(variants, nextSuggestion, anwser, settings);
		nextSuggestion = PeekRandom(variants);
	}
}
