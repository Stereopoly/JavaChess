import java.util.ArrayList;

/**
 * Created by Oscar on 5/13/17.
 */
public class Bishop extends Piece {

    private ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    private ArrayList<Cell> possibleMovesKingContext = new ArrayList<Cell>();

    public Bishop(String pictureName, int color) {
        setPictureLocation(pictureName);
        setColor(color);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possibleMoves.clear();

        // down and left
        for (int tx = x + 1, ty = y - 1; tx < 8 && ty >= 0; tx++, ty--) {
            if (pos[tx][ty].getPiece() == null) {
                possibleMoves.add(pos[tx][ty]);
            } else if (pos[tx][ty].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMoves.add(pos[tx][ty]);
                break;
            }
        }
        // down and right
        for (int tx = x + 1, ty = y + 1; tx < 8 && ty < 8; tx++, ty++) {
            if (pos[tx][ty].getPiece() == null) {
                possibleMoves.add(pos[tx][ty]);
            } else if (pos[tx][ty].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMoves.add(pos[tx][ty]);
                break;
            }
        }
        // up and left
        for (int tx = x - 1, ty = y - 1; tx >= 0 && ty >= 0; tx--, ty--) {
            if (pos[tx][ty].getPiece() == null) {
                possibleMoves.add(pos[tx][ty]);
            } else if (pos[tx][ty].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMoves.add(pos[tx][ty]);
                break;
            }
        }
        // up and right
        for (int tx = x - 1, ty = y + 1; tx >= 0 && ty < 8; tx--, ty++) {
            if (pos[tx][ty].getPiece() == null) {
                possibleMoves.add(pos[tx][ty]);
            } else if (pos[tx][ty].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMoves.add(pos[tx][ty]);
                break;
            }
        }

        return possibleMoves;
    }

    @Override
    public ArrayList<Cell> moveKingContext(Cell[][] pos, int x, int y) {
        possibleMovesKingContext.clear();

        // down and left
        for (int tx = x + 1, ty = y - 1; tx < 8 && ty >= 0; tx++, ty--) {
            if (pos[tx][ty].getPiece() == null) {
                possibleMovesKingContext.add(pos[tx][ty]);
            } else if (pos[tx][ty].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMovesKingContext.add(pos[tx][ty]);
                break;
            }
        }
        // down and right
        for (int tx = x + 1, ty = y + 1; tx < 8 && ty < 8; tx++, ty++) {
            if (pos[tx][ty].getPiece() == null) {
                possibleMovesKingContext.add(pos[tx][ty]);
            } else if (pos[tx][ty].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMovesKingContext.add(pos[tx][ty]);
                break;
            }
        }
        // up and left
        for (int tx = x - 1, ty = y - 1; tx >= 0 && ty >= 0; tx--, ty--) {
            if (pos[tx][ty].getPiece() == null) {
                possibleMovesKingContext.add(pos[tx][ty]);
            } else if (pos[tx][ty].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMovesKingContext.add(pos[tx][ty]);
                break;
            }
        }
        // up and right
        for (int tx = x - 1, ty = y + 1; tx >= 0 && ty < 8; tx--, ty++) {
            if (pos[tx][ty].getPiece() == null) {
                possibleMovesKingContext.add(pos[tx][ty]);
            } else if (pos[tx][ty].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMovesKingContext.add(pos[tx][ty]);
                break;
            }
        }

        return possibleMovesKingContext;
    }
}
