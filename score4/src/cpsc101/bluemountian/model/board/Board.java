package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

/**
 * Provides a board for the game to use containing 16 pegs
 *
 * @author Suyash
 */
public class Board{
    private Peg[][] pegs = new Peg[4][4];

    /**
     * Constructs an empty board.
     */
    public Board(){
        for(int i=0;i<16;i++)pegs[i/4][i%4]=new Peg();  //Initialise empty Pegs
    }

    /**
     * Used in watchedBoard to make sure view is updated when board is updated
     */
    public void updateBoard(){}     // Used to update view when model changes.

    /**
     *
     * @param i row num of peg
     * @param j column num of peg
     * @return Returns the peg on provided indices
     */
    public Peg getPeg(int i,int j) {
        return pegs[i][j];
    }

    /**
     *
     * @param move move containing x and y position of peg
     * @return the peg at given move co-ordinates
     */
    public Peg getPeg(Move move){return pegs[move.getX()][move.getY()];}

    /**
     * Adds a bead to the board
     *
     * @param move move containing x and y position to place bead.
     * @param player player whose bead is being added to board.
     */
    public void addBead(Move move, Player player){
        Boolean added = pegs[move.getX()][move.getY()].addBead(player);
        if(added)updateBoard();
    }

    /**
     *
     * @return all the pegs
     */
    public Peg[][] getPegs() {
        return pegs;
    }

    /**
     * Generates new pegs of similar values but in distinct memory locations
     * @param pegs pegs to set from
     */
    public void setPegs(Peg[][] pegs) { // Avoids copying of reference of pegs
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < pegs[i][j].getBeadCount(); k++) {
                    this.pegs[i][j].addBead(pegs[i][j].getBead(k));
                }
            }
        }
    }

    /**
     * Prints board, used for testing purposes
     */
    public void print(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("["+pegs[i][j].getBeadCount()+"]\t");
            }
            System.out.println();
        }
    }

    /**
     * Empties all the pegs
     */
    public void reInit(){
        for(int i=0;i<16;i++)pegs[i/4][i%4]=new Peg();
    }

}