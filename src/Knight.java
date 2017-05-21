import java.util.ArrayList;

/**
 * Created by Oscar on 5/14/17.
 */
public class Knight extends Piece {

    private ArrayList<Cell> possibleMoves = new ArrayList<Cell>();

    public Knight(String id, String pictureName, int color) {
        setId(id);
        setPictureLocation(pictureName);
        setColor(color);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possibleMoves.clear();

        int[] possibleX = new int[]{x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2};
        int[] possibleY = new int[]{y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1};

        for (int i = 0; i < 8; i++) {    // same as king, brute force check the possible moves
            if (possibleX[i] >= 0 && possibleX[i] < 8) {
                if (possibleY[i] >= 0 && possibleY[i] < 8) {
                    if (pos[possibleX[i]][possibleY[i]].getPiece() == null || pos[possibleX[i]][possibleY[i]].getPiece().getColor() != this.getColor()) {
                        // empty or opposing color are allowed spots
                        possibleMoves.add(pos[possibleX[i]][possibleY[i]]);
                    }
                }
            }
        }

        return possibleMoves;
    }
}
