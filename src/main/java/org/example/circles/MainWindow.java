package org.example.circles;

import org.example.common.Background;
import org.example.common.CanvasRepaidListener;
import org.example.common.MainCanvas;
import org.example.common.Interactable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainWindow extends JFrame implements CanvasRepaidListener, Thread.UncaughtExceptionHandler {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static int count;
    private static final int ARRAY_SIZE = 16;
    private final Interactable[] interactables = new Interactable[ARRAY_SIZE];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        for (int i = 0; i < 3; i++) {
            System.out.println("Amount: " + ++count);
            interactables[i] = new Ball();
        }
        interactables[interactables.length - 1] = new Background();
        MainCanvas canvas = new MainCanvas(this);
        Thread.setDefaultUncaughtExceptionHandler(this);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 1) {
                    count++;
                    if (count >= ARRAY_SIZE) {
                        count = ARRAY_SIZE - 1;
                        throw new RuntimeException("Maximum amount of balls: " + (ARRAY_SIZE - 1));
                    }
                    interactables[count -1] = new Ball();
                    System.out.print("Add command. ");
                } else if (e.getButton() == 3) {
                    System.out.print("Delete command. ");
                    if (count > 0) {
                        System.out.print("Inside if count > 0. ");
                        interactables[count -1] = null;
                        count--;
                    }

                }
                System.out.println("Amount: " + count);
            }
        });
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
            if (interactable != null) interactable.update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (Interactable interactable : interactables) {
            if (interactable != null) interactable.render(canvas, g);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Exception!", JOptionPane.ERROR_MESSAGE);
        //System.exit(-1);
    }
}
