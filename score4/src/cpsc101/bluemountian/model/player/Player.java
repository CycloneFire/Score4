package cpsc101.bluemountian.model.player;

import java.awt.Color;

/**
 * This class is a base class that can be subclassed into a A.I or Human Player and also as a bead.
 * Objects of this class get set to a bead object subclassed from player.
 */
public class Player {
    private String name;
    private Color color;

    public Player(){
    }
    public Player(String name,Color color){
        this.name=name;
        this.color=color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name+" : "+color.toString();
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("fmkc");
        if(obj instanceof Player){
            System.out.println("tmkc");
            return ((Player) obj).getColor() == color && ((Player) obj).getName().equals(name);
        }
        return false;
    }
}
