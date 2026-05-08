import javax.swing.ImageIcon;

public class ChocoBlorp extends Enemies {

    public ChocoBlorp(ImageIcon i) {
        super(12, 5, 5, 1, "Chocolate Blorp", new Slam("slam", 3), new RaiseGuard("raise guard", 1), i, 1, 1);
    }

}
