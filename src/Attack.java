public class Attack extends SpecialMoves{

    int damage;

    public Attack() {
        super();
        damage = 0;
    }

    public Attack(String n, int x, int y, int d) {
        super(n, x, y);
        damage = d;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
