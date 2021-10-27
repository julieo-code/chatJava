package com.mycompany.chat;

import javax.swing.JFrame;

public class Model extends JFrame{
    public Model(int x, int y) {
        setupFrame(x, y);
        setupComponents();
        setupVisibility(); 
    }
    public Model() {
        this(-1, -1);
    }
    private void setupFrame(int x, int y) {
        setTitle("Titulo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        if(x == -1 && y == -1) {
            setLocationByPlatform(true);
        }else{
            setLocation(x, y);
        }
    }
    private void setupComponents() {
    
    }
    private void setupVisibility() {
        setVisible(true);
    }
}
