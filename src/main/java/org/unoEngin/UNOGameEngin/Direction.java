package org.unoEngin.UNOGameEngin;

public class Direction {
    private static String key;
    public Direction(String key) {
        if (!key.equals("normal") && !key.equals("reversed")) {
            throw new IllegalArgumentException("Key must be either 'normal' or 'reversed'.");
        }
        Direction.key = key;
    }
    public String getKey() {
        return key;
    }
    public void getOpposite() {
        if(key.equals("normal"))
        {
            key="reversed";
        }
        else
            key= "normal";
        }

    @Override
    public String toString() {
        return "Current direction: " + key;
    }

    }





