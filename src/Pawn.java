import java.util.ArrayList;

/**
 * Created by Oscar on 5/11/17.
 */
public class Pawn extends Piece {

    public Pawn(String id, String pictureName, int color) {
        setId(id);
        setPictureLocation(pictureName);
        setColor(color);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possibleMoves.clear();

//        TODO: Implement Pawn movement
        if (getColor() == 0) {  // white piece

        } else {   // black piece

        }

        return possibleMoves;
    }
}
