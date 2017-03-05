package com.company;

import javax.swing.*;

public class Field extends JButton {

    /*
    * -2 nieokreślone
    * -1 mina
    * 0 puste
    * */
    private int value;

    static final int MINA = -1;
    static final int PUSTE = 0;

    /*
    * 0 odkryte
    * 1 zakryte
    * 2 flaga
    * */
    private int state;
    static final int ODKRYTE = 0;
    static final int ZAKRYTE = 1;
    static final int FLAGA = 2;


    Field() {
        super();
        this.state = ZAKRYTE;
        this.value = -2;
    }

    public Field(ImageIcon icon) {
        super(new ImageIcon(
                (icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH))));
        this.state = ZAKRYTE;
        this.value = -2;
    }

    public Field(String s) {
        super(s);
        this.state = ZAKRYTE;
        this.value = -2;
    }

    public Field(Action action) {
        super(action);
        this.state = ZAKRYTE;
        this.value = -2;
    }

    public Field(String s, ImageIcon icon) {
        super(s, new ImageIcon(
                (icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH))));
        this.state = ZAKRYTE;
        this.value = -2;
    }

    void setIcon(ImageIcon icon) {
        if(icon == null){
            super.setIcon(null);
        }else{
            super.setIcon(new ImageIcon(
                    (icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH))));
        }
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
}
