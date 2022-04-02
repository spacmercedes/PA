package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
  //  JPanel panel = new JPanel();
   private JButton exitBtn = new JButton("Exit");
  private  JButton loadBtn = new JButton("Load");
  private   JButton saveBtn = new JButton("Save");
    //create all buttons (Load, Exit, etc.)

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {

      //frame.setLayout(new GridLayout(3,4,5,10));
        frame.setSize(425,500);

       add(exitBtn);
       add(loadBtn);
       add(saveBtn);
        //add all buttons ...TODO
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::exitGame);
        loadBtn.addActionListener(this::exitGame);

    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void saveGame(ActionEvent e){
        System.out.println("Saved");
    }

    private void loadGame(ActionEvent e){
        System.out.println("Loaded");
    }

}
