package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

import java.awt.*;

public class WinningCondition {
    private Bead winner = new Bead();
    private int[][][] grid = new int[4][4][4];
    private Board model;
    private int moveCount;
    private Color p1Col;

    public WinningCondition(Board model,Color p1Col){
        this.model=model;
        this.p1Col=p1Col;
        boardToGrid();
        
    }

    private void boardToGrid(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (model.getPeg(i,j).getBead(k).isSet()) {
                        moveCount++;
                        if(model.getPeg(i,j).getBead(k).getColor()==p1Col)grid[i][j][k] =1;
                        else grid[i][j][k]=2;
                        
                    }else grid[i][j][k] = 0;
                }
            }
        }
    }

    public int corX(int i){
        int x;
        x = (i - 1) % 4;
        return x;}

    public int corY(int i){
        int y;
        y = ((i - 1) / 4);
        return y;}
    
    public Bead winCheck(){
        boardToGrid();
      for (int i = 1; i < 17; i++)
        {
            int[] coOrd=new int[2];
            coOrd[0] =corX(i);
            coOrd[1] =corY(i);
            if ((grid[coOrd[0]][coOrd[1]][0] == grid[coOrd[0]][coOrd[1]][1]) &&
                    (grid[coOrd[0]][coOrd[1]][1] == grid[coOrd[0]][coOrd[1]][2]) &&
                    (grid[coOrd[0]][coOrd[1]][2] == grid[coOrd[0]][coOrd[1]][3]) &&
                    (grid[coOrd[0]][coOrd[1]][3] !=0))
            {
                // When win is vertical, all beads have information about winning player.
                winner.setBead(model.getPeg(new Move(coOrd[0],coOrd[1])).getBead(0));
            } }
    
      if (!winner.isSet()){
            for (int i = 0; i < 4; i++){
                for (int k = 0; k < 4; k++){
                    if ((grid[i][0][k] == grid[i][1][k]) &&
                            (grid[i][1][k] == grid[i][2][k]) &&
                            (grid[i][2][k] == grid[i][3][k]) &&
                            (grid[i][3][k] != 0))
                    {
                        winner.setBead(model.getPeg(new Move(i,0)).getBead(k));
                    } }
    
                if ((grid[i][0][0] == grid[i][1][1]) &&
                        (grid[i][1][1] == grid[i][2][2]) &&
                        (grid[i][2][2] == grid[i][3][3]) &&
                        (grid[i][3][3] !=0))
                {
                    winner.setBead(model.getPeg(new Move(i,0)).getBead(0));
                }
    
                if ((grid[i][0][3] == grid[i][1][2]) &&
                        (grid[i][1][2] == grid[i][2][1]) &&
                        (grid[i][2][1] == grid[i][3][0]) &&
                        (grid[i][3][0] !=0))
                {
                    winner.setBead(model.getPeg(new Move(i,0)).getBead(3));
                } } }
    
      if (!winner.isSet())
        {
            for (int i = 0; i < 4; i++)
            {
                for (int k = 0; k < 4; k++)
                {
                    if ((grid[0][i][k] == grid[1][i][k]) &&
                            (grid[1][i][k] == grid[2][i][k]) &&
                            (grid[2][i][k] == grid[3][i][k]) &&
                            (grid[3][i][k] !=0))
                    {
                        winner.setBead(model.getPeg(new Move(0,i)).getBead(k));
                    } }
    
                if ((grid[0][i][0] == grid[1][i][1]) &&
                        (grid[1][i][1] == grid[2][i][2]) &&
                        (grid[2][i][2] == grid[3][i][3]) &&
                        (grid[3][i][3] !=0))
                {
                    winner.setBead(model.getPeg(new Move(0,i)).getBead(0));
                }
    
                if ((grid[0][i][3] == grid[1][i][2]) &&
                        (grid[1][i][2] == grid[2][i][1]) &&
                        (grid[2][i][1] == grid[3][i][0]) &&
                        (grid[3][i][0] !=0))
                {
                    winner.setBead(model.getPeg(new Move(0,i)).getBead(3));
                } } }
    
      if (!winner.isSet()){
            for (int k = 0; k < 4; k++){
                if ((grid[0][0][k] == grid[1][1][k]) &&
                        (grid[1][1][k] == grid[2][2][k]) &&
                        (grid[2][2][k] == grid[3][3][k]) &&
                        (grid[3][3][k] !=0))
                {
                    winner.setBead(model.getPeg(new Move(0,0)).getBead(k));
                } }
    
            if ((grid[0][0][0] == grid[1][1][1]) &&
                    (grid[1][1][1] == grid[2][2][2]) &&
                    (grid[2][2][2] == grid[3][3][3]) &&
                    (grid[3][3][3] !=0))
            {
                winner.setBead(model.getPeg(new Move(0,0)).getBead(0));
            }
    
            if ((grid[0][0][3] == grid[1][1][2]) &&
                    (grid[1][1][2] == grid[2][2][1]) &&
                    (grid[2][2][1] == grid[3][3][0]) &&
                    (grid[3][3][0] !=0))
            {
                winner.setBead(model.getPeg(new Move(0,0)).getBead(3));
            } }
    
      if (!winner.isSet()){
            int i = 4;
            for (int k=0;k<4;k++){
                if ((grid[0][3][k] == grid[1][2][k]) &&
                        (grid[1][2][k] == grid[2][1][k]) &&
                        (grid[2][1][k] == grid[3][0][k]) &&
                        (grid[3][0][k] !=0))
                {
                    winner.setBead(model.getPeg(new Move(0,3)).getBead(k));
                } }
    
            if ((grid[0][3][0] == grid[1][2][1]) &&
                    (grid[1][2][1] == grid[2][1][2]) &&
                    (grid[2][1][2] == grid[3][0][3]) &&
                    (grid[3][0][3] !=0))
            {
                winner.setBead(model.getPeg(new Move(0,3)).getBead(0));
            }
    
            if ((grid[0][3][3] == grid[1][2][2]) &&
                    (grid[1][2][2] == grid[2][1][1]) &&
                    (grid[2][1][1] == grid[3][0][0]) &&
                    (grid[3][0][0] !=0))
            {
                winner.setBead(model.getPeg(new Move(0,3)).getBead(3));
            } }
    
      if (moveCount == 64)
        {
            winner= new Bead(new Player("DRAW",Color.CYAN));
        }
            return winner;
       }

}

