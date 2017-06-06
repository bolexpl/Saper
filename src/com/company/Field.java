package com.company;

import javax.swing.*;

public class Field extends AbstractField {

    public static final int MINA = -1;
    public static final int PUSTE = 0;
    public static final int NIEOKRESLONE = -2;

    public static final int ODKRYTE = 0;
    public static final int ZAKRYTE = 1;
    public static final int FLAGA = 2;

    public Field() {
        super();
        this.state = ZAKRYTE;
        this.value = -2;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void setIcon(ImageIcon icon) {
        if (icon == null) {
            super.setIcon(null);
        } else {
            super.setIcon(new ImageIcon(
                    (icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH))));
        }
    }
}
