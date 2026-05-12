import javax.swing.ImageIcon;

public class Strawblorp extends Enemies {

    public Strawblorp(ImageIcon i) {
        super(13, 3, 7, 1, "Strawberry Blorp", new Slam("flare", 4), new RaiseGuard("raise guard", 1), i, 1, 2);
    }

}
