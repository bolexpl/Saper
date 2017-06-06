package com.company;

import javax.swing.*;

public abstract class AbstractField extends JButton {

    protected int value;
    protected int state;

    public abstract int getValue();

    public abstract void setValue(int value);

    public abstract int getState();

    public abstract void setState(int state);

    public void setIcon(ImageIcon icon){
        super.setIcon(icon);
    }
}
