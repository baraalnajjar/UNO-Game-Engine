package org.unoEngin.UNOGameEngin.Cards;

import java.util.Stack;

public class PlayedCards {
    private final Stack<Card> playedCards;
    public PlayedCards() {
        playedCards = new Stack<>();
    }
    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("The card to be added cannot be null.");
        }

        playedCards.push(card);
    }
    public Card peekTopCard() {
        if (playedCards.isEmpty()) {
            throw new IllegalStateException("No cards have been played yet!");
        }
        return playedCards.peek();
    }
    public boolean isEmpty() {
        return playedCards.isEmpty();
    }
    public Card popTopCard() {
        if (playedCards.isEmpty()) {
            throw new IllegalStateException("No cards have been played yet!");
        }
        return playedCards.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Played Cards: \n");
        for (Card card : playedCards) {
            sb.append(card.toString()).append("\n");
        }
        return sb.toString();
    }
}
