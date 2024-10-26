package org.unoEngin.UNOGameVariations.ActionCards.Impl;

import org.unoEngin.UNOGameEngin.Cards.Card;
import org.unoEngin.UNOGameVariations.ActionCards.CardActionStrategyInt;
import org.unoEngin.UNOGameEngin.Game;

public class WildCardStrategyImpl implements CardActionStrategyInt {
    @Override
    public void playActionCard(Game game, Card card) {
        System.out.println("WILD card Played, you can play any colour!");

    }
}