package ben.kn.cardgames;

import ben.kn.cardgames.to.Card;

/**
 * Represents the face value of a {@link Card}
 * 
 * @author Ben (bknear@gmail.com)
 * @since Feb 7, 2013
 */
public enum Face {
	ACE("a"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE(
			"9"), TEN("10"), JACK("j"), QUEEN("q"), KING("k");
	private String display;

	Face(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}
}
