package org.unoEngin.UNOGameVariations.ActionCards.Impl;

import org.unoEngin.UNOGameEngin.Cards.Card;
import org.unoEngin.UNOGameVariations.ActionCards.CardActionStrategyInt;
import org.unoEngin.UNOGameEngin.Game;

public class WildDrawFourCardStrategyImpl implements CardActionStrategyInt {
    @Override
    public void playActionCard(Game game, Card card) {
        System.out.println("WILD DRAW FOUR card Played");

        for (int i=0;i<4;i++) {
            game.getNextPlayer().addCardToHand(game.getDeck().draw());
        }

    }
}
