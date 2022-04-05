//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example;
;

import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private final List<JComponent> componentList;
    private JLabel label;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton createBtn;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        this.componentList = new ArrayList();
        this.init();
    }

    private void init() {
        this.initComponents();
        this.initListeners();
        this.addComponents();
    }

    private void initComponents() {
        this.label = new JLabel("Grid size:");
        this.spinner1 = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        this.spinner2 = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        this.createBtn = new JButton("Generate");

        this.componentList.add(this.label);
        this.componentList.add(this.spinner1);
        this.componentList.add(this.spinner2);
        this.componentList.add(this.createBtn);
    }

    private void initListeners() {
        this.createBtn.addActionListener(this::changeCanvas);
    }

    private void addComponents() {
        Iterator var1 = this.componentList.iterator();

        while(var1.hasNext()) {
            JComponent component = (JComponent)var1.next();
            this.add(component);
        }

    }

    private void changeCanvas(ActionEvent e) {
        PrintStream printStream = System.out;
        String var = this.spinner1.getValue().toString();
        printStream.println("Updated canvas size " + var + " by " + this.spinner2.getValue().toString());
        this.frame.gameLogic.init(this.getNrOfRows(), this.getNrOfColumns());
        this.frame.drawingPanel.init(this.getNrOfRows(), this.getNrOfColumns());

    }

    public int getNrOfRows() {
        return (Integer)this.spinner1.getValue();
    }

    public int getNrOfColumns() {
        return (Integer)this.spinner2.getValue();
    }



}
