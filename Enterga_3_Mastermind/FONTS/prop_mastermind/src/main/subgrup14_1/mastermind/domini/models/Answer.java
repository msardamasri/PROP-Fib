package main.subgrup14_1.mastermind.domini.models;

import main.subgrup14_1.mastermind.utils.Pair;
import main.subgrup14_1.mastermind.excepcions.ExcepcioPartida;

/**
 * 
 * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
 *
 */

public final class Answer {
	public static String alphabeth = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public Pair<Integer, Integer> value;
	public Settings settings;
	  
	  /**
	   * Comprova el valor del Answer
	   * @throws ExcepcioPartida si el valor es incorrect
	   */
	  void Check() throws ExcepcioPartida{
		  if (value.getL() > settings.combinationLength || value.getL() < 0)
			  throw new ExcepcioPartida("Invalid number of correct values");
		  if (value.getR() > settings.combinationLength || value.getR() < 0)
			  throw new ExcepcioPartida("Invalid number of partially correct values");
		  if(value.getL() + value.getR() > settings.combinationLength)
			  throw new ExcepcioPartida("Invalid answer value1");
	  };
	  
	  /**
	   * Construeix un nou Answer
	   * @param settings informacio dobre la partida
	   * @throws ExcepcioPartida en el caso de Check fallado
	   */
	public Answer(Settings settings) throws ExcepcioPartida{
			value = new Pair<Integer, Integer>(0, 0);
			this.settings = settings;
			Check();
		}
	
	/**
	 * Passa el value a String
	 * @return String
	 */
	public String toString() {
		String result = "";
		result = result + alphabeth.charAt(value.getL());
		//result = result + " ";
		result = result + alphabeth.charAt(value.getR());
	  return result;
	}

	/**
	 * Passa el String a Answer
	 * @param s string a passar
	 * @throws ExcepcioPartida si falla el Check
	 */
	public void fromString(String s) throws ExcepcioPartida{
		if(s.length() != 2)
			throw new ExcepcioPartida("Invalid answer length!");
       	value.setL(alphabeth.indexOf(s.charAt(0)));
       	value.setR(alphabeth.indexOf(s.charAt(1)));
        Check();
	}

	/**
	 * Compara el Answer.value a this.value
	 * @param answer intent
	 * @return boolean si es igual o no
	 */
	public boolean equalTo(Answer answer) {
		if(this.value.getL() != answer.value.getL())
			return false;
		if(this.value.getR() != answer.value.getR())
			return false;		
		return true;
	}
	
	/**
	 * Copia answer.value() a this.value()
	 * @param answer answer
	 */
	public void fromAnswer(Answer answer) {
      this.value = new Pair<>(answer.value.getL(), answer.value.getR());  
    }
	
	/**
	 * Copia els valors del array a this.value
	 * @param answer Array amb l'intent
	 * @throws ExcepcioPartida si el tamany del array no es valid
	 */
	public void fromArray(Integer[] answer) throws ExcepcioPartida {
		if(answer.length != 2)
			throw new ExcepcioPartida("Invalid answer length!");
		this.value.setL(answer[0]);
		this.value.setR(answer[1]);
		Check();
	};
	
	/**
	 * Passa el value a Integer[2]
	 * @return Integer[]
	 */
	public Integer[] toArray() {
		Integer[] result = new Integer[2];
		result[0] = this.value.getL();
		result[1] = this.value.getR();
		return result;
	}
	
	/**
	 * Copia el pair a this.value
	 * @param value correccio
	 * @throws ExcepcioPartida si el valor no passa el Check
	 */
	public void fromPair(Pair<Integer, Integer> value) throws ExcepcioPartida {
		this.value = value;
		Check();
	};
	
	/**
	 * Comprova si el valor es el ganador
	 * @return boolean
	 */
	public boolean gameOver() {
	  if(this.value.getL() == settings.combinationLength && this.value.getR() == 0)
	    return true;
	  return false;
	}
};
	

