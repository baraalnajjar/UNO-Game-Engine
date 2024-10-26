package org.unoEngin.UNOGameVariations.ActionCards.Impl;

import org.unoEngin.UNOGameEngin.Cards.Card;
import org.unoEngin.UNOGameVariations.ActionCards.CardActionStrategyInt;
import org.unoEngin.UNOGameEngin.Direction;
import org.unoEngin.UNOGameEngin.Game;

public class ReverseCardStrategyImpl implements CardActionStrategyInt {
    @Override
    public void playActionCard(Game game, Card card) {

        System.out.println("REVERSE card Played: " + card);
        Direction direction = game.getDirection();

        direction.getOpposite();
        System.out.println("Direction is now "+ direction.getKey());
        game.setDirection(direction);

    }
}