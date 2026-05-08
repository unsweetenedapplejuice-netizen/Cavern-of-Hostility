public class BoostMove extends SpecialMoves{

    int boost;

    public BoostMove() {
        super();
        boost = 0;
    }

    public BoostMove(String n, int x, int y, int b) {
        super(n, x, y);
        boost = b;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

}
