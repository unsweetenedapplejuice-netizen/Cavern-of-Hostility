import javax.swing.ImageIcon;

public class Protagonist extends Character {

    public int type;

    public Protagonist() {
        super();
        type = 0;
    }

    public Protagonist(int HP, int AP, int s, int a, String n, int t, SpecialMoves m1, SpecialMoves m2, ImageIcon i) {
        super(HP, AP, s, a, n, m1, m2, i);
        type = t;
    }
  

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
