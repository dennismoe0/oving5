package no.idatx2003.oving5.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a deck of cards with 52 cards.
 *
 * @author Dennis Moe
 */
public class DeckOfCards {
  private final char[] suits = { 'S', 'H', 'D', 'C' };
  private final List<PlayingCard> cards;
  int deckSize = 52; // Probably stupid but I put it here to be used in methods.
  private final Random random = new Random();

  /**
   * Creates a deck.
   */
  public DeckOfCards() {
    this.cards = new ArrayList<>(deckSize); // Size
    for (char suit : suits) {
      for (int face = 1; face <= 13; face++) {
        cards.add(new PlayingCard(suit, face));
      }
    }
  }

  /**
   * Deals a hand of n cards from the deck.
   *
   * @param n Amount of cards to be in a hand between 1 and deckSize.
   * @return Randomly selected cards.
   * @throws IllegalArgumentException if n is not between 1 and deckSize.
   */
  public List<PlayingCard> dealHand(int n) {
    if (n < 1 || n > deckSize) {
      throw new IllegalArgumentException("Number of cards must be between 1 and " + deckSize);
    }

    List<PlayingCard> playerHand = new ArrayList<>(n);

    List<PlayingCard> temporaryDeck = new ArrayList<>(cards); // Copy of the deck from constructor

    for (int i = 0; i < n; i++) {
      int cardIndex = random.nextInt(temporaryDeck.size()); // Random index based on size of deck
      playerHand.add(temporaryDeck.remove(cardIndex)); // adds to hand and removes from deck
    }
    return playerHand;
  }
}
