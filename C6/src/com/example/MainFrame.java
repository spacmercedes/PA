package com.example;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    protected ConfigPanel configPanel;
    protected ControlPanel controlPanel;
    protected DrawingPanel drawingPanel;

    public MainFrame()
    {
        super("My Game");
        init();
    }

    private void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initPanels();
        addPanels();
        pack();
    }

    private void initPanels()
    {
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        drawingPanel = new DrawingPanel(this);
    }

    private void addPanels()
    {
        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(drawingPanel, BorderLayout.CENTER);
    }
}
