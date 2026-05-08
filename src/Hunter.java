import javax.swing.ImageIcon;

public class Hunter extends Protagonist {

    public Hunter(String wd) {
        super();
    }

    public Hunter() {
        super(40, 18, 10, 3, "Hunter", 2, new Buff("Buff", 0, 0, 3), new Skewer("Skewer", 0, 0, 18), new ImageIcon("HunterIcon.png"));
    }

}
