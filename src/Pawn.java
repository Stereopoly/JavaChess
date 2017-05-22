import java.util.ArrayList;

/**
 * Created by Oscar on 5/11/17.
 */
public class Pawn extends Piece {

    private ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    private ArrayList<Cell> possibleMovesKingContext = new ArrayList<Cell>();

    public Pawn(String id, String pictureName, int color) {
        setId(id);
        setPictureLocation(pictureName);
        setColor(color);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possibleMoves.clear();

        if (getColor() == 0) {  // white piece
            if (x == 0) {
                return possibleMoves;
            }
            if (pos[x - 1][y].getPiece() == null) {
                possibleMoves.add(pos[x - 1][y]);  // 1 step forward in whites's perspective, 1 step back in blacks's perspective
                if (x == 6) {  // first pawn move can move 2 spots up
                    if (pos[4][y].getPiece() == null) {
                        possibleMoves.add(pos[4][y]);
                    }
                }
            }

            if (y > 0) {
                if (pos[x - 1][y - 1].getPiece() != null) {  // diag left and forward 1  (white's perspective)
                    if (pos[x - 1][y - 1].getPiece().getColor() != this.getColor()) {  // opposing pawn to capture
                        possibleMoves.add(pos[x - 1][y - 1]);
                    }
                }
            }
            if (y < 7) {
                if (pos[x - 1][y + 1].getPiece() != null) {  // diag right and forward 1   (white's perspective)
                    if (pos[x - 1][y + 1].getPiece().getColor() != this.getColor()) {
                        possibleMoves.add(pos[x - 1][y + 1]);
                    }
                }
            }
        } else {   // black piece
            if (x == 8) {
                return possibleMoves;
            }
            if (pos[x + 1][y].getPiece() == null) {
                possibleMoves.add(pos[x + 1][y]);  // 1 step forward in black's perspective, 1 step back in white's perspective
                if (x == 1) {  // first pawn move can move 2 spots up
                    if (pos[3][y].getPiece() == null) {
                        possibleMoves.add(pos[3][y]);
                    }
                }
            }

            if (y > 0) {
                if (pos[x + 1][y - 1].getPiece() != null) {  // diag left and forward 1   (black's perspective)
                    if (pos[x + 1][y - 1].getPiece().getColor() != this.getColor()) {  // opposing pawn to capture
                        possibleMoves.add(pos[x + 1][y - 1]);
                    }
                }
            }
            if (y < 7) {
                if (pos[x + 1][y + 1].getPiece() != null) {  // diag right and forward 1  (black's perspective)
                    if (pos[x + 1][y + 1].getPiece().getColor() != this.getColor()) {
                        possibleMoves.add(pos[x + 1][y + 1]);
                    }
                }
            }
        }

        return possibleMoves;
    }

    @Override
    public ArrayList<Cell> moveKingContext(Cell[][] pos, int x, int y) {
        possibleMovesKingContext.clear();

        if (getColor() == 0) {  // white piece
            if (x == 0) {
                return possibleMovesKingContext;
            }
            if (pos[x - 1][y].getPiece() == null) {
                possibleMovesKingContext.add(pos[x - 1][y]);  // 1 step forward in whites's perspective, 1 step back in blacks's perspective
                if (x == 6) {  // first pawn move can move 2 spots up
                    if (pos[4][y].getPiece() == null) {
                        possibleMovesKingContext.add(pos[4][y]);
                    }
                }
            }

            if (y > 0) {
                if (pos[x - 1][y - 1].getPiece() != null) {  // diag left and forward 1  (white's perspective)
                    if (pos[x - 1][y - 1].getPiece().getColor() != this.getColor()) {  // opposing pawn to capture
                        possibleMovesKingContext.add(pos[x - 1][y - 1]);
                    }
                }
            }
            if (y < 7) {
                if (pos[x - 1][y + 1].getPiece() != null) {  // diag right and forward 1   (white's perspective)
                    if (pos[x - 1][y + 1].getPiece().getColor() != this.getColor()) {
                        possibleMovesKingContext.add(pos[x - 1][y + 1]);
                    }
                }
            }
        } else {   // black piece
            if (x == 8) {
                return possibleMovesKingContext;
            }
            if (pos[x + 1][y].getPiece() == null) {
                possibleMovesKingContext.add(pos[x + 1][y]);  // 1 step forward in black's perspective, 1 step back in white's perspective
                if (x == 1) {  // first pawn move can move 2 spots up
                    if (pos[3][y].getPiece() == null) {
                        possibleMovesKingContext.add(pos[3][y]);
                    }
                }
            }

            if (y > 0) {
                if (pos[x + 1][y - 1].getPiece() != null) {  // diag left and forward 1   (black's perspective)
                    if (pos[x + 1][y - 1].getPiece().getColor() != this.getColor()) {  // opposing pawn to capture
                        possibleMovesKingContext.add(pos[x + 1][y - 1]);
                    }
                }
            }
            if (y < 7) {
                if (pos[x + 1][y + 1].getPiece() != null) {  // diag right and forward 1  (black's perspective)
                    if (pos[x + 1][y + 1].getPiece().getColor() != this.getColor()) {
                        possibleMovesKingContext.add(pos[x + 1][y + 1]);
                    }
                }
            }
        }

        return possibleMoves;
    }
}
