package org.unoEngin.UNOGameEngin.Cards;

public class Card {
    public enum Color {
        RED, BLUE, GREEN, YELLOW, WILD
    }

    public enum Type {
        NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR,
        ACTION_CARD_1, ACTION_CARD_2, ACTION_CARD_3, ACTION_CARD_4

    }

    private final Color color;
    private final Type type;
    private final int number;
    private static int actionNumber=10;

    public Card(Color color, int number) {

        if (color == null) {
            throw new NullPointerException("Color cannot be null.");
        }

        if (number < 0 || number > 9) {
            throw new IllegalArgumentException("Number must be between 0 and 9.");
        }

        this.color = color;
        this.type = Type.NUMBER;
        this.number = number;
    }

    public Card(Color color, Type type) {
        if (color == null) {
            throw new NullPointerException("Color cannot be null.");
        }

        if (type == null) {
            throw new NullPointerException("Type cannot be null.");
        }

        this.color = color;
        this.type = type;
        this.number = actionNumber++;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }


    @Override
    public String toString() {
        return switch (type) {
            case NUMBER -> color + " " + number;
            case SKIP -> color + " Skip";
            case REVERSE -> color + " Reverse";
            case DRAW_TWO -> color + " Draw Two";
            case WILD -> "Wild";
            case WILD_DRAW_FOUR -> "Wild Draw Four";
            default -> "Unknown Card";
        };
    }


}

