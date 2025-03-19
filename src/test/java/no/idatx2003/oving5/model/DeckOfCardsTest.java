package no.idatx2003.oving5.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;

import java.util.List;

class DeckOfCardsTest {

  @Test
  @DisplayName("Test dealing a valid hand")
  void testDealHand() {
    DeckOfCards deck = new DeckOfCards();
    List<PlayingCard> cards = deck.dealHand(5);

    assertEquals(5, cards.size());
  }

  @Test
  @DisplayName("Test each dealt hand contains unique cards")
  void testDealHandUnique() {
    DeckOfCards deck = new DeckOfCards();
    List<PlayingCard> hand1 = deck.dealHand(5);
    List<PlayingCard> hand2 = deck.dealHand(5);

    // Convert hands to strings for easy comparison
    String hand1Str = hand1.toString();
    String hand2Str = hand2.toString();

    // Highly unlikely that two random 5-card hands would be identical
    assertNotEquals(hand1Str, hand2Str, "Hands should be different");
  }

  @Test
  @DisplayName("Test dealing an invalid number of cards throws exception")
  void testDealHandInvalidNumber() {
    DeckOfCards deck = new DeckOfCards();

    IllegalArgumentException exception1 = assertThrows(
        IllegalArgumentException.class, () -> deck.dealHand(0));
    assertEquals("Hand size must be between 1 and 52", exception1.getMessage());
    IllegalArgumentException exception2 = assertThrows(
        IllegalArgumentException.class, () -> deck.dealHand(53));
    assertEquals("Hand size must be between 1 and 52", exception2.getMessage());
  }
}