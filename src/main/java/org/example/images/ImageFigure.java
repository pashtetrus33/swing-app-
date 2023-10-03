package org.example.images;

import org.example.common.Interactable;
import org.example.common.MainCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFigure implements Interactable {
    File path = new File("C:/JDK course Interfaces/src/main/java/org/example/images/png");
    float x, y;
    BufferedImage image;

    {
        try {
            image = ImageIO.read(new File(path, "smile.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private float vX, vY;

    public ImageFigure() {
        vX = 100f + (float) (Math.random() * 200f);
        vY = 100f + (float) (Math.random() * 200f);
    }

    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;

        if (x < canvas.getLeft() -50) {
            x = canvas.getLeft() -50;
            vX = -vX;
        }
        if (x > canvas.getRight() - 150) {
            x = canvas.getRight() -150;
            vX = -vX;
        }
        if (y < canvas.getTop()) {
            y = canvas.getTop();
            vY = -vY;
        }
        if (y > canvas.getBottom() - 100) {
            y = canvas.getBottom() - 100;
            vY = -vY;

        }
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}
