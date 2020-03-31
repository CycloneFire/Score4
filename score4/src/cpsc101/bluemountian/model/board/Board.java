package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

public class Board implements Cloneable{
    private Peg[][] pegs = new Peg[4][4];

    public Board(){
        for(int i=0;i<16;i++)pegs[i/4][i%4]=new Peg();  //Initialise empty Pegs
    }

    public void updateBoard(){}     // Used to update view when model changes.

    public Peg getPeg(int i,int j) {
        return pegs[i][j];
    }

    public Peg getPeg(Move move){return pegs[move.getX()][move.getY()];}

    public void addBead(Move move, Player player){
        Boolean added = pegs[move.getX()][move.getY()].addBead(player);
        if(added)updateBoard();
    }

    @Override
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

    public void reInit(){
        for(int i=0;i<16;i++)pegs[i/4][i%4]=new Peg();
    }

}