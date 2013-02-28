package ben.kn.cardgames.games;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ben.kn.cardgames.Outcome;
import ben.kn.cardgames.to.Deck;
import ben.kn.cardgames.util.DeckUtil;

public class AcesUpJTest {

	Game game = new AcesUp();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPlayBasicDeck() {
		Outcome result = game.play(DeckUtil.getOrderedDeck());

		switch (result) {
			case WIN:
				System.out.println("\n\nWIN!");
				break;
			case DRAW:
				System.out.println("\n\nDRAW...");
				break;
			case LOSS:
				System.out.println("\n\nLOSS.");
				break;
		}

		assertEquals(Outcome.WIN, result);
	}

	@Test
	public void testShuffledDeck() {
		Deck deck = DeckUtil.getOrderedDeck();
		deck = DeckUtil.shuffleDeck(deck);
		Outcome result = game.play(deck);

		System.out.println(game.getResults());
		switch (result) {
			case WIN:
				System.out.println("\n\nWIN!");
				break;
			case DRAW:
				System.out.println("\n\nDRAW...");
				break;
			case LOSS:
				System.out.println("\n\nLOSS.");
				break;
		}
	}

	@Test
	public void testStatistics() {
		int totalGames = 1000;
		int totalWins = 0;
		int totalLosses = 0;
		int totalDraws = 0;

		Deck deck = DeckUtil.getOrderedDeck();

		for ( int i = 0; i < totalGames; i++ ) {
			deck = DeckUtil.shuffleDeck(DeckUtil.getOrderedDeck());
			Outcome result = game.play(deck);

			switch (result) {
				case WIN:
					totalWins++;
					break;
				case DRAW:
					totalDraws++;
					break;
				case LOSS:
					totalLosses++;
					break;
			}
		}
		System.out.println("Ran " + totalGames + " games:");
		System.out.println("Won:\t" + totalWins + " (" + (double) totalWins / totalGames + ")");
		System.out.println("Losses:\t" + totalLosses + " (" + (double) totalLosses / totalGames
				+ ")");
		System.out.println("Draws:\t" + totalDraws + " (" + (double) totalDraws / totalGames + ")");
	}
}