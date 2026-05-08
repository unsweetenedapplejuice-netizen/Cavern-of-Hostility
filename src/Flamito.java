import javax.swing.ImageIcon;

public class Flamito extends Enemies {

    public Flamito(ImageIcon i) {
        super(12, 6, 6, 0, "Flamito", new Slam("slam", 2), new Feed("feed", 4), i, 1, 2);
    }

}
