package org.unoEngin.UNOGameVariations.ActionCards.Impl;

import org.unoEngin.UNOGameEngin.Cards.Card;
import org.unoEngin.UNOGameVariations.ActionCards.CardActionStrategyInt;
import org.unoEngin.UNOGameEngin.Game;

public class DrawTwoCardStrategyImpl implements CardActionStrategyInt {
    @Override
    public void playActionCard(Game game, Card card) {
        System.out.println("DRAW_TWO card played: " + card);

        for (int i=0;i<2;i++) {
            game.getNextPlayer().addCardToHand(game.getDeck().draw());
        }

    }
}