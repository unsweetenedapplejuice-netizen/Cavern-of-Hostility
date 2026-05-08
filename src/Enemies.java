import javax.swing.ImageIcon;


public class Enemies extends Character {

    public int size;
    public int type;

    public Enemies() {
        super();
    }

    public Enemies(int HP, int AP, int s, int a, String strnm, SpecialMoves a1, SpecialMoves a2, ImageIcon i, int sz, int t) {
        super(HP, AP, s, a, strnm, a1, a2, i);
        size = sz;
        type = t;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
