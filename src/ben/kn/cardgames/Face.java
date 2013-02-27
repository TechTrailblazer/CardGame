package ben.kn.cardgames;

import ben.kn.cardgames.to.Card;

/**
 * Represents the face value of a {@link Card}
 * 
 * @author Ben (bknear@gmail.com)
 * @since Feb 7, 2013
 */
public enum Face {
	TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT(
			"8", 8), NINE("9", 9), TEN("10", 10), JACK("j", 11), QUEEN("q", 12), KING("k", 13), ACE("a", 14);

	private String display;
	private int value;

	Face(String display, int value) {
		this.display = display;
		this.value = value;
	}

	public String getDisplay() {
		return display;
	}

	public int getValue() {
		return value;
	}

	public boolean lessThan(Face f) {
		return value < f.value;
	}

	public boolean greaterThan(Face f) {
		return value > f.value;
	}
}
