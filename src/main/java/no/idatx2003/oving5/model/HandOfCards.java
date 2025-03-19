package no.idatx2003.oving5.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a hand of playing cards.
 * This class provides methods to analyze the cards in the hand.
 */
public class HandOfCards {
  private final List<PlayingCard> cards;

  /**
   * Creates a hand with the given cards.
   *
   * @param cards The cards in the hand
   */
  public HandOfCards(List<PlayingCard> cards) {
    this.cards = cards;
  }

  /**
   * Calculates the sum of all card values (Ace = 1).
   * Uses streams to map each card to its face value and sum them.
   *
   * @return The sum of all card values
   */
  public int calculateSumOfCardValues() {
    return cards.stream()
        .mapToInt(PlayingCard::getFace)
        .sum();
  }

  /**
   * Gets all hearts in the hand using stream filtering.
   *
   * @return A string representation of all hearts, or "No Hearts" if none
   */
  public String getAllHearts() {
    String heartsString = cards.stream()
        .filter(card -> card.getSuit() == 'H')
        .map(PlayingCard::getAsString)
        .collect(Collectors.joining(" "));

    return heartsString.isEmpty() ? "No Hearts" : heartsString;
  }

  /**
   * Checks if the hand contains the Queen of Spades using stream matching.
   *
   * @return true if the hand contains the Queen of Spades, false otherwise
   */
  public boolean containsQueenOfSpades() {
    return cards.stream()
        .anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
  }

  /**
   * Checks if the hand contains a flush (5 cards of the same suit).
   * Uses streams to group cards by suit and count them.
   *
   * @return true if the hand contains a flush, false otherwise
   */
  public boolean containsFlush() {
    Map<Character, Long> suitCounts = cards.stream()
        .collect(Collectors.groupingBy(PlayingCard::getSuit, Collectors.counting()));

    return suitCounts.values().stream().anyMatch(count -> count >= 5);
  }

  /**
   * Gets the cards in the hand.
   *
   * @return The cards in the hand
   */
  public List<PlayingCard> getCards() {
    return cards;
  }

  /**
   * Returns a string representation of the hand.
   *
   * @return A string representation of all cards in the hand
   */
  @Override
  public String toString() {
    return cards.stream()
        .map(PlayingCard::getAsString)
        .collect(Collectors.joining(" "));
  }
}