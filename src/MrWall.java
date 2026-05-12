import javax.swing.ImageIcon;

public class MrWall extends Enemies{
    
    public MrWall(ImageIcon i) {
        super(20, 4, 8, 4, "Mr. Wall", new Slam("Slam", 6), new RaiseGuard("Raise Guard", 2), i, 3, 0);
    }

}
