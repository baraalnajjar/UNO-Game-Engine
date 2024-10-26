package org.unoEngin.UNOGameEngin.Cards;

import java.util.*;

public class Deck {
    private final List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        for (Card.Color color : Card.Color.values()) {
            if (color != Card.Color.WILD) {

                cards.add(new Card(color, 0));

                for (int number = 1; number <= 9; number++) {
                    cards.add(new Card(color, number));
                    cards.add(new Card(color, number));
                }

                cards.add(new Card(color, Card.Type.SKIP));
                cards.add(new Card(color, Card.Type.SKIP));
                cards.add(new Card(color, Card.Type.REVERSE));
                cards.add(new Card(color, Card.Type.REVERSE));
                cards.add(new Card(color, Card.Type.DRAW_TWO));
                cards.add(new Card(color, Card.Type.DRAW_TWO));
            }
        }

        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Card.Color.WILD, Card.Type.WILD));
            cards.add(new Card(Card.Color.WILD, Card.Type.WILD_DRAW_FOUR));
        }
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("The card to be added cannot be null.");
        }

        cards.add(new Card(card.getColor(), card.getType()));
    }
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
    public Card draw()
    {
        if (isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public int getSize()
    {
        return cards.size();
    }

    public boolean isEmpty()
    {
        return cards.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck contains ").append(cards.size()).append(" cards:\n");
        for (Card card : cards) {
            sb.append(card.toString()).append("\n");
        }
        return sb.toString();
    }
}

