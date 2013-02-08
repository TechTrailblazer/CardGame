package ben.kn.cardgames;

import ben.kn.cardgames.to.Card;

/**
 * Represents the suite of a {@link Card}
 * 
 * @author Ben (bknear@gmail.com)
 * @since Feb 7, 2013
 */
public enum Suit {
//	CLUBS("Clubs", '♣'), DIAMONDS("Diamonds", '♦'), HEARTS("Hearts", '♥'), SPADES("Spades", '♠');
	CLUBS("Clubs", 'C'), DIAMONDS("Diamonds", 'D'), HEARTS("Hearts", 'H'), SPADES("Spades", 'S');

	private String display;
	private char abbreviation;

	Suit(String display, char abbreviation) {
		this.display = display;
		this.abbreviation = abbreviation;
	}

	public String getDisplay() {
		return display;
	}

	public char getAbbreviation() {
		return abbreviation;
	}
}
