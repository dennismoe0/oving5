package no.idatx2003.oving5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.idatx2003.oving5.model.DeckOfCards;
import no.idatx2003.oving5.model.HandOfCards;
import no.idatx2003.oving5.model.PlayingCard;

import java.util.List;

/**
 * Simple GUI for the card game with card images.
 */
public class CardGameApp extends Application {

  private HandOfCards currentHand;
  private final DeckOfCards deck = new DeckOfCards();

  // UI components
  private FlowPane cardsPane;
  private Label sumLabel;
  private Label heartsLabel;
  private Label queenLabel;
  private Label flushLabel;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Card Game");

    VBox root = new VBox(10);
    root.setPadding(new Insets(10));

    // Cards display area
    cardsPane = new FlowPane(10, 10);
    cardsPane.setPrefHeight(150);
    cardsPane.setAlignment(Pos.CENTER);

    // Buttons
    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER);

    Button dealButton = new Button("Deal Hand");
    Button checkButton = new Button("Check Hand");

    buttonBox.getChildren().addAll(dealButton, checkButton);

    // Results area
    HBox resultsBox = new HBox(20);
    resultsBox.setAlignment(Pos.CENTER);

    sumLabel = new Label("Sum: ");
    heartsLabel = new Label("Hearts: ");
    queenLabel = new Label("Queen of Spades: No");
    flushLabel = new Label("Flush: No");

    resultsBox.getChildren().addAll(sumLabel, heartsLabel, queenLabel, flushLabel);

    // Add to root
    root.getChildren().addAll(cardsPane, buttonBox, resultsBox);

    // Event handlers
    dealButton.setOnAction(e -> dealHand());
    checkButton.setOnAction(e -> checkHand());

    Scene scene = new Scene(root, 600, 300);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void dealHand() {
    List<PlayingCard> cards = deck.dealHand(5);
    currentHand = new HandOfCards(cards);
    displayCards();
    clearResults();
  }

  private void displayCards() {
    cardsPane.getChildren().clear();

    for (PlayingCard card : currentHand.getCards()) {
      String cardName = card.getAsString();
      String imagePath = "/PlayingCards/" + cardName + ".png";

      try {
        Image cardImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(100);
        cardView.setPreserveRatio(true);
        cardsPane.getChildren().add(cardView);
      } catch (Exception e) {
        // If image not found, use text representation
        Label cardLabel = new Label(cardName);
        cardLabel.setPrefSize(60, 100);
        cardLabel.setStyle("-fx-border-color: black; -fx-alignment: center;");
        cardsPane.getChildren().add(cardLabel);
      }
    }
  }

  private void checkHand() {
    if (currentHand != null) {
      sumLabel.setText("Sum: " + currentHand.calculateSumOfCardValues());
      heartsLabel.setText("Hearts: " + currentHand.getAllHearts());
      queenLabel.setText("Queen of Spades: "
          + (currentHand.containsQueenOfSpades() ? "Yes" : "No"));
      flushLabel.setText("Flush: " + (currentHand.containsFlush() ? "Yes" : "No"));
    }
  }

  private void clearResults() {
    sumLabel.setText("Sum: ");
    heartsLabel.setText("Hearts: ");
    queenLabel.setText("Queen of Spades: No");
    flushLabel.setText("Flush: No");
  }
}