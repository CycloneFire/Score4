import cpsc101.bluemountian.model.board.EvaluateBoard;
import cpsc101.bluemountian.model.player.Player;

import java.awt.*;

public class Test {
    public static void main(String[] args) {
        int[][][] test = {
                {
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0}
                }
        };

        EvaluateBoard eval = new EvaluateBoard(test,new Player("Suyash", Color.CYAN));
        System.out.println(eval.getScore());

    }
}
