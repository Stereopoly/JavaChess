import java.util.ArrayList;

/**
 * Created by Oscar on 5/12/17.
 */
public class Rook extends Piece {

    private ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    private ArrayList<Cell> possibleMovesKingContext = new ArrayList<Cell>();


    public Rook(String id, String pictureName, int color) {
        setId(id);
        setPictureLocation(pictureName);
        setColor(color);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possibleMoves.clear();

        // left movement
        for (int i = y - 1; i >= 0; i--) {
            if (pos[x][i].getPiece() == null) {
                possibleMoves.add(pos[x][i]);
            } else if (pos[x][i].getPiece().getColor() == this.getColor()) {
                break;
            } else {  // capture the piece
                possibleMoves.add(pos[x][i]);
                break;
            }
        }
        // right movement
        for (int i = y + 1; i < 8; i++) {
            if (pos[x][i].getPiece() == null) {
                possibleMoves.add(pos[x][i]);
            } else if (pos[x][i].getPiece().getColor() == this.getColor()) {
                break;
            } else {  // capture the piece
                possibleMoves.add(pos[x][i]);
                break;
            }
        }
        // up movement
        for (int i = x - 1; i >= 0; i--) {
            if (pos[i][y].getPiece() == null) {
                possibleMoves.add(pos[i][y]);
            } else if (pos[i][y].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMoves.add(pos[i][y]);
                break;
            }
        }
        // down movement
        for (int i = x + 1; i < 8; i++) {
            if (pos[i][y].getPiece() == null) {
                possibleMoves.add(pos[i][y]);
            } else if (pos[i][y].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMoves.add(pos[i][y]);
                break;
            }
        }

        return possibleMoves;
    }

    @Override
    public ArrayList<Cell> moveKingContext(Cell[][] pos, int x, int y) {
        possibleMovesKingContext.clear();

        // left movement
        for (int i = y - 1; i >= 0; i--) {
            if (pos[x][i].getPiece() == null) {
                possibleMovesKingContext.add(pos[x][i]);
            } else if (pos[x][i].getPiece().getColor() == this.getColor()) {
                break;
            } else {  // capture the piece
                possibleMovesKingContext.add(pos[x][i]);
                break;
            }
        }
        // right movement
        for (int i = y + 1; i < 8; i++) {
            if (pos[x][i].getPiece() == null) {
                possibleMovesKingContext.add(pos[x][i]);
            } else if (pos[x][i].getPiece().getColor() == this.getColor()) {
                break;
            } else {  // capture the piece
                possibleMovesKingContext.add(pos[x][i]);
                break;
            }
        }
        // up movement
        for (int i = x - 1; i >= 0; i--) {
            if (pos[i][y].getPiece() == null) {
                possibleMovesKingContext.add(pos[i][y]);
            } else if (pos[i][y].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMovesKingContext.add(pos[i][y]);
                break;
            }
        }
        // down movement
        for (int i = x + 1; i < 8; i++) {
            if (pos[i][y].getPiece() == null) {
                possibleMovesKingContext.add(pos[i][y]);
            } else if (pos[i][y].getPiece().getColor() == this.getColor()) {
                break;
            } else {
                possibleMovesKingContext.add(pos[i][y]);
                break;
            }
        }

        return possibleMovesKingContext;
    }
}

