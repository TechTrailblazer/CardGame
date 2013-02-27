package ben.kn.cardgames.util;

import java.util.LinkedList;

import ben.kn.cardgames.Face;
import ben.kn.cardgames.Suit;
import ben.kn.cardgames.to.Card;
import ben.kn.cardgames.to.Deck;

/**
 * Utility class for creating decks ({@link Deck}s of {@link Card}s) and
 * manipulating those decks.
 * 
 * @author Ben Knear (bknear@gmail.com)
 */
public class DeckUtil {
	private static RandomNumberUtil randomUtil = new RandomNumberUtil();

	public static Deck getOrderedDeck() {
		Deck cards = new Deck();

		for ( Suit s : Suit.values() ) {
			for ( Face f : Face.values() ) {
				cards.add(new Card(s, f));
			}
		}

		return cards;
	}

	public static Deck shuffleDeck(Deck cards) {
		LinkedList<Card> linkedCards = new LinkedList<Card>(cards);
		Deck shuffledCards = new Deck();

		while ( linkedCards.size() > 0 ) {
			// get index to remove
			int index = randomUtil.generateRandomBetween(0, linkedCards.size() - 1);
			shuffledCards.add(linkedCards.get(index));
			linkedCards.remove(index);
		}

		return shuffledCards;
	}

	public static void printDeck(Deck cards) {
		int newLineCounter = 0;
		for ( Card c : cards ) {
			if ( newLineCounter == 13 ) {
				System.out.println();
				newLineCounter = 0;
			}
			System.out.print(c.toString() + " ");
			newLineCounter++;
		}
	}

	public static void printHTMLDeck(Deck cards) {
		int newLineCounter = 0;
		for ( Card c : cards ) {
			if ( newLineCounter == 13 ) {
				System.out.println();
				newLineCounter = 0;
			}
			System.out.print(c.getHtml() + " ");
			newLineCounter++;
		}
	}

	public static void main(String[] args) {
		Deck cards = getOrderedDeck();
		printDeck(cards);
		System.out.println("\nShuffling...");
		cards = shuffleDeck(cards);
		System.out.println("Shuffled:");
		printHTMLDeck(cards);
		System.out.println("\nShuffling...");
		cards = shuffleDeck(cards);
		System.out.println("Shuffled:");
		printHTMLDeck(cards);
		System.out.println("\nShuffling...");
		cards = shuffleDeck(cards);
		System.out.println("Shuffled:");
		printHTMLDeck(cards);
		System.out.println("\nShuffling...");
		cards = shuffleDeck(cards);
		System.out.println("Shuffled:");
		printHTMLDeck(cards);
	}
}
