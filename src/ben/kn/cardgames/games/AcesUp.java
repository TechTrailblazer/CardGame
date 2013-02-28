package ben.kn.cardgames.games;

import java.util.Stack;

import org.apache.log4j.Logger;

import ben.kn.cardgames.Face;
import ben.kn.cardgames.Outcome;
import ben.kn.cardgames.to.Card;
import ben.kn.cardgames.to.Deck;

public class AcesUp implements Game {
	protected final Logger log = Logger.getLogger(getClass());

	private Stack<Card>[] board;

	private StringBuilder results;

	@SuppressWarnings("unchecked")
	@Override
	public Outcome play(Deck... deck) {
		// parameter check
		if ( deck == null || deck.length == 0 ) {
			throw new RuntimeException("No deck given to play the game!");
		}

		// Reset the results output
		results = new StringBuilder();

		// get just the first deck given
		Deck gameDeck = deck[0];

		// check that cards are available
		if ( !gameDeck.hasCards() ) {
			throw new RuntimeException("Deck didn't have any cards to play this game.");
		}

		// Initialize the board of cards
		board = new Stack[4];
		board[0] = new Stack<Card>();
		board[1] = new Stack<Card>();
		board[2] = new Stack<Card>();
		board[3] = new Stack<Card>();

		// index of a blank spot on the board
		int blankIndex = -1;

		// go through the deck, playing the game

		results.append("<table border=1>");

		while ( gameDeck.hasCards() ) {
			Card[] draw = gameDeck.drawCardsToArray(4);

			if ( log.isDebugEnabled() ) {
				log.debug("Draw: " + draw[0].toString() + " " + draw[1].toString() + " "
						+ draw[2].toString() + " " + draw[3].toString());
			}

			// deploy draw
			for ( int i = 0; i < 4; i++ ) {
				board[i].add(draw[i]);
			}

			// check displayed cards to remove overcome suits
			blankSpotMovement: do {
				// Remove all low cards until no more can be removed
				do {
					printTopCards();
				} while ( removeLoweredSuit(board, true) );

				// now, see if there are any blank spots for moving cards
				blankIndex = boardHasEmptySpace();
				if ( blankIndex >= 0 ) {
					log.debug("There's a blank spot...");

					// returns true if a card was moved, false if not
					if ( !moveCardToBlankSpot(blankIndex) ) {
						// if a card was not moved, there may be no cards to
						// move. When that is the case, then this will result in
						// an infinite loop unless we break free.
						break blankSpotMovement;
					}
					// else, the cards were moved, so check on what can be
					// removed
				}
				// no more empty spaces, so attempt to remove low cards again

			} while ( blankIndex >= 0 );
		}

		results.append("</table>");

		// check if the game is a winner
		if ( isBottomAces() ) {
			for ( Stack<Card> stack : board ) {
				if ( stack.size() > 1 ) {
					return Outcome.DRAW;
				}
			}

			return Outcome.WIN;
		} else {
			return Outcome.LOSS;
		}
	}

	private int boardHasEmptySpace() {
		for ( int i = 0; i < board.length; i++ ) {
			if ( board[i].isEmpty() ) {
				return i;
			}
		}
		return -1;
	}

