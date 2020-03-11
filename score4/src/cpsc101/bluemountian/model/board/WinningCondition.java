package cpsc101.bluemountian.model.board;

public class WinningCondition {
    private int[] Winner = new int[4];
    private int[][][] grid = new int[4][4][4];
    private int movecount;

    public int corX(int i){
        int x;
        x = (i - 1) % 4;
        return x;}

    public int corY(int i){
        int y;
        y = ((i - 1) / 4);
        return y;}
    
    public void winCheck(){
        
      for (int i = 1; i < 17; i++)
        {
            int[] theGrid=new int[2];
            theGrid[0] =corX(i);
            theGrid[1] =corY(i);
            if ((grid[theGrid[0]][theGrid[1]][0] == grid[theGrid[0]][theGrid[1]][1]) &&
                    (grid[theGrid[0]][theGrid[1]][1] == grid[theGrid[0]][theGrid[1]][2]) &&
                    (grid[theGrid[0]][theGrid[1]][2] == grid[theGrid[0]][theGrid[1]][3]) &&
                    (grid[theGrid[0]][theGrid[1]][3] != 0))
            {
                Winner[0] = grid[theGrid[0]][theGrid[1]][0];
            } }
    
      if (Winner[0] == 0){
            for (int i = 0; i < 4; i++){
                for (int k = 0; k < 4; k++){
                    if ((grid[i][0][k] == grid[i][1][k]) &&
                            (grid[i][1][k] == grid[i][2][k]) &&
                            (grid[i][2][k] == grid[i][3][k]) &&
                            (grid[i][3][k] != 0))
                    {
                        Winner[0] = grid[i][0][k];
                    } }
    
                if ((grid[i][0][0] == grid[i][1][1]) &&
                        (grid[i][1][1] == grid[i][2][2]) &&
                        (grid[i][2][2] == grid[i][3][3]) &&
                        (grid[i][3][3] != 0))
                {
                    Winner[0] = grid[i][0][0];
                }
    
                if ((grid[i][0][3] == grid[i][1][2]) &&
                        (grid[i][1][2] == grid[i][2][1]) &&
                        (grid[i][2][1] == grid[i][3][0]) &&
                        (grid[i][3][0] != 0))
                {
                    Winner[0] = grid[i][0][3];
                } } }
    
      if (Winner[0] == 0)
        {
            for (int i = 0; i < 4; i++)
            {
                for (int k = 0; k < 4; k++)
                {
                    if ((grid[0][i][k] == grid[1][i][k]) &&
                            (grid[1][i][k] == grid[2][i][k]) &&
                            (grid[2][i][k] == grid[3][i][k]) &&
                            (grid[3][i][k] != 0))
                    {
                        Winner[0] = grid[0][i][k];
                    } }
    
                if ((grid[0][i][0] == grid[1][i][1]) &&
                        (grid[1][i][1] == grid[2][i][2]) &&
                        (grid[2][i][2] == grid[3][i][3]) &&
                        (grid[3][i][3] != 0))
                {
                    Winner[0] = grid[0][i][0];
                }
    
                if ((grid[0][i][3] == grid[1][i][2]) &&
                        (grid[1][i][2] == grid[2][i][1]) &&
                        (grid[2][i][1] == grid[3][i][0]) &&
                        (grid[3][i][0] != 0))
                {
                    Winner[0] = grid[0][i][3];
                } } }
    
      if (Winner[0] == 0){
            for (int k = 0; k < 4; k++){
                if ((grid[0][0][k] == grid[1][1][k]) &&
                        (grid[1][1][k] == grid[2][2][k]) &&
                        (grid[2][2][k] == grid[3][3][k]) &&
                        (grid[3][3][k] != 0))
                {
                    Winner[0] = grid[0][0][k];
                } }
    
            if ((grid[0][0][0] == grid[1][1][1]) &&
                    (grid[1][1][1] == grid[2][2][2]) &&
                    (grid[2][2][2] == grid[3][3][3]) &&
                    (grid[3][3][3] != 0))
            {
                Winner[0] = grid[0][0][0];
            }
    
            if ((grid[0][0][3] == grid[1][1][2]) &&
                    (grid[1][1][2] == grid[2][2][1]) &&
                    (grid[2][2][1] == grid[3][3][0]) &&
                    (grid[3][3][0] != 0))
            {
                Winner[0] = grid[0][0][3];
            } }
    
      if (Winner[0] == 0){
            int i = 4;
            for (int k=0;k<4;k++){
                if ((grid[0][3][k] == grid[1][2][k]) &&
                        (grid[1][2][k] == grid[2][1][k]) &&
                        (grid[2][1][k] == grid[3][0][k]) &&
                        (grid[3][0][k] != 0))
                {
                    Winner[0] = grid[0][3][k];
                } }
    
            if ((grid[0][3][0] == grid[1][2][1]) &&
                    (grid[1][2][1] == grid[2][1][2]) &&
                    (grid[2][1][2] == grid[3][0][3]) &&
                    (grid[3][0][3] != 0))
            {
                Winner[0] = grid[0][3][0];
            }
    
            if ((grid[0][3][3] == grid[1][2][2]) &&
                    (grid[1][2][2] == grid[2][1][1]) &&
                    (grid[2][1][1] == grid[3][0][0]) &&
                    (grid[3][0][0] != 0))
            {
                Winner[0] = grid[0][3][3];
            } }
    
      if (movecount == 64)
        {
            Winner[0] = 3;
        }
    
      if (Winner[0] != 0)
        {
            if (Winner[0] == 1)
            {
               System.out.print("Winner is White");
            }
            else if (Winner[0] == 2)
            {
                System.out.print("Winner is Black");
            }
            else if (Winner[0] == 3)
            {
                System.out.print("Match is Draw");
            }
    } }

}

