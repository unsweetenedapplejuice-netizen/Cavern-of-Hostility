import javax.swing.ImageIcon;

public class Explorer extends Protagonist {

    public Explorer(String wd) {
        super();
    }

    public Explorer() {
        super(30, 24, 6, 7, "Explorer", 3, new Resist("Resist", 0, 0, 3), new Replenish("Replenish", 0, 0, 7), new ImageIcon("ExplorerIcon.png"));
    }
}
