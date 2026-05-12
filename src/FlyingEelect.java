import javax.swing.ImageIcon;

public class FlyingEelect extends Enemies{
    
    public FlyingEelect(ImageIcon i) {
        super(17, 4, 8, 0, "Flying Eelect", new Slam("sucker shock", 4), new RaiseGuard("energize", 1), i, 2, 0);
    }

}
