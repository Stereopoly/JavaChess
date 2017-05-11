import java.util.ArrayList;

/**
 * Created by Oscar on 5/10/17.
 */
public abstract class Piece implements Cloneable {
    private int color;
    private String path;
    private String id = null;
    public ArrayList<Cell> possibleMoves = new ArrayList<Cell>();

    public abstract ArrayList<Cell> move(Cell pos[][], int x, int y);

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Piece getCopy() throws CloneNotSupportedException {
        return (Piece) this.clone();
    }
}
