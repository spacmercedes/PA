package com.example;
import java.util.Random;

public class GameLogic
{
    private final MainFrame frame;
    private int player;
    private int lastMoveRow;
    private int lastMoveColumn;
    private int nrOfRows;
    private int nrOfColumns;
    private int[][] stickArray;



    public GameLogic(MainFrame frame)
    {
        this.frame = frame;
        init(frame.configPanel.getNrOfRows(), frame.configPanel.getNrOfColumns());
    }

    final void init(int rowsNr, int columnsNr)
    {
        this.nrOfRows = rowsNr;
        this.nrOfColumns= columnsNr;
        stickArray = new int[rowsNr][columnsNr];
        player = 1;
        lastMoveRow = -1;
        lastMoveColumn = -1;
        generateRandomSticks();
    }

    void generateRandomSticks()
    {
        final Random rand = new Random();
        int numberOfSticks = rand.nextInt((nrOfRows * nrOfColumns) / 8, (nrOfRows * nrOfColumns) / 4);

        int i = 0;
        while (i < numberOfSticks)
        {
            int randomRow = rand.nextInt(nrOfRows);
            int randomColumn = rand.nextInt(nrOfColumns);


            int nextRowPoint = randomRow;
            int nextColumnPoint = randomColumn;

            if (rand.nextInt(2) == 0)
            {
                if (rand.nextInt(2) == 0)
                {nextRowPoint -= 1;
                } else
                {nextRowPoint += 1;
                }
            } else
            {
                if (rand.nextInt(2) == 0)
                {
                    nextColumnPoint -= 1;
                } else
                {nextColumnPoint += 1;
                }
            }

            try
            {
                if (stickArray[nextRowPoint][nextColumnPoint] == 0) {
                    stickArray[nextRowPoint][nextColumnPoint] = 1;
                    stickArray[randomRow][randomColumn] = 1;
                    i++;
                }
            } catch (Exception e)
            {
                assert true;
            }
        }
    }

    public int[][] getStickArray()
    {
        return stickArray;
    }


//
//    protected int setStone(int row, int column)
//    {if (checkMove(row, column))
//        {
//            stoneArray[row][column] = player;
//            lastMoveRow = row;
//            lastMoveColumn = column;
//            if (player == 1) {
//                player = 2;
//                return 1;
//            } else {
//                player = 1;
//                return 2;
//            }
//        } else {
//            return 0;
//        }
//    }
}
