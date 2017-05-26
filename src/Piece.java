import java.util.ArrayList;

/**
 * Created by Oscar on 5/10/17.
 */
public abstract class Piece implements Cloneable {
    private int color;
    private String pictureLocation;

    public abstract ArrayList<Cell> move(Cell[][] pos, int x, int y);
    public abstract ArrayList<Cell> moveKingContext(Cell[][] pos, int x, int y);

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    public Piece getCopy() throws CloneNotSupportedException {
        return (Piece) this.clone();
    }
}
