package com.example;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JPanel
{
    private final MainFrame frame;
    private final List<JComponent> componentList;
    private JButton loadBtn;
    private JButton saveBtn;
    private JButton exitBtn;

    public ControlPanel(MainFrame frame)
    {
        this.frame = frame;
        componentList = new ArrayList<>();
        init();
    }

    private void init()
    {
        initComponents();
        initListeners();
        addComponents();
    }

    private void initComponents()
    {
        loadBtn = new JButton("Load");
        saveBtn = new JButton("Save");
        exitBtn = new JButton("Exit");

        componentList.add(loadBtn);
        componentList.add(saveBtn);
        componentList.add(exitBtn);
    }

    private void initListeners()
    {
        loadBtn.addActionListener(this::loadEvent);
        saveBtn.addActionListener(this::saveEvent);
        exitBtn.addActionListener(this::exitEvent);
    }

    private void addComponents()
    {
        for (JComponent component : componentList)
        {
            add(component);
        }
    }

    private void loadEvent(ActionEvent event)
    {
        System.out.println("Load");
    }

    private void saveEvent(ActionEvent event)
    {
        System.out.println("Save");
    }

    private void exitEvent(ActionEvent event)
    {
        frame.dispose();
    }
}
