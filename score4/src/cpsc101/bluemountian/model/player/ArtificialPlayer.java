package cpsc101.bluemountian.model.player;

import cpsc101.bluemountian.model.board.*;

import java.awt.*;
import java.util.Random;

public class ArtificialPlayer extends Player implements CanPlay{
    private Move move;
    private Boolean hasMove = false;
    private Player opposition;

    public ArtificialPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public Move getMove() {
        hasMove=false;
        return move;
    }

    @Override
    public void resetMove() {
        hasMove=false;
    }

    public void setMove(Board board,Player opposition){
        hasMove=true;
        Random random = new Random();
        move=new Move(random.nextInt(4),random.nextInt(4));
        //this.opposition=opposition;
        //miniMax(board,3,true);
    };


    private int miniMax(Board board, int depth, boolean maximizingPlayer){
        if(depth==0 || new WinningCondition(board,this.getColor()).winCheck().isSet()){
            // Stop evaluating once highest depth is reached or game has ended
            return (new EvaluateBoard(board,this).getScore());
        }
        if(maximizingPlayer){
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < 4; i++) {   // Looping through all available cells
                for (int j = 0; j < 4; j++) {
                    System.out.println("ILOOOPED: "+depth+" I am max");
                    try{
                        Board modifiedBoard = (Board)board.clone();
                        // Making a clone of current board, so as to not change it when predicting.
                        if(board.getPeg(new Move(i,j)).canPlace()){
                            modifiedBoard.addBead(new Move(i,j),this);
                            int eval = miniMax(modifiedBoard,depth-1,false);
                            System.out.println(eval+" max");
                            if(eval>maxEval){
                                move=new Move(i,j);
                                maxEval=eval;
                            }
                        }
                    }catch (CloneNotSupportedException c){
                        System.out.println("Cloning error");
                    }
                }
            }
            System.out.println("maxEval: "+maxEval);
            return maxEval;
        }else{
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {   // Looping through all available cells
                for (int j = 0; j < 4; j++) {
                    System.out.println("ILOOOPED: "+depth+" I am min");
                    try{
                        Board modifiedBoard= (Board) board.clone();
                        // Making a clone of current board, so as to not change it while predicting.
                        if(board.getPeg(new Move(i,j)).canPlace()){
                            modifiedBoard.addBead(new Move(i,j),opposition);
                            int eval = miniMax(modifiedBoard,depth-1,true);
                            System.out.println(eval+" min");
                            if(eval<minEval){
                                minEval=eval;
                            }
                        }
                    }catch (CloneNotSupportedException c){
                        System.out.println("Cloning error");
                    }
                }
            }
            System.out.println("minEval: "+minEval);
            return minEval;
        }
    }

}
