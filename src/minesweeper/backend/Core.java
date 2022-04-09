package minesweeper.backend;

public class Core
{
    private int row;
    private int column;
    private int mines;
    private boolean[][] map;

    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }

    public void acceptMapData(String rowString, String columnString, String minesString)
    {
        try
        {
            row = Integer.parseInt(rowString);
            column = Integer.parseInt(columnString);
            mines = Integer.parseInt(minesString);
        }
        catch (NumberFormatException exception)
        {
            row = 10;
            column = 10;
            mines = 10;
        }

        map = new boolean[row][column];
    }

    public void generateMap()
    {
        double chance = (double) mines / (row * column);
        int plantMines = mines;
        while (plantMines > 0)
            for (int i = 0; i < row; i++)
                for (int j = 0; j < column; j++)
                    if (map[i][j] = Math.random() < chance)
                        plantMines--;
    }

    public int near(int x, int y)
    {
        if (map[x][y])
            return -1;
        else
        {
            int count = 0;
            for (int i = x - 1; i <= x + 1; i++)
                for (int j = y - 1; j <= y + 1; j++)
                    if (i >= 0 && j >= 0 && i < row && j < row && map[i][j])
                        count++;
            return count;
        }
    }
}
