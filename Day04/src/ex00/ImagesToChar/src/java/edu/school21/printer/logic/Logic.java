package edu.school21.printer.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public class Logic {
    public char[][] convert(String imagePath, char whitePixelChar, char blackPixelChar) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        int width = image.getWidth();
        int height = image.getHeight();
        char[][] imageArray = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = image.getRGB(j, i);
                if (pixel == -1) { // white pixel
                    imageArray[i][j] = whitePixelChar;
                } else { // black pixel
                    imageArray[i][j] = blackPixelChar;
                }
            }
        }
        return imageArray;
    }
}