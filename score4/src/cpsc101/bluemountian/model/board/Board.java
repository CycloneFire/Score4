package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

public class Board{
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

    public Peg[][] getPegs() {
        return pegs;
    }

    public void setPegs(Peg[][] pegs) { // Avoids copying of reference of pegs
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < pegs[i][j].getBeadCount(); k++) {
                    this.pegs[i][j].addBead(pegs[i][j].getBead(k));
                }
            }
        }
    }

    public void print(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("["+pegs[i][j].getBeadCount()+"]\t");
            }
            System.out.println();
        }
    }
    public void reInit(){
        for(int i=0;i<16;i++)pegs[i/4][i%4]=new Peg();
    }

}