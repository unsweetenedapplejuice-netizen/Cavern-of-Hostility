import javax.swing.ImageIcon;

public class Slot {
    public ImageIcon pic;
    public int position, size;

    public Slot() {
        pic = new ImageIcon("");
        position = 2;
        size = 1;
    }

    public Slot(ImageIcon p, int l, int s) {
        pic = p;
        position = l;
        size = s;
    }

    public ImageIcon getPic() {
        return pic;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
    }

    public int getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
