package org.unoEngin.UNOGameEngin.Players;

import org.unoEngin.UNOGameEngin.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }
        this.name = name;
        this.hand = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void addCardToHand(Card card) {
        if (card == null) {
            throw new NullPointerException("The card to be added cannot be null.");
        }

        hand.add(card);
    }
    public void removeCardFromHand(Card card) {
        if (card == null) {
            throw new NullPointerException("The card to be removed cannot be null.");
        }

        if (!hand.contains(card)) {
            throw new IllegalArgumentException("The card is not in the player's hand.");
        }

        hand.remove(card);
    }
    public int getHandSize() {
        return hand.size();
    }
    public List<Card> getHand() {
        return hand;
    }
    public void showHand() {
        System.out.println(name + "'s hand:");
        for (Card card : hand) {
            System.out.println("  - " + card.toString());
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("'s hand:\n");
        for (Card card : hand) {
            sb.append("  - ").append(card.toString()).append("\n");
        }
        return sb.toString();
    }
}

