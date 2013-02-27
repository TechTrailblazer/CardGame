package ben.kn.cardgames.games;

import ben.kn.cardgames.Outcome;
import ben.kn.cardgames.to.Card;
import ben.kn.cardgames.to.Deck;

/**
 * This interface represents a game that can be played with a {@link Deck} of
 * {@link Card}s. To implement it, you must implement the play function, which
 * receives an indeterminate number of Decks. It then returns an {@link Outcome}
 * after completing the game.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Feb 13, 2013
 */
public interface Game {

	/**
	 * Run the simulation for this game implementation.
	 * 
	 * @param deck Deck or Deck[]
	 * @return Outcome
	 */
	public Outcome play(Deck... deck);

	public String getResults();
}
