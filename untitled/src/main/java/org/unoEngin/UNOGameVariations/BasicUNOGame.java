package org.unoEngin.UNOGameVariations;

import org.unoEngin.UNOGameEngin.Cards.Card;
import org.unoEngin.UNOGameEngin.Game;
import org.unoEngin.UNOGameEngin.Players.Player;
import org.unoEngin.UNOGameVariations.ActionCards.CardActionStrategyInt;
import org.unoEngin.UNOGameVariations.ActionCards.Impl.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicUNOGame extends Game {
    protected Map<Card.Type, CardActionStrategyInt> strategyMap;
    protected List<Player> initializePlayers() {

        players = new ArrayList<>();
        players.add(new Player("Alice"));
        players.add(new Player("Bob"));
        players.add(new Player("Charlie"));

        return players;
    }
    protected void pileUsage() {
        ArrayList<Card> pileCards = new ArrayList<>();

        Card topCard=playedCards.popTopCard();
        playedCards.addCard(topCard);
        while (!playedCards.isEmpty()) {
            pileCards.add(playedCards.popTopCard());
        }

        for (Card card : pileCards) {
            deck.addCard(card);
        }
    }
    protected void initializeActionCard() {

        strategyMap = new HashMap<>();
        strategyMap.put(Card.Type.NUMBER, new NumberCardStrategyImpl());
        strategyMap.put(Card.Type.SKIP, new SkipCardStrategyImpl());
        strategyMap.put(Card.Type.REVERSE, new ReverseCardStrategyImpl());
        strategyMap.put(Card.Type.DRAW_TWO, new DrawTwoCardStrategyImpl());
        strategyMap.put(Card.Type.WILD, new WildCardStrategyImpl());
        strategyMap.put(Card.Type.WILD_DRAW_FOUR, new WildDrawFourCardStrategyImpl());
    }
    protected void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 14; i++) {
                if (deck.getSize() > 0) {
                    Card card = deck.draw();
                    player.addCardToHand(card);
                } else {
                    System.out.println("Deck is empty.");
                    System.out.println();

                    return;
                }
            }
        }
    }
    protected void handlePlayerTurn(Player currentPlayer) {

        Card topCard = playedCards.peekTopCard();
        Card cardToPlay = playCard(currentPlayer,topCard);

        while (cardToPlay == null) {
            System.out.println("No playable cards. Drawing a card...");
            System.out.println();

            Card drawnCard = deck.draw();

            if (drawnCard == null) {
                System.out.println("Deck is empty, flipping pile...");
                System.out.println();
                pileUsage();
                drawnCard = deck.draw();

                if (drawnCard == null) {
                    System.out.println("No cards left to draw. Skipping turn.");
                    System.out.println();
                    return;
                }
            }

            System.out.println("You drew: " + drawnCard);
            currentPlayer.addCardToHand(drawnCard);
            System.out.println();

            cardToPlay =playCard(currentPlayer,topCard);
        }

        playedCards.addCard(cardToPlay);
        applyCardEffect(cardToPlay);
        currentPlayer.removeCardFromHand(cardToPlay);
    }
    protected boolean chooseCardToPlay(Card card, Card topCard, boolean canPlayAnyCard) {
        if (canPlayAnyCard) {
            return true;
        }
        return card.getColor().equals(topCard.getColor()) ||
                card.getNumber() == topCard.getNumber() ||
                card.getType().equals(Card.Type.WILD) ||
                ( card.getType().equals(topCard.getType()) && card.getType()!=Card.Type.NUMBER )||
                card.getType().equals(Card.Type.WILD_DRAW_FOUR);
    }
    public Player getNextPlayer() {
        if (direction.getKey().equals("normal")) {
            return players.get((playerIndex + 1) % players.size());
        } else {
            return players.get((playerIndex - 1 + players.size()) % players.size());
        }
    }
    protected void applyCardEffect(Card card) {
        CardActionStrategyInt strategy = strategyMap.get(card.getType());
        if (strategy != null) {
            strategy.playActionCard(this,card);
        } else {
            System.out.println("No special action for this card.");
            System.out.println();

        }
    }

}
