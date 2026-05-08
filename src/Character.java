import javax.swing.ImageIcon;

public class Character {

    public int hp;
    public int ap;
    public int strngth;
    public int armrp;
    public String name;
    public SpecialMoves action1;
    public SpecialMoves action2;
    public ImageIcon icon;

    public Character() {
        hp = 1;
        ap = 1;
        strngth = 1;
        armrp = 0;
        name = "walter the defaulter";
        action1 = new Replenish();
        action2 = new Resist();
        icon = new ImageIcon();
    }

    public Character(int HP, int AP, int s, int a, String strnm, SpecialMoves a1, SpecialMoves a2, ImageIcon i) {
        hp = HP;
        ap = AP;
        strngth = s;
        armrp = a;
        name = strnm;
        action1 = a1;
        action2 = a2;
        icon = i;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getStrngth() {
        return strngth;
    }

    public void setStrngth(int strngth) {
        this.strngth = strngth;
    }

    public int getArmrp() {
        return armrp;
    }

    public void setArmrp(int armrp) {
        this.armrp = armrp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpecialMoves getAction1() {
        return action1;
    }

    public SpecialMoves getAction2() {
        return action2;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
}
