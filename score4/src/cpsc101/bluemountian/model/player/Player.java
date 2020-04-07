package cpsc101.bluemountian.model.player;

import java.awt.Color;

/**
 * Provides a Player to the game that can have a name and a color
 *
 * @author Suyash
 */
public class Player {
    private String name;
    private Color color;

    /**
     * Constructs a player without name or color
     */
    public Player(){
    }

    /**
     * Constructs a player with given name and color
     * @param name name of player
     * @param color color of player
     */
    public Player(String name,Color color){
        this.name=name;
        this.color=color;
    }

    /**
     *
     * @return color of this player
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this player
     * @param color color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return name of this player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this player
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Used for console testing purposes
     * @return String representation of player's name and color.
     */
    @Override
    public String toString(){
        return name+" : "+color.toString();
    }

    /**
     * Compares two colors
     * @param obj object to compare to
     * @return result of comparision
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player){
            return ((Player) obj).getColor() == color && ((Player) obj).getName().equals(name);
        }
        return false;
    }
}
