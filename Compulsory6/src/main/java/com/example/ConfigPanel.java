package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ConfigPanel extends JPanel {
   private final MainFrame frame;
    private  JLabel label;
    private JSpinner spinner1, spinner2;
    private JButton button;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {

        label = new JLabel("Grid size:");
        this.frame.add(label);
        label.setBounds(50,10,100,20);
      //label.setBackground(Color.MAGENTA);

        SpinnerModel value = new SpinnerNumberModel(10,2,100,1);
        spinner1 = new JSpinner(value);
        spinner1.setBounds(120,10,50,20);
        this.frame.add(spinner1);
       setSize(150,150);
       setLayout(null);
       setVisible(true);


        SpinnerModel value2 = new SpinnerNumberModel(10,2,100,1);
        spinner2 = new JSpinner(value2);
        spinner2.setBounds(180,10,50,20);
        this.frame.add(spinner2);
        setSize(150,150);
        setLayout(null);
        setVisible(true);

        button = new JButton("Create");
        this.frame.add(button);
        button.setBounds(250,10,80,20);
        button.addActionListener(this::changeCanvas);


        //JPanel uses FlowLayout by default
       // add(spinner);
    }



    private void changeCanvas(ActionEvent e ){
        System.out.println("New size of the canvas " + spinner1.getValue().toString() + " by " + spinner2.getValue().toString());
        frame.canvas.init(getNrOfRows(),getNrOfColumns());
    }

    public int getNrOfRows(){
        return (int) spinner1.getValue();
    }

    public int getNrOfColumns(){
        return (int) spinner2.getValue();
    }


}
