package com.company;

import javax.swing.*;

public class Field extends JButton {
    private short value;
    private short state;

    public Field() {
        super();
        this.state = 0;
    }

    public Field(ImageIcon icon) {
        super(new ImageIcon(
                (icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH))));
        this.state = 0;
    }

    public void setIcon(ImageIcon icon) {
        if(icon == null){
            super.setIcon(null);
        }else{
            super.setIcon(new ImageIcon(
                    (icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH))));
        }
    }

    public Field(String s) {
        super(s);
        this.state = 0;
    }

    public Field(Action action) {
        super(action);
        this.state = 0;
    }

    public Field(String s, ImageIcon icon) {
        super(s, new ImageIcon(
                (icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH))));
        this.state = 0;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }
}
