package cpsc101.bluemountian.view.components;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.model.board.Move3D;
import cpsc101.bluemountian.model.player.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;

/**
 * Provides a way to display the model on screen
 *
 * @author Sebastian
 */
public class BoardComponent extends JComponent{

    private int hoverX = 10;
    private int hoverY = 10;
    private int hoverZ = 0;
    private Color hoverColor = new Color(255, 255, 255, 128);
    private Board model;
    private int maxSize;
    private int xGap;
    private int yGap;
    private Graphics2D g2;
    private int xGapBtwPegs;
    private int yGapBtwPegs;
    private int beadWidth;
    private int beadHeight;
    private Player winner;
    private boolean showText;
    private boolean winAnimation;
    private boolean beadAnimation;
    private int loops;
    private Timer timer;
    private int[] winningBeadsX = new int[4];
    private int[] winningBeadsY = new int[4];
    private int[] winningBeadsZ = new int[4];
    boolean showWinningBeads;


    /**
     * Constructs a board view from provided board model
     * @param model
     */
    public BoardComponent(Board model) {
        this.model = model;
        reInit();
    }

    /**
     * Re-initialises all variables for re-use without dumping this object
     */
    public void reInit(){
        winAnimation = false;
        beadAnimation = false;
        loops=0;
        winner = null;
        showText = false;
        showWinningBeads = false;
        timer = new Timer(100, e -> {
            if(winAnimation) showText = !showText;

            if(beadAnimation) showWinningBeads = !showWinningBeads;
            loops++;
            repaint();
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
    }

    /**
     * Set model for the board view
     * @param model model to set
     */
    public void setModel(Board model) {
        this.model = model;
    }


    /**
     * All coordinates require the Gap to be added to them.
     * To more easily draw the board inside the size-changing square, we can imagine a square with 1000-units
     * long sides. Then, if we want a point in X we just need to multiply the size of the square by X / 1000 and
     * add the corresponding gap.
     *
     * 0 / 1000 is the left/top side of the square and 1000 / 1000 the right/bottom side.
     *
     * @param g
     */
    public void paintComponent(Graphics g) {

        updateData(g);
        drawBoard();
        drawPegs();
        drawHover();
        drawBeads();
        if (beadAnimation && showWinningBeads) drawHighlightedBeads();
        if (winner != null) startWinAnimation();
        if (showText) showVictoryText();

    }

    private void drawHighlightedBeads() {
        for (int i = 0; i < 4; i++) {
            if(model.getPeg(winningBeadsX[i],winningBeadsY[i]).getBead(winningBeadsZ[i]).getColor() == Color.RED){
                g2.setColor(Color.CYAN);
            } else {
                g2.setColor(Color.RED);
            }
            g2.drawRect(
                    xGap + maxSize * 123 / 1000
                            + xGapBtwPegs * winningBeadsY[i]
                            + yGapBtwPegs * winningBeadsX[i],
                    yGap + maxSize * 600 / 1000
                            - yGapBtwPegs * winningBeadsX[i]
                            - winningBeadsZ[i] * (beadHeight + maxSize * 4 / 1000),
                    beadWidth,
                    beadHeight);
        }
    }

    private void startWinAnimation() {
        if (loops == 0) {
            timer.start();
            winAnimation = true;
        }
        if (loops > 15 && loops < 25) {

            winAnimation = false;
            showText = true;
        }
        if (loops >= 5) {
            timer.setDelay(250);
            if (loops > 25) showText = false;
            beadAnimation = true;
        }
    }

    private void updateData(Graphics g) {
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Calculate window size to better fit the game in its boundaries.
        int x = getSize().width;
        int y = getSize().height;

        //If the screen is wider than taller, calculate the gap needed from the left side of the window, and assign the
        // maximum size of the square (the background) the board appears in to y.
        if (x >= y) {
            maxSize = y;
            xGap = (x - y) / 2;
            yGap = 0;
        } else {
            //If the screen is taller, do the opposite.
            maxSize = x;
            xGap = 0;
            yGap = (y - x) / 2;
        }

        xGapBtwPegs = maxSize * 165 / 1000;
        yGapBtwPegs = maxSize * 75 / 1000;

        beadWidth = maxSize * 40 / 1000;
        beadHeight = maxSize * 20 / 1000;
    }

    private void drawBoard() {
        //Draw square (background)
        g2.setColor(new Color(255, 242, 231));
        g2.fill(new Rectangle(xGap, yGap, maxSize, maxSize));

        //Draw board using polygons.
        //Right side of the board
        int[] XVerticesCoords = {
                xGap + maxSize * 660 / 1000,
                xGap + maxSize * 660 / 1000,
                xGap + maxSize * 980 / 1000,
                xGap + maxSize * 980 / 1000};
        int[] YVerticesCoords = {
                yGap + maxSize * 660 / 1000,
                yGap + maxSize * 690 / 1000,
                yGap + maxSize * 390 / 1000,
                yGap + maxSize * 360 / 1000};
        int vertices = 4;
        g2.setColor(new Color(136, 0, 21));
        g2.fillPolygon(XVerticesCoords, YVerticesCoords, vertices);


        //Bottom side of the board
        XVerticesCoords = new int[]{
                xGap + maxSize * 660 / 1000,
                xGap + maxSize * 660 / 1000,
                xGap + maxSize * 20 / 1000,
                xGap + maxSize * 20 / 1000};
        YVerticesCoords = new int[]{
                yGap + maxSize * 660 / 1000,
                yGap + maxSize * 690 / 1000,
                yGap + maxSize * 690 / 1000,
                yGap + maxSize * 660 / 1000};
        g2.setColor(new Color(184, 121, 85));
        g2.fillPolygon(XVerticesCoords, YVerticesCoords, vertices);

        //Top of Board
        XVerticesCoords = new int[]{
                xGap + maxSize * 660 / 1000,
                xGap + maxSize * 20 / 1000,
                xGap + maxSize * 350 / 1000,
                xGap + maxSize * 980 / 1000};
        YVerticesCoords = new int[]{
                yGap + maxSize * 660 / 1000,
                yGap + maxSize * 660 / 1000,
                yGap + maxSize * 360 / 1000,
                yGap + maxSize * 360 / 1000};
        g2.setColor(new Color(242, 192, 123));
        g2.fillPolygon(XVerticesCoords, YVerticesCoords, vertices);
    }

    /**
     * Pegs are a bit more complicated, because they require a gap between each other in a row, as well as in a
     * column, on top of the normal square gap. Each Row also requires to be farther to the right than the one
     * below it since we are aiming for a 3D type of view.
     */
    private void drawPegs() {
        int pegHeight = maxSize * 100 / 1000;
        int pegWidth = maxSize * 10 / 1000;
        int pegBaseHeight = maxSize * 5 / 1000;
        int pegBaseWidth = maxSize * 20 / 1000;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g2.setColor(new Color(184, 121, 85));
                g2.fill(new Rectangle(
                        xGap + maxSize * 138 / 1000
                                + xGapBtwPegs * j
                                + yGapBtwPegs * i,
                        yGap + maxSize * 525 / 1000
                                - yGapBtwPegs * i,
                        pegWidth,
                        pegHeight));
                //Pegs base
                g2.setColor(new Color(136, 0, 21));
                g2.fill(new Rectangle(
                        xGap + maxSize * 133 / 1000
                                + xGapBtwPegs * j
                                + yGapBtwPegs * i,
                        yGap + maxSize * 620 / 1000
                                - yGapBtwPegs * i,
                        pegBaseWidth,
                        pegBaseHeight));
            }
        }
    }

