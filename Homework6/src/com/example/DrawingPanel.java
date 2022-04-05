package com.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawingPanel extends JPanel
{
    private final MainFrame frame;
    private int nrOfRows;
    private int nrOfColumns;
    private final int canvasWidth = 400, canvasHeight = 400;
    private int boardWidth;
    private int boardHeight;
    private int centerX;
    private int centerY;
    private int cellWidth;
    private int cellHeight;
    private int stoneSize;
    private BufferedImage image;
    private Graphics2D offscreen;

    public DrawingPanel(MainFrame frame)
    {

        this.frame = frame;
        System.out.println(frame.configPanel.getNrOfRows() + " " + frame.configPanel.getNrOfColumns());
        init(frame.configPanel.getNrOfRows(), frame.configPanel.getNrOfColumns());

    }

    final void init(int rowsNr, int columnsNr)
    {
        this.nrOfRows = rowsNr;
        this.nrOfColumns = columnsNr;
        this.stoneSize = 20;
        this.centerX = stoneSize + 10;
        this.centerY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * centerX) / (columnsNr - 1);
        this.cellHeight = (canvasHeight - 2 * centerY) / (rowsNr - 1);
        this.boardWidth = (columnsNr - 1) * cellWidth;
        this.boardHeight = (rowsNr - 1) * cellHeight;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        createOffscreenImage();
        initCircles();
        repaint();
    }

    private void createOffscreenImage()
    {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    final void initCircles() {
        for(int row=0;row< this.nrOfRows; row++){
            for(int column = 0; column< this.nrOfColumns;column ++){

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawBlueStone(e.getX(), e.getY());
                repaint();

                drawRedStone(e.getX(), e.getY());
                repaint();

        }
        });
    }}}



    @Override
    protected void paintComponent(Graphics graphics)
    {
        paintGrid();
        graphics.drawImage(image, 0, 0, this);
    }

    private void paintGrid()
    {
        offscreen.setColor(Color.DARK_GRAY);

        for (int row = 0; row < nrOfRows; row++)
        {
            int x1 = centerX;
            int y1 = centerY + row * cellHeight;
            int x2 = centerX + boardWidth;
            int y2 = y1;
            offscreen.drawLine(x1, y1, x2, y2);
        }

        for (int column = 0; column < nrOfColumns; column++)
        {
            int x1 = centerX + column * cellWidth;
            int y1 = centerY;
            int x2 = x1;
            int y2 = centerY + boardHeight;
            offscreen.drawLine(x1, y1, x2, y2);
        }

        for (int row = 0; row < nrOfRows; row++)
        {
            for (int col = 0; col < nrOfColumns; col++)
            {
                int x = centerX + col * cellWidth;
                int y = centerY + row * cellHeight;
                offscreen.setColor(Color.LIGHT_GRAY);
                offscreen.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }

    private void drawBlueStone(int x,int y){

//            int x = centerX + column * cellWidth;
//            int y = centerY + row * cellHeight;


        super.paintComponent(offscreen);
        Graphics2D g2 = offscreen;
        g2.setColor(Color.blue);
        g2.drawOval(x,y,20,20);
        g2.fillOval(x,y,20,20);
    }

    private void drawRedStone(int x,int y){

//            int x = centerX + column * cellWidth;
//            int y = centerY + row * cellHeight;


        super.paintComponent(offscreen);
        Graphics2D g2 = offscreen;
        g2.setColor(Color.red);
        g2.drawOval(x,y,20,20);
        g2.fillOval(x,y,20,20);
    }


    void loadImage(BufferedImage image) {
        this.image = image;
        Graphics2D offscreen = image.createGraphics();
        repaint();
    }

  public void saveGame(ActionEvent event) {
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Choose a directory to save your file");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                ImageIO.write(frame.drawingPanel.image, "PNG",
                        new File(fileChooser.getSelectedFile() + "\\test.png"));
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


  public  void loadGame(ActionEvent event) {
        BufferedImage image = null;
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Select an image");
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG images", "png");
            fileChooser.addChoosableFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                image = ImageIO.read(new File(fileChooser.getSelectedFile().getPath()));
                frame.drawingPanel.loadImage(image);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

}}
