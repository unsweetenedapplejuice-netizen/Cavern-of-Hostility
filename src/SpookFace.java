import javax.swing.ImageIcon;

public class SpookFace extends Enemies {

    public SpookFace(ImageIcon i) {
        super(10, 5, 5, 0, "Spook Face", new Slam("slam", 3), new Slam("slam", 3), i, 1, 0);
    }

}
