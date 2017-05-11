import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Oscar on 5/10/17.
 */
public class Cell extends JPanel implements Cloneable {
    private Boolean isPossibleDestination;
    private JLabel content;
    private Piece piece;
    int x;
    int y;
    private boolean isSelected = false;
    private boolean isCheck = false;

    public Cell(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        setLayout(new BorderLayout());

        if ((x + y) % 2 == 0) {
            setBackground(new Color(121, 85, 72));
        } else {
            setBackground(Color.white);
        }
        if (piece != null) {
            setPiece(piece);
        }
    }

    public Cell(Cell cell) throws CloneNotSupportedException {
        this.x = cell.x;
        this.y = cell.y;
        setLayout(new BorderLayout());

        if ((x + y) % 2 == 0) {
            setBackground(new Color(121, 85, 72));
        } else {
            setBackground(Color.white);
        }
        if (cell.getPiece() != null) {
            setPiece(cell.getPiece().getCopy());
        } else {
            piece = null;
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
//        TODO: Set picture details later
    }

    public void removePiece() {
//        TODO: Add remove functionality
    }

    public void selectPiece() {
        this.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        this.isSelected = true;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void deselectPiece() {
        this.setBorder(null);
        this.isSelected = false;
    }

    public void highlightPossibleDestinations() {
        this.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
        this.isPossibleDestination = true;
    }

    public void removeHighlightDestinations() {
        this.setBorder(null);
        this.isPossibleDestination = false;
    }

    public boolean isPossibleDestination() {
        return this.isPossibleDestination;
    }

//    TODO: Colors for if King is in check

}
