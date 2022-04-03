package com.example;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
        repaint();
    }

    private void createOffscreenImage()
    {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

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
}
