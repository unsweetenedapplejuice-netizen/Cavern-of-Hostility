import javax.swing.ImageIcon;

public class FreezeFace extends Enemies{
    
    public FreezeFace(ImageIcon i) {
        super(14, 5, 6, 1, "Freeze Face", new Slam("slam", 3), new Slam("freeze", 3), i, 1, 1);
    }

}
