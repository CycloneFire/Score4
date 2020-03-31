package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

import java.awt.*;

public class EvaluateBoard{
    private static final int PATTERN_CONSTANT_1 = 3;  // Weight 1 cell alone carries in board evaluation
    private static final int PATTERN_CONSTANT_2 = 10;  // Weight 2 in a row carries in board evaluation
    private static final int PATTERN_CONSTANT_3 = 30;  // Weight 3 in  a row carries in board evaluation
    private static final int WINNING_CONSTANT = 500;    // Kind of max/min evaluation a board could have.
    private static final int CUBE_SIDE = 4; // Just a reusable constant
    private Board model;    // Board to evaluate
    private Player maximizingPlayer;
    private int score=0;
    private int[][][] grid = new int[4][4][4];

    public EvaluateBoard(int[][][] grid, Player maximizingPlayer){
        this.grid=grid;
        this.maximizingPlayer=maximizingPlayer;
    }

    public EvaluateBoard(Board model, Player maximizingPlayer){
        this.maximizingPlayer = maximizingPlayer;
        this.model = model;
        boardToGrid();
    }

    public int getScore() {
        evaluate();
        return score;
    }

    private void evaluate() {
        Bead winCheck = new WinningCondition(model, maximizingPlayer.getColor()).winCheck();
        if (winCheck.isSet()) {
            score = maxCoEff(winCheck.getColor())*WINNING_CONSTANT;
            return;
        }

        // Finds single cell / 2 in a row / 3 in a rows and score them accordingly.

        for (int i = 0; i < CUBE_SIDE; i++) {   // left to right lines
            for (int j = 0; j < CUBE_SIDE; j++) {
                score+=linePatternScoreFinder(new int[]{grid[i][0][j],grid[i][1][j],grid[i][2][j],grid[i][3][j]});
            }
        }
        for (int i = 0; i < CUBE_SIDE; i++) {   // back to front lines
            for (int j = 0; j < CUBE_SIDE; j++) {
                score+=linePatternScoreFinder(new int[]{grid[0][i][j],grid[1][i][j],grid[2][i][j],grid[3][i][j]});
            }
        }
        for (int i = 0; i < CUBE_SIDE; i++) {   // hanging lines
            for (int j = 0; j < CUBE_SIDE; j++) {
                score+=linePatternScoreFinder(new int[]{grid[i][j][0],grid[i][j][1],grid[i][j][2],grid[i][j][3]});
            }
        }

        // 28 diagonals ahead

        // 8 here
        for (int i = 0; i < CUBE_SIDE; i++) { // Top view cross diagonals (all floors - bottom to top)
            score+=linePatternScoreFinder(new int[]{grid[0][0][i], grid[1][1][i], grid[2][2][i], grid[3][3][i]});
            // Top left to bottom right relatively
            score+=linePatternScoreFinder(new int[]{grid[0][3][i], grid[1][2][i], grid[2][1][i], grid[3][0][i]});
            // Top right to bottom left relatively
        }

        // 8 here
        for (int i = 0; i < CUBE_SIDE; i++) { // Front view cross diagonals (all rows - front to back)
            score+=linePatternScoreFinder(new int[]{grid[i][0][0], grid[i][1][1], grid[i][2][2], grid[i][3][3]});
            // Top left to bottom right relatively
            score+=linePatternScoreFinder(new int[]{grid[i][0][3], grid[i][1][2], grid[i][2][1], grid[i][3][0]});
            // Top right to bottom left relatively
        }

        // 8 here
        for (int i = 0; i < CUBE_SIDE; i++) { // Side view cross diagonals (all columns - left to right)
            score+=linePatternScoreFinder(new int[]{grid[0][i][0], grid[1][i][1], grid[2][i][2], grid[3][i][3]});
            // Top left to bottom right relatively
            score+=linePatternScoreFinder(new int[]{grid[0][i][3], grid[1][i][2], grid[2][i][1], grid[3][i][0]});
            // Top right to bottom left relatively
        }

        // Mid-body diagonals (4)

        score+=linePatternScoreFinder(new int[]{grid[0][0][0], grid[1][1][1], grid[2][2][2], grid[3][3][3]});
        //Main mid-body diagonal
        score+=linePatternScoreFinder(new int[]{grid[0][0][3], grid[1][1][2], grid[2][2][1], grid[3][3][0]});
        //Anti-Main mid-body diagonal
        score+=linePatternScoreFinder(new int[]{grid[3][0][0], grid[2][1][1], grid[1][2][2], grid[0][3][3]});
        //Inverse mid-body diagonal
        score+=linePatternScoreFinder(new int[]{grid[3][0][3], grid[2][1][2], grid[1][2][1], grid[0][3][0]});
        //Anti-Inverse mid-body diagonal
    }

    private int linePatternScoreFinder(int[] beads){
        int length=0;   // Start with a base value of pattern as 0
        int patternStart=0; // Pattern starts for which player
        for (int i = 0; i < beads.length; i++) {
            if(beads[i]!=0 && i<beads.length-1){    // If bead is set
                if(length==0){
                    patternStart=beads[i];
                    length++;
                }
                if(beads[i]==beads[i+1]){   // If there's a follow up pattern
                    length++;
                }
                if(beads[i]!=beads[i+1] && beads[i+1]!=0){   // If pattern breaks just push it back too much
                    length-=6;
                }
            }else{
                if(length==0 && beads[i]!=0){
                    patternStart=beads[i];
                    length++;
                }
            }
        }
        if(length<1)return 0;   // If there's a broken pattern return an evaluation for this window as 0 ( useless )
        else if(length==1) return maxCoEff(patternStart)*(PATTERN_CONSTANT_1); // for 1 cell alone
        else if(length==2) return maxCoEff(patternStart)*(PATTERN_CONSTANT_2); // for 2 in a row
        else return maxCoEff(patternStart)*(PATTERN_CONSTANT_3); // for 3 in a row
    }

    private int maxCoEff(int positionValue){    // Give scoring a positivity/negativity based on grid position passed
        if(positionValue==2)return 1;
        return -1;
    }

    private int maxCoEff(Color colorToCheck){   // Overloaded to compare color with maximizer
        if(maximizingPlayer.getColor()==colorToCheck)return 1;
        return -1;
    }

    private void boardToGrid(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (model.getPeg(i,j).getBead(k).isSet()) {
                        if(model.getPeg(i,j).getBead(k).getColor()==maximizingPlayer.getColor())grid[i][j][k] =2;
                        else grid[i][j][k]=1;
                    }else grid[i][j][k] = 0;
                }
            }
        }
    }

}
