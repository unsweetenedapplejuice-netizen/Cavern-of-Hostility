import javax.swing.ImageIcon;

public class PurpleShrop extends Enemies {
    
    public PurpleShrop(ImageIcon i) {
        super(15, 4, 5, 1, "Purple Shrop", new Slam("Thorn Attack", 5), new Slam("poison leech", 6), i, 1, 1);
    }

}