	private boolean moveCardToBlankSpot(int blankIndex) {
		// check each stack to see which would be best to move to the blank spot
		boolean[] stackHasMoreThanOneCard = new boolean[4];

		/*
		 * first, see if only one stack has more than one card, making it the
		 * only viable option for moving cards
		 */
		int totalStacksWithMultipleCards = 0;
		int indexWithMoreCards = -1;
		for ( int i = 0; i < board.length; i++ ) {
			Stack<Card> stack = board[i];
			if ( stack.size() > 1 && i != blankIndex ) {
				totalStacksWithMultipleCards++;
				indexWithMoreCards = i;
				stackHasMoreThanOneCard[i] = true;
			} else {
				stackHasMoreThanOneCard[i] = false;
			}
		}

		if ( totalStacksWithMultipleCards == 1 ) {
			// great, do this then
			log.debug("Moving card from " + indexWithMoreCards + " to " + blankIndex);
			Card c = board[indexWithMoreCards].pop();
			board[blankIndex].add(c);
			return true;
		}

		/*
		 * Now we know more than one stack has more than one card. So see if
		 * either of the stacks can a lower card on top or on bottom
		 */
		if ( totalStacksWithMultipleCards > 0 ) {
			// first, see if the top card can be removed by the one below it
			for ( int i = 0; i < board.length; i++ ) {
				if ( stackHasMoreThanOneCard[i] ) {

					Card top = board[i].pop();
					Card bottom = board[i].peek();

					// if top is less than bottom
					if ( top.getSuit() == bottom.getSuit()
							&& top.getFace().lessThan(bottom.getFace()) ) {
						// leave it popped
						return true;
					} else {
						board[i].add(top);
					}
				}
			}

			// if still here, then none of the top cards could be removed by its
			// bottom card. So, see if they can affect each other.
			for ( int i = 0; i < board.length; i++ ) {
				if ( stackHasMoreThanOneCard[i] ) {

					board[blankIndex].add(board[i].pop());
					if ( removeLoweredSuit(board, false) ) {
						return true;
					} else {
						// return things as they were
						board[i].add(board[blankIndex].pop());
					}
				}
			}

			// if we reach here, then none of the multi-card stacks have
			// changed things. Another condition is that aces could be stacked
			// on top of each other.
			for ( int i = 0; i < board.length; i++ ) {
				if ( stackHasMoreThanOneCard[i] ) {
					Stack<Card> stack = board[i];
					int aces = 0;
					for ( Card c : stack ) {
						if ( c.getFace() == Face.ACE ) {
							aces++;
						}
					}
					if ( aces > 1 ) {
						log.debug("Found multiple aces in this stack: "
								+ board[i].peek().toString() + " " + getRestOfStack(board[i]));
						// move the top card from this stack to the blank spot
						board[blankIndex].add(board[i].pop());
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean isBottomAces() {
		for ( Stack<Card> stack : board ) {
			if ( stack.isEmpty() )
				return false;
			if ( stack.get(0).getFace() != Face.ACE ) {
				return false;
			}
		}
		return true;
	}

	private void printTopCards() {
		StringBuilder sb = new StringBuilder();
		sb.append("Board: ");

		if ( log.isDebugEnabled() ) {
			for ( int i = 0; i < board.length; i++ ) {
				Stack<Card> stack = board[i];
				if ( !stack.isEmpty() && stack.peek() != null ) {
					sb.append(stack.peek().toString()).append("(").append(getRestOfStack(stack))
							.append(") ");
				}
			}
		}

		results.append("<tr>");
		for ( int i = 0; i < board.length; i++ ) {
			Stack<Card> stack = board[i];
			if ( !stack.isEmpty() && stack.peek() != null ) {
				results.append("<td>").append(stack.peek().getHtml()).append("(")
						.append(getRestOfStack(stack)).append(") </td>");
			} else {
				results.append("<td></td>");
			}
		}
		results.append("</tr>\n");

		if ( log.isDebugEnabled() ) {
			log.debug(sb.toString());
		}
	}

	private String getRestOfStack(Stack<Card> stack) {
		String s = "";
		for ( int i = stack.size() - 2; i > -1; i-- ) {
			if ( log.isDebugEnabled() )
				s = s + stack.get(i).toString() + " ";
			else
				s = s + stack.get(i).getHtml() + " ";
		}
		return s;
	}

	private boolean removeLoweredSuit(Stack<Card>[] board, boolean pop) {
		// check first card against the rest
		for ( int i = 0; i < 4; i++ ) {
			if ( board[i].isEmpty() ) {
				continue;
			}
			Card cur = board[i].peek();

			for ( int j = i + 1; j < 4; j++ ) {
				if ( !board[j].isEmpty() && cur.getSuit() == board[j].peek().getSuit() ) {
					if ( pop ) {
						Card c;
						// if i is less than j, pop i off
						if ( cur.getFace().lessThan(board[j].peek().getFace()) ) {
							c = board[i].pop();
						} else {
							c = board[j].pop();
						}
						log.debug("----------- Popping " + c.toString());
					}
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public String getResults() {
		return results != null ? results.toString() : null;
	}
}