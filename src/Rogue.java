import javax.swing.ImageIcon;

public class Rogue extends Enemies {
    
    public Rogue(ImageIcon i) {
        super(20, 5, 9, 2, "Rogue", new Slam("Super Slash", 5), new RaiseGuard("Sharpen", 1), i, 3, 0);
    }

}
