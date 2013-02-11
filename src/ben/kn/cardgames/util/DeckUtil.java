package ben.kn.cardgames.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ben.kn.cardgames.Face;
import ben.kn.cardgames.Suit;
import ben.kn.cardgames.to.Card;

public class DeckUtil {
	private static RandomNumberUtil randomUtil = new RandomNumberUtil();

	public static List<Card> getOrderedDeck() {
		List<Card> cards = new ArrayList<Card>();

		for ( Suit s : Suit.values() ) {
			for ( Face f : Face.values() ) {
				cards.add(new Card(s, f));
			}
		}

		return cards;
	}

	public static List<Card> shuffleDeck(List<Card> cards) {
		LinkedList<Card> linkedCards = new LinkedList<Card>(cards);
		List<Card> shuffledCards = new ArrayList<Card>();

		while ( linkedCards.size() > 0 ) {
			// get index to remove
			int index = randomUtil.generateRandomBetween(0, linkedCards.size() - 1);
			shuffledCards.add(linkedCards.get(index));
			linkedCards.remove(index);
		}

		return shuffledCards;
	}

	public static void printDeck(List<Card> cards) {
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

	public static void main(String[] args) {
		List<Card> cards = getOrderedDeck();
		printDeck(cards);
		System.out.println("\nShuffling...");
		cards = shuffleDeck(cards);
		System.out.println("Shuffled:");
		printDeck(cards);
		System.out.println("\nShuffling...");
		cards = shuffleDeck(cards);
		System.out.println("Shuffled:");
		printDeck(cards);
		System.out.println("\nShuffling...");
		cards = shuffleDeck(cards);
		System.out.println("Shuffled:");
		printDeck(cards);
		System.out.println("\nShuffling...");
		cards = shuffleDeck(cards);
		System.out.println("Shuffled:");
		printDeck(cards);
	}
}
