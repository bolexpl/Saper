package com.company;

import javax.swing.*;

class Field extends JButton {

    private int value;
    static final int MINA = -1;
    static final int PUSTE = 0;
    static final int NIEOKRESLONE = -2;

    private int state;
    static final int ODKRYTE = 0;
    static final int ZAKRYTE = 1;
    static final int FLAGA = 2;

    Field() {
        super();
        this.state = ZAKRYTE;
        this.value = -2;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    int getState() {
        return state;
    }

    void setState(int state) {
        this.state = state;
    }

    void setIcon(ImageIcon icon) {
        if (icon == null) {
            super.setIcon(null);
        } else {
            super.setIcon(new ImageIcon(
                    (icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH))));
        }
    }
}
