package com.company;

import java.awt.*;
import java.awt.event.InputEvent;

class Bot {

    private Field[][] bt;
    private Robot r;
    private int maxX;
    private int maxY;

    Bot(Field[][] button, int maxX, int maxY) throws AWTException {
        this.bt = button;
        this.maxX=maxX;
        this.maxY=maxY;

        r = new Robot();
        moveToPoint(button[4][4]);
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
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.mouseMove(x1 + dx * i, y1 + dy * i);
        }

        r.mouseMove(x2, y2);
//        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
