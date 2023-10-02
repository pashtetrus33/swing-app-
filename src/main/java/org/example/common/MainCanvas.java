package org.example.common;

import org.example.circles.MainWindow;

import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {
    private final CanvasRepaidListener controller;
    private long lastFrameTime;

    public MainCanvas(CanvasRepaidListener controller) {
        this.controller = controller;
        //setBackground(Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {  // do {
        super.paintComponent(g);                 // doing something useful

        //repaint(); // зацикливание действия (постоянно обновляющаяся канва), но варинат не очень, так как нагрузит одно ядро процессора только отрисовкой окна

        try {
            Thread.sleep(16); //поток спит 16 мс это даст нам FPS близкий к 60  (около 1/60 секунды)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        float deltaTime = (System.nanoTime() - lastFrameTime) * 0.000000001f;
        System.out.println("Delta time: " + deltaTime);
        controller.onDrawFrame(this, g, deltaTime);                // doing something useful
        lastFrameTime = System.nanoTime();
        repaint();             // while(true)
    }

    public int getLeft() {
        return 0;
    }

    public int getRight() {
        return getWidth() - 1;
    }

    public int getTop() {
        return 0;
    }

    public int getBottom() {
        return getHeight() - 1;
    }
}
