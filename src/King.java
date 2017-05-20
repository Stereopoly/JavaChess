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
        int[] possibleX = new int[]{x, x, x + 1, x + 1, x + 1, x - 1, x - 1, x - 1};  // 8 possible moves by the king
        int[] possibleY = new int[]{y - 1, y + 1, y - 1, y, y + 1, y - 1, y, y + 1};

        for (int i = 0; i < 8; i++) {
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

    public boolean isKingInDanger(Cell chessBoardState[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoardState[i][j].getPiece().getColor() != this.getColor()) {
                    // opposing color
                    ArrayList<Cell> tempMoves = chessBoardState[i][j].getPiece().move(chessBoardState, i, j);
                    for (Cell move : tempMoves) {
                        if (move.getPiece() != null) {
                            if (move.getPiece() instanceof King) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

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
