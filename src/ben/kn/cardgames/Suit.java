package ben.kn.cardgames;

import ben.kn.cardgames.to.Card;

/**
 * Represents the suite of a {@link Card}
 * 
 * @author Ben (bknear@gmail.com)
 * @since Feb 7, 2013
 */
public enum Suit {
	// CLUBS("Clubs", '♣'), DIAMONDS("Diamonds", '♦'), HEARTS("Hearts",
	// '♥'), SPADES("Spades", '♠');
	CLUBS("Clubs", "&#9827", Color.BLACK, 'C'), DIAMONDS("Diamonds", "&#9826", Color.RED, 'D'), HEARTS(
			"Hearts", "&#9825", Color.RED, 'H'), SPADES("Spades", "&#9824", Color.BLACK, 'S');

	private String display;
	private String ascii;
	private Color color;
	private char abbreviation;

	Suit(String display, String ascii, Color color, char abbreviation) {
		this.display = display;
		this.ascii = ascii;
		this.color = color;
		this.abbreviation = abbreviation;
	}

	public String getDisplay() {
		return display;
	}

	public String getAscii() {
		return ascii;
	}

	public Color getColor() {
		return color;
	}

	public char getAbbreviation() {
		return abbreviation;
	}
}
