import javax.swing.ImageIcon;

public class LilGlacestorm extends Enemies{

    public LilGlacestorm(ImageIcon i) {
        super(18, 6, 7, 2, "Lil' Glacestorm", new Slam("Blizzard", 4), new RaiseGuard("Gust", 1), i, 2, 0);
    }


}
