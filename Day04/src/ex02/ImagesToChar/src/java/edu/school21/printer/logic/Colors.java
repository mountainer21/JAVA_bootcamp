package edu.school21.printer.logic;

import java.awt.*;

public class Colors {
    public static Color getColor(String color) {
        if (color.equals("WHITE") || color.equals("white"))
            return Color.WHITE;
        else if (color.equals("BLACK") || color.equals("black"))
            return Color.BLACK;
        else if (color.equals("RED") || color.equals("red"))
            return Color.RED;
        else if (color.equals("GREEN") || color.equals("green"))
            return Color.GREEN;
        else if (color.equals("BLUE") || color.equals("blue"))
            return Color.BLUE;
        else if (color.equals("YELLOW") || color.equals("yellow"))
            return Color.YELLOW;
        return null;
    }
}
