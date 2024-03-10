
package main.subgrup14_1.mastermind.domini.models;

/**
 * 
 * @author aglaya.khalipskaya (aglaya.khalipskaya@estudiantat.upc.edu)
 *
 */

public final class Settings {
  public    int numLetters;
  public 	boolean duplicationsAllowed;
  public 	int combinationLength;
  public 	int maxMoves;
  
  /**
   * Construeix Settings
   */
  public 	Settings(){
		numLetters = 10;
		duplicationsAllowed = true;
		combinationLength = 10;
		maxMoves = 0;
	}

   /**
    * Cambia Settings d'acord amb la dificultat de la partida
    * @param info Informacio de la partida
    */
   public void fromInfo(InfoPartida info) {
	  switch(info.getDificultat()) {
	  case FACIL:
		numLetters = 4;
		combinationLength = 4;
		duplicationsAllowed = false;
		break;
	  case INTERMIG:
			numLetters = 6;
			combinationLength = 4;
			duplicationsAllowed = true;
			break;
	  case DIFICIL:
			numLetters = 8;
			combinationLength = 4;
			duplicationsAllowed = true;
			break;
	}
	maxMoves = info.getTotalTorns();
	  
  };
}
