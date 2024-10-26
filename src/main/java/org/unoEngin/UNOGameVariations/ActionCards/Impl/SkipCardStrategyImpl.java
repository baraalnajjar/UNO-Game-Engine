package org.unoEngin.UNOGameVariations.ActionCards.Impl;

import org.unoEngin.UNOGameEngin.Cards.Card;
import org.unoEngin.UNOGameVariations.ActionCards.CardActionStrategyInt;
import org.unoEngin.UNOGameEngin.Direction;
import org.unoEngin.UNOGameEngin.Game;

public class SkipCardStrategyImpl implements CardActionStrategyInt {
    @Override
    public void playActionCard(Game game, Card card) {
        System.out.println("SKIP card Played: " + card);
        Direction direction=game.getDirection();
        if (direction.getKey().equals("normal")) {
            game.setCurrentPlayerIndex(
                    game.getCurrentPlayerIndex() + 1);
        }
        else  {
            game.setCurrentPlayerIndex(
                    game.getCurrentPlayerIndex() + -1);
        }
    }

}