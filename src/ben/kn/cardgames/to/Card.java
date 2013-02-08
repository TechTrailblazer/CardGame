package ben.kn.cardgames.to;

import ben.kn.cardgames.Face;
import ben.kn.cardgames.Suit;

public class Card {

	private Suit suit;
	private Face face;

	public Card(Suit suit, Face face) {
		this.suit = suit;
		this.face = face;
	}

	public Suit getSuit() {
		return suit;
	}

	public Face getFace() {
		return face;
	}

	@Override
	public String toString() {
		return suit.getAbbreviation() + face.getDisplay();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((face == null) ? 0 : face.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Card other = (Card) obj;
		if ( face != other.face )
			return false;
		if ( suit != other.suit )
			return false;
		return true;
	}

}
