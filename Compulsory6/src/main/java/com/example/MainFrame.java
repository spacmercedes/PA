package com.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    protected ConfigPanel configPanel;
    protected  ControlPanel controlPanel;
    protected DrawingPanel canvas;


    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new DrawingPanel(this);
 controlPanel =new ControlPanel(this);
 configPanel=new ConfigPanel(this);

  add(controlPanel,BorderLayout.SOUTH);
 add(canvas,BorderLayout.CENTER);
 add(configPanel, BorderLayout.NORTH);


       // pack();
    }
}
