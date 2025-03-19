package no.ntnu.idatx2003.oving5.model;

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
  private final Random random = new Random();

  /**
   * Creates a deck.
   */
  public DeckOfCards() {
    this.cards = new ArrayList<>(52); // Size
    for (char suit : suits) {
      for (int face = 1; face <= 13; face++) {
        cards.add(new PlayingCard(suit, face));
      }
    }
  }
}
