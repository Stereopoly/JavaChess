import java.util.ArrayList;

/**
 * Created by Oscar on 5/12/17.
 */
public class Rook extends Piece {

    public Rook(String id, String pictureName, int color) {
        setId(id);
        setPictureLocation(pictureName);
        setColor(color);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
//        TODO: Override this
    }
}

