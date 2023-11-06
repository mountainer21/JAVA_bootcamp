package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                    "Usage: java -cp target edu.school21.printer.app.Program <white-pixel-char> <black-pixel-char>");
            System.exit(1);
        }

        char whitePixelChar = args[0].charAt(0);
        char blackPixelChar = args[1].charAt(0);

        try {
            Logic converter = new Logic();
            char[][] imageArray = converter.convert(whitePixelChar, blackPixelChar);
            for (int i = 0; i < imageArray.length; i++) {
                for (int j = 0; j < imageArray[0].length; j++) {
                    System.out.print(imageArray[i][j]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error reading image file: " + e.getMessage());
            System.exit(1);
        }
    }
}