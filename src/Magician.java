import javax.swing.ImageIcon;

public class Magician extends Protagonist {

    public Magician(String wd) {
        super();
    }

    public Magician() {
        super(45, 30, 8, 5, "Magician", 1, new FireBurst("Fire Burst", 0, 0, 10), new ColdSnap("Cold Snap", 0, 0, 10), new ImageIcon("MagicianIcon.png"));
    }

}
