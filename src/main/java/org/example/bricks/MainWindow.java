package org.example.bricks;


import org.example.common.Background;
import org.example.common.CanvasRepaidListener;
import org.example.common.Interactable;
import org.example.common.MainCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame implements CanvasRepaidListener, MouseListener {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private final Interactable[] interactables = new Interactable[11];

    public static void main(String[] args) {
        new MainWindow();
        System.out.println("Main method is finished.");
    }

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        for (int i = 0; i < interactables.length; i++) {
            interactables[i] = new Brick();
        }
        interactables[10] = new Background();
        MainCanvas canvas = new MainCanvas(this);
        canvas.addMouseListener(this);
        add(canvas);
        setVisible(true);
    }

    @Override
    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (Interactable interactable : interactables) {
            interactable.update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (Interactable interactable : interactables) {
            interactable.render(canvas, g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
