import java.util.ArrayList;

/**
 * Created by Oscar on 5/14/17.
 */
public class Queen extends Piece {

    public Queen(String id, String pictureName, int color) {
        setId(id);
        setPictureLocation(pictureName);
        setColor(color);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possibleMoves.clear();

        // rook code:
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

        // bishop code
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
}
