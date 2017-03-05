package com.company;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

class Bot {

    private Field[][] button;
    private Robot r;
    private Random rand = new Random();
    private int maxX;
    private int maxY;

    Bot(Field[][] button, int maxX, int maxY) throws AWTException {
        this.button = button;
        this.maxX = maxX;
        this.maxY = maxY;

        r = new Robot();
        begin();
    }

    private void begin() {
//        moveToPoint(button[maxX / 2][maxY / 2]);
//        moveToPoint(button[rand.nextInt(maxX)][rand.nextInt(maxY)]);
//        System.out.println(maxX / 2 + ", " + maxY / 2);

        moveToPoint(button[0][0]);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveToPoint(button[4][4]);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveToPoint(button[1][1]);

//        for (int i = 0; i < maxX; i++) {
//            moveToPoint(button[i][i]);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void moveToPoint(Field f) {
        Point d = f.getLocationOnScreen();
        int n = 40;

        int x1 = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int y1 = (int) MouseInfo.getPointerInfo().getLocation().getY();

        int x2 = (int) d.getX() + f.getWidth() / 2;
        int y2 = (int) d.getY() + f.getHeight() / 2;

        int dx = (x2 - x1) / n;
        int dy = (y2 - y1) / n;

        for (int i = 1; i <= n; i++) {
            r.mouseMove(x1 + dx * i, y1 + dy * i);
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        r.mouseMove(x2, y2);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
