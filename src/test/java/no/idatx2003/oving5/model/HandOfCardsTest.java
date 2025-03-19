package no.idatx2003.oving5.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

class HandOfCardsTest {

  @Test
  @DisplayName("Test calculating sum of card values")
  void testCalculateSumOfCardValues() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('H', 5));
    cards.add(new PlayingCard('S', 10));
    cards.add(new PlayingCard('D', 1)); // Ace

    HandOfCards hand = new HandOfCards(cards);
    assertEquals(16, hand.calculateSumOfCardValues());
  }

  @Test
  @DisplayName("Test getting all hearts")
  void testGetAllHearts() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('H', 5));
    cards.add(new PlayingCard('S', 10));
    cards.add(new PlayingCard('H', 1));

    HandOfCards hand = new HandOfCards(cards);
    assertEquals("H5 H1", hand.getAllHearts());
  }

  @Test
  @DisplayName("Test no hearts in hand")
  void testNoHearts() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('S', 5));
    cards.add(new PlayingCard('D', 10));

    HandOfCards hand = new HandOfCards(cards);
    assertEquals("No Hearts", hand.getAllHearts());
  }

  @Test
  @DisplayName("Test contains Queen of Spades")
  void testContainsQueenOfSpades() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('S', 12)); // Queen of Spades
    cards.add(new PlayingCard('H', 10));

    HandOfCards hand = new HandOfCards(cards);
    assertTrue(hand.containsQueenOfSpades());
  }

  @Test
  @DisplayName("Test does not contain Queen of Spades")
  void testDoesNotContainQueenOfSpades() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('S', 11)); // Jack of Spades
    cards.add(new PlayingCard('H', 12)); // Queen of Hearts

    HandOfCards hand = new HandOfCards(cards);
    assertFalse(hand.containsQueenOfSpades());
  }

  @Test
  @DisplayName("Test contains flush")
  void testContainsFlush() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('H', 1));
    cards.add(new PlayingCard('H', 4));
    cards.add(new PlayingCard('H', 7));
    cards.add(new PlayingCard('H', 10));
    cards.add(new PlayingCard('H', 13));

    HandOfCards hand = new HandOfCards(cards);
    assertTrue(hand.containsFlush());
  }

  @Test
  @DisplayName("Test does not contain flush")
  void testDoesNotContainFlush() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('H', 1));
    cards.add(new PlayingCard('H', 4));
    cards.add(new PlayingCard('H', 7));
    cards.add(new PlayingCard('H', 10));
    cards.add(new PlayingCard('S', 13));

    HandOfCards hand = new HandOfCards(cards);
    assertFalse(hand.containsFlush());
  }

  @Test
  @DisplayName("Test getCards returns the correct cards")
  void testGetCards() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('H', 5));
    cards.add(new PlayingCard('S', 10));

    HandOfCards hand = new HandOfCards(cards);
    List<PlayingCard> returnedCards = hand.getCards();

    assertEquals(2, returnedCards.size());
    assertEquals('H', returnedCards.get(0).getSuit());
    assertEquals(5, returnedCards.get(0).getFace());
    assertEquals('S', returnedCards.get(1).getSuit());
    assertEquals(10, returnedCards.get(1).getFace());
  }

  @Test
  @DisplayName("Test toString returns correct string representation")
  void testToString() {
    List<PlayingCard> cards = new ArrayList<>();
    cards.add(new PlayingCard('H', 5));
    cards.add(new PlayingCard('S', 10));

    HandOfCards hand = new HandOfCards(cards);
    assertEquals("H5 S10", hand.toString());
  }
}