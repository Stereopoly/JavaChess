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

    /*public Cell(Cell cell) {
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
    }*/

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(piece.getPictureLocation()));
        content = new JLabel(imageIcon);
        this.add(content);
    }

    public void removePiece() {
//        TODO: Check if king
        this.piece = null;
        this.remove(content);
    }

    public void selectPiece() {
        this.setBorder(BorderFactory.createLineBorder(new Color(231, 76, 60), 4));
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
        this.setBorder(BorderFactory.createLineBorder(new Color(39, 174, 96), 4));
        this.isPossibleDestination = true;
    }

    public void removeHighlightDestinations() {
        this.setBorder(null);
        this.isPossibleDestination = false;
    }

    public boolean isPossibleDestination() {
        return this.isPossibleDestination;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck() {
        this.setBackground(new Color(231, 76, 60));
        this.isCheck = true;
    }

    public void removeCheck() {
        this.setBorder(null);
        if ((x + y) % 2 == 0) {
            setBackground(new Color(121, 85, 72));
        } else {
            setBackground(Color.white);
        }
        this.isCheck = false;
    }

}
