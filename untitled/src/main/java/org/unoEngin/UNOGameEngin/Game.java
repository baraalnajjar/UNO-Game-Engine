package org.unoEngin.UNOGameEngin;

import org.unoEngin.UNOGameEngin.Cards.Card;
import org.unoEngin.UNOGameEngin.Cards.Deck;
import org.unoEngin.UNOGameEngin.Cards.PlayedCards;
import org.unoEngin.UNOGameEngin.Players.Player;

import java.util.*;

public abstract class Game {

    protected List<Player> players;
    protected Deck deck;
    protected int playerIndex;
    protected PlayedCards playedCards;
    protected Direction direction;

    //abstract methods
    abstract protected List<Player> initializePlayers() ;
    abstract protected void pileUsage();
    abstract protected void initializeActionCard();
    abstract protected void dealCards();
    abstract protected void handlePlayerTurn(Player currentPlayer) ;
    abstract protected boolean chooseCardToPlay(Card card, Card topCard, boolean canPlayAnyCard);
    abstract protected void applyCardEffect(Card card);
    abstract public Player getNextPlayer();


    //other methods
    public void play() {
        players = initializePlayers();
        deck = new Deck();
        playedCards = new PlayedCards();
        direction = new Direction("normal");
        playerIndex = 0;
        initializeActionCard();
        dealCards();

        Card startingCard;
        do {
            startingCard = deck.draw();
        } while (startingCard.getType() != Card.Type.NUMBER);
        playedCards.addCard(startingCard);
        System.out.println();
        System.out.println("Starting card: " + startingCard);
        System.out.println();


        Player currentPlayer;
        boolean gameEnded = false;
        while (!gameEnded) {
            currentPlayer = players.get(playerIndex);
            System.out.print(playerIndex+"  ");
            System.out.println(currentPlayer.getName() + "'s turn:");
            System.out.println();

            currentPlayer.showHand();

            handlePlayerTurn(currentPlayer);

            if (currentPlayer.getHandSize()==0) {
                System.out.println(currentPlayer.getName() + " wins the game!");
                System.out.println();

                gameEnded = true;
            } else {
                nextTurn();
            }
        }

        System.out.println("Game over!");
    }
    public Card playCard(Player player, Card topCard) {
        List<Card> playableCards = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean canPlayAnyCard = (topCard.getType().equals(Card.Type.WILD) || topCard.getType().equals(Card.Type.WILD_DRAW_FOUR));

        for (Card card : player.getHand()) {
            if (chooseCardToPlay(card, topCard, canPlayAnyCard)) {
                playableCards.add(card);
            }
        }

        if (playableCards.isEmpty()) {
            System.out.println("No playable cards.");
            return null;
        }

        System.out.println("Top card on the pile: " + topCard);
        System.out.println("Your playable cards:");
        for (int i = 0; i < playableCards.size(); i++) {
            System.out.println((i + 1) + ": " + playableCards.get(i));
        }

        System.out.print("Choose a card to play by entering the corresponding number: ");
        int choice = scanner.nextInt();

        while (choice < 1 || choice > playableCards.size()) {
            System.out.print("Invalid choice. Choose a number between 1 and " + playableCards.size() + ": ");
            choice = scanner.nextInt();
        }

        return playableCards.get(choice - 1);
    }
    public Deck getDeck() {
        return deck;
    }
    protected void nextTurn() {
        playerIndex = players.indexOf(getNextPlayer());
        Player currentPlayer = players.get(playerIndex);
        System.out.println("It's " + currentPlayer.getName() + "'s turn.");
        System.out.println();

    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public int getCurrentPlayerIndex() {
        return playerIndex;
    }
    public void setCurrentPlayerIndex(int index) {
        this.playerIndex = index;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game State:\n");
        sb.append("Direction: ").append(direction.getKey()).append("\n");
        sb.append("Current Player: ").append(players.get(playerIndex).getName()).append("\n");
        sb.append("Deck size: ").append(deck.getSize()).append(" cards\n");
        sb.append("Top card on the pile: ").append(playedCards.peekTopCard()).append("\n");
        sb.append("Players:\n");

        for (Player player : players) {
            sb.append("  - ").append(player.getName()).append(" (").append(player.getHandSize()).append(" cards)\n");
        }

        return sb.toString();
    }


}