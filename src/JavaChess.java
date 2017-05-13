import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Oscar on 5/10/17.
 */
public class JavaChess extends JFrame implements MouseListener {
    public static final int height = 800;
    public static final int width = 800;

    // pieces
    private static Pawn whitePawns[];
    private static Pawn blackPawns[];
    private static Rook whiteRook1, whiteRook2, blackRook1, blackRook2;

    // ui
    private Container content;
    private Cell chessBoardState[][];
    private Cell cellPressed;


    public static JavaChess MainScreen;
    private JPanel board = new JPanel(new GridLayout(8, 8));

    private static String move;

    public static void main(String[] args) {
        // generate pieces
        // pawns
        whitePawns = new Pawn[8];
        blackPawns = new Pawn[8];
        for (int i = 0; i < whitePawns.length; i++) {
            whitePawns[i] = new Pawn("WhitePawn0" + (i + 1), "whitepawn.png", 0);  // TODO: Add image name
            blackPawns[i] = new Pawn("BlackPawn0" + (i + 1), "blackpawn.png", 1);
        }
        // rooks
        whiteRook1 = new Rook("WhiteRook1", "whiterook.png", 0);
        whiteRook2 = new Rook("WhiteRook2", "whiterook.png", 0);
        blackRook1 = new Rook("BlackRook1", "blackrook.png", 1);
        blackRook2 = new Rook("blackRook2", "blackrook.png", 1);


        MainScreen = new JavaChess();
        MainScreen.setVisible(true);
        MainScreen.setResizable(false);
    }

    public JavaChess() {
        move = "White";
        board = new JPanel(new GridLayout(8, 8));
        board.setMinimumSize(new Dimension(480, 480));

        Cell cell;
        Piece piece;

        board.setBorder(BorderFactory.createLoweredBevelBorder());
        content = getContentPane();
        setSize(width, height);
        setTitle("Java Chess");
        content.setBackground(Color.black);
        content.setLayout(new BorderLayout());

        // generate all pieces and put on board
        chessBoardState = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                piece = null;

                if (i == 0 && j == 0) {
                    piece = blackRook1;
                } else if (i == 0 && j == 7) {
                    piece = blackRook2;
                } else if (i == 7 && j == 0) {
                    piece = whiteRook1;
                } else if (i == 7 && j == 7) {
                    piece = whiteRook2;
                }
//                TODO: All other pieces to be generated before pawns
                else if (i == 1) {
                    piece = blackPawns[j];
                } else if (i == 6) {
                    piece = whitePawns[j];
                }
                cell = new Cell(i, j, piece);
                cell.addMouseListener(this);
                board.add(cell);
                chessBoardState[i][j] = cell;
            }
        }
        content.add(board);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cellPressed = (Cell) e.getSource();

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
