package com.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JPanel
{
    private final MainFrame frame;
    private final List<JComponent> componentList;
    public RenderedImage image;
    private JButton loadBtn;
    private JButton saveBtn;
    private JButton exitBtn;
  private DrawingPanel drawingPanel;

    public ControlPanel(MainFrame frame)
    {
        this.frame = frame;
        this.drawingPanel= drawingPanel;
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
        frame.drawingPanel.loadGame(event);

        System.out.println("Load");
    }

    private void saveEvent(ActionEvent event)
    {

        frame.drawingPanel.saveGame(event);
        System.out.println("Save");
    }

    private void exitEvent(ActionEvent event)
    {
        frame.dispose();
    }




}
