import java.util.ArrayList;

/**
 * Created by Oscar on 5/13/17.
 */
public class King extends Piece {

    private int x, y;

    public King(String id, String pictureName, int color, int x, int y) {
        setId(id);
        setPictureLocation(pictureName);
        setColor(color);
        setX(x);
        setY(y);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possibleMoves.clear();

//        TODO: Implement King movement
        int[] possibleX = new int[]{};  // 8 possible places
        int[] possibleY = new int[]{};

        for (int i = 0; i < 8; i++) {
            if (possibleX[i] >= 0 && possibleX[i] < 8) {
                if (possibleY[i] >= 0 && possibleY[i] < 8) {
                    if (pos[possibleX[i]][possibleY[i]].getPiece() == null || pos[possibleX[i]][possibleY[i]].getPiece().getColor() != this.getColor()) {
                        // empty or opposing color
                        possibleMoves.add(pos[possibleX[i]][possibleY[i]]);
                    }
                }
            }
        }

        return possibleMoves;
    }

    public boolean isKingInDanger(Cell chessBoardState[][]) {
//        TODO: Do this stuff
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