    /**
     * When moving your mouse over a location where a bead can be placed, a semi-transparent bead will appear to
     * help visualizing the final result of the move. Beads also have space between each other.
     */
    private void drawHover() {
        g2.setColor(hoverColor);
        g2.fill(new Rectangle(
                xGap + maxSize * 123 / 1000
                        + xGapBtwPegs * hoverX
                        + yGapBtwPegs * hoverY,
                yGap + maxSize * 600 / 1000
                        - yGapBtwPegs * hoverY
                        - hoverZ * (beadHeight + maxSize * 4 / 1000),
                beadWidth,
                beadHeight));
    }

    /**
     * The bead information is taken directly from the model and then displayed. Beads also have space between them.
     */
    private void drawBeads() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (model.getPeg(i, j).getBead(k).isSet()) {
                        g2.setColor(model.getPeg(i, j).getBead(k).getColor());
                        g2.fillRect(
                                xGap + maxSize * 123 / 1000
                                        + xGapBtwPegs * j
                                        + yGapBtwPegs * i,
                                yGap + maxSize * 600 / 1000
                                        - yGapBtwPegs * i
                                        - k * (beadHeight + maxSize * 4 / 1000),
                                beadWidth,
                                beadHeight);
                    }
                }
            }
        }
    }

    /**
     * Draws a semi-transparent bead on provided indices
     *
     * @param i x co-ordinate
     * @param j y co-ordinate
     * @param k z co-ordinate
     * @param hoverColor Color of the hover effect
     */
    public void setHoverLocation(int i, int j, int k, Color hoverColor) {
        hoverX = j;
        hoverY = i;
        if (k < 4) hoverZ = k;
        else hoverZ = 3;
        this.hoverColor = new Color(hoverColor.getRed(), hoverColor.getGreen(), hoverColor.getBlue(), 128);
        repaint();
    }



    private void showVictoryText(){
        String text = winner.getName().toUpperCase() + " WINS!!!";

        Font font = new Font("Arial", Font.BOLD, maxSize * 90/1000);

        TextLayout textTl = new TextLayout(text, font, g2.getFontRenderContext());

        Shape outline = textTl.getOutline(null);
        Rectangle outlineBounds = outline.getBounds();

        double x = xGap + (maxSize - outlineBounds.getWidth())/2;
        double y = yGap + (maxSize - outlineBounds.getHeight())/2;

        g2.translate(x,y );

        g2.setColor(Color.WHITE);
        g2.fill(outline);

        g2.setColor(Color.BLACK);
        g2.draw(outline);
        g2.setClip(outline);
    }

    /**
     * Set the winner for this board
     * @param winner player to set as winner
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Sets the winning beads for this board
     * @param move3D winning move
     */
    public void setWinningBeads(Move3D[] move3D){
        for(int i = 0; i < 4; i++){
            winningBeadsX[i] = move3D[i].getX();
            winningBeadsY[i] = move3D[i].getY();
            winningBeadsZ[i] = move3D[i].getZ();
        }
    }


}

