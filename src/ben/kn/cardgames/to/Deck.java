package ben.kn.cardgames.to;

import java.util.ArrayList;
import java.util.List;

/**
 * A Deck represents an ordered collection of {@link Card}s.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Feb 12, 2013
 */
public class Deck extends ArrayList<Card> {

	private static final long serialVersionUID = -8463862577935886366L;

	/**
	 * Return true if this Deck has {@link Card}s.
	 * 
	 * @return boolean
	 */
	public boolean hasCards() {
		return size() > 0;
	}

	/**
	 * Draw the given total of {@link Card}s from the top of this Deck.
	 * 
	 * @param total int
	 * @return Card array
	 */
	public Card[] drawCardsToArray(int total) {
		Card[] cards = new Card[total];
		for ( int i = 0; i < total; i++ ) {
			cards[i] = remove(0);
		}
		return cards;
	}

	/**
	 * Draw the given total of {@link Card}s from the top of this Deck.
	 * 
	 * @param total int
	 * @return {@link List} of Cards
	 */
	public List<Card> drawCardsToList(int total) {
		List<Card> cards = new ArrayList<Card>();

		for ( int i = 0; i < total; i++ ) {
			cards.add(remove(0));
		}

		return cards;
	}
}